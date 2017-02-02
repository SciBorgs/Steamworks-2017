import cv2
import numpy as np
import threading
from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
from SocketServer import ThreadingMixIn
import StringIO
import time
import sys
from PIL import Image
from networktables import NetworkTables as nt
import httplib

nt.initialize(server="10.11.55.51")
loopUseless = 0
#A delay is required for a successful connection to the roboRIO
while loopUseless < 300000:
    loopUseless = loopUseless + 1
    connected = nt.isConnected()  
if connected == False:
    print("The roboRIO was not found!")
    sys.exit()
else:
    print("Great, I have connected with the 1155 SmartDashboard!")
#Calls upon the SmartDashboard table
global dashboardTable
dashboardTable = nt.getTable('SmartDashboard')

# global FOV_HORZ_ANGLE
# global FOV_VERT_ANGLE
#
# FOV_HORZ_ANGLE = 60
# FOV_VERT_ANGLE = 60
#
# global FOV_WIDTH_PIXELS
# global FOV_HEIGHT_PIXELS
#
# FOV_WIDTH_PIXELS = 640
# FOV_HEIGHT_PIXELS = 480
#
# global FOCAL_LENGTH
# FOCAL_LENGTH = 700
# # 600
# global LOWER_COLOR_BOUNDS
# global UPPER_COLOR_BOUNDS
#
# LOWER_COLOR_BOUNDS = np.array([200, 230, 40], dtype="uint8") #RGB BGR
# UPPER_COLOR_BOUNDS = np.array([255, 255, 255], dtype="uint8")
#
# global GENEROSITY_CONSTANT
# GENEROSITY_CONSTANT = 0.02
#
# # EVERYTHING IN INCHES
# global PEG_TAPE_WIDTH
# global PEG_TAPE_HEIGHT
# global HIGH_GOAL_TAPES_WIDTH
# global TOP_TAPE_HEIGHT
# global BOT_TAPE_HEIGHT
#
# PEG_TAPE_HEIGHT = 5
# PEG_TAPE_WIDTH = 2
# HIGH_GOAL_TAPES_WIDTH = 15
# TOP_TAPE_HEIGHT = 4
# BOT_TAPE_HEIGHT = 2
#
# global PEG_TAPE_DISTANCE_IN_BW
# global HIGH_GOAL_TAPE_DISTANCE_BW_EDGES
# global PEG_TAPE_DISTANCE_CENTER2CENTER
#
# PEG_TAPE_DISTANCE_CENTER2CENTER = 8.25
# PEG_TAPE_DISTANCE_IN_BW = 6.25
# HIGH_GOAL_TAPE_DISTANCE_BW_EDGES = 10

global serverIP
serverIP = "10.11.55.109"


def main():
    startCamera()
    startServer()


class ImageAnalysis():
    FOV_HORZ_ANGLE = 60
    FOV_VERT_ANGLE = 60

    FOV_WIDTH_PIXELS = 640
    FOV_HEIGHT_PIXELS = 480

    FOCAL_LENGTH = 700

    LOWER_COLOR_BOUNDS = np.array([200, 230, 40], dtype="uint8")  # RGB BGR
    #LOWER_COLOR_BOUNDS = np.array([220, 220, 220], dtype="uint8")
    UPPER_COLOR_BOUNDS = np.array([255, 255, 255], dtype="uint8")

    GENEROSITY_CONSTANT = 0.02

    PEG_TAPE_HEIGHT = 5
    PEG_TAPE_WIDTH = 2
    HIGH_GOAL_TAPES_WIDTH = 15
    TOP_TAPE_HEIGHT = 4
    BOT_TAPE_HEIGHT = 2

    PEG_TAPE_DISTANCE_CENTER2CENTER = 8.25
    PEG_TAPE_DISTANCE_IN_BW = 6.25
    HIGH_GOAL_TAPE_DISTANCE_BW_EDGES = 10

    EDITED_IMAGE = np.zeros((FOV_HEIGHT_PIXELS, FOV_WIDTH_PIXELS, 3), np.uint8)

    #def setImageFrame(self, sourceImage):
    #    self.sourceImage = sourceImage

    def startAnalysis(self, sourceImage):
        # while (VIDEO_STREAM.isOpened()):

        imageMask = cv2.inRange(sourceImage, ImageAnalysis.LOWER_COLOR_BOUNDS, ImageAnalysis.UPPER_COLOR_BOUNDS)
        imageMask = abs(255 - imageMask)

        retVal, threshedImage = cv2.threshold(imageMask, 255, 255, 255)

        filteredStream, validContours, hierarchy = cv2.findContours(threshedImage.copy(), cv2.RETR_CCOMP,
                                                                    cv2.CHAIN_APPROX_TC89_L1)

        # Center X, Center Y, Area
        previousValidTape = [0, 0, 0]

        for contour in validContours:
            try:
                moments = cv2.moments(contour)
                centerPoint = (int((moments["m10"] / moments["m00"])),
                               int((moments["m01"] / moments["m00"])))

                contour = contour.astype("int")
                cArea = cv2.contourArea(contour)
                cPerimeter = cv2.arcLength(contour, True)
                cBoundary = cv2.approxPolyDP(contour, ImageAnalysis.GENEROSITY_CONSTANT * cPerimeter, True)
                x, y, w, h = cv2.boundingRect(cBoundary)

                shape = self.determineShape(cBoundary, cPerimeter, w, h)

                if (shape == "Rectangle"):
                    self.drawContourMarkers(sourceImage, x, y, w, h, centerPoint, cBoundary)
                    outputFile = open("output.txt", "w")
                    target = self.determineTarget(centerPoint[0], centerPoint[1], cArea,
                                                           previousValidTape[0], previousValidTape[1],
                                                           previousValidTape[2])

                    if (target == "Gear Peg"):
                        # print "Seeing Peg"
                        dashboardTable.putString('Currently Seeing: ', 'Gear Peg')
                        midPoint = self.calculateMidpoint(sourceImage, centerPoint[0],
                                                                   previousValidTape[0],
                                                                   centerPoint[1], target)

                        distanceToTarget = (ImageAnalysis.FOCAL_LENGTH * ImageAnalysis.PEG_TAPE_DISTANCE_CENTER2CENTER /
                                            abs(centerPoint[0] - previousValidTape[0]))

                        theta = (midPoint[0] * (ImageAnalysis.FOV_HORZ_ANGLE / 2) /
                                 (ImageAnalysis.FOV_WIDTH_PIXELS / 2) -
                                 (ImageAnalysis.FOV_HORZ_ANGLE / 2))

                        cv2.putText(sourceImage, "Distance: %f" % (distanceToTarget), (x, y - 50),
                                    cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2)
                        dashboardTable.putNumber('Distance: ', distanceToTarget)
                        cv2.putText(sourceImage, "Theta: " + str(theta), (x, y - 25), cv2.FONT_HERSHEY_SIMPLEX,
                                    0.5, (255, 255, 255), 2)
                        dashboardTable.putNumber('Theta: ', theta)
                        outputFile.write("Distance from target:\n%f\n" % (distanceToTarget))
                        outputFile.write("Angle from target:\n%f\n" % (theta))
                        outputFile.close()

                    elif (target == "High Goal"):
                        # print "Seeing High Goal"
                        dashboardTable.putString('Currently Seeing: ', 'High Goal')
                        midPoint = self.calculateMidpoint(sourceImage, centerPoint[1],
                                                                   previousValidTape[1],
                                                                   centerPoint[0], target)

                        distanceToTarget = (ImageAnalysis.FOCAL_LENGTH * ImageAnalysis.HIGH_GOAL_TAPES_WIDTH /
                                            float(w))

                        theta = (midPoint[0] * (ImageAnalysis.FOV_HORZ_ANGLE / 2) /
                                 (ImageAnalysis.FOV_WIDTH_PIXELS / 2) -
                                 (ImageAnalysis.FOV_HORZ_ANGLE / 2))

                        cv2.putText(sourceImage, "Distance: %f" % (distanceToTarget), (x, y - 25),
                                    cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2)
                        dashboardTable.putNumber('Distance: ', float(distanceToTarget))
                        cv2.putText(sourceImage, "Theta: " + str(theta), (x, y), cv2.FONT_HERSHEY_SIMPLEX, 0.5,
                                    (255, 255, 255), 2)
                        dashboardTable.putNumber('Theta: ', theta)
                        outputFile.write("Distance from target:\n%f\n" % (distanceToTarget))
                        outputFile.write("Angle from target:\n%f\n" % (theta))
                        outputFile.close()
                    else:
                        previousValidTape[0] = centerPoint[0]
                        previousValidTape[1] = centerPoint[1]
                        previousValidTape[2] = cArea

            except ZeroDivisionError:
                pass

                #	global CONTOURED_IMAGE
                #	CONTOURED_IMAGE = self.sourceImage
        # self.editedImage = self.sourceImage

        # EDITED_IMAGE = np.zeroes((self.FOV_HEIGHT_PIXELS,self.FOV_WIDTH_PIXELS,3),np.uint8)
        #ImageAnalysis.EDITED_IMAGE = sourceImage
        return sourceImage
    # keyPressed = cv2.waitKey(1)
    # if keyPressed == ord('q'):
    #     print("PROGRAM EXITED".center(50, " "))
    #     break
    # elif keyPressed == ord('p'):
    #     print ("PICTURE TAKEN".center(50, " "))
    #     cv2.imwrite("Original Image + Contours.jpg", sourceImage)

    # Returns the name of contour's approximate shape
    def determineShape(self, boundary, perimeter, w, h):
        shape = "unknown"

        # Too small to be determined
        if (perimeter < 60 or perimeter >= 2000):
            return shape

        # len(approx) returns the number of vertices on the contour's approximated polygon.
        # Compares this value with a preset value to determine what kind of polygon it is
        elif (len(boundary) == 4):
            whRatio = float(w) / h
            shape = "Rectangle"

            if (0.9 <= whRatio <= 1.05):
                shape = "Square"

        return shape

    def drawContourMarkers(self, source, x, y, w, h, center, boundary):
        cv2.circle(source, center, 3, (0, 0, 0), -1)
        cv2.drawContours(source, [boundary], -1, (0, 255, 0), 3)
        cv2.rectangle(source, (x, y), (x + w, y + h), (255, 0, 0), 2)

        # TODO Debug print statements here
        cv2.putText(source, "Area: " + str(w * h), (x, y - 100), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2)
       
        cv2.putText(source, "Y Position: " + str(y), (x, y - 75), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2)
      
    def determineTarget(self, x, y, area, prevX, prevY, prevArea):
        target = "n/a"


        if (0.75 <= float(prevArea) / area <= 1.1 or 0.45 <= float(prevArea) / area <= 0.55):
            if (0.9 <= float(prevY) / y <= 1.1):
                target = "Gear Peg"
            elif (0.9 <= float(prevX) / x <= 1.1):
                target = "High Goal"
        return target

    def calculateMidpoint(self, source, px1, px2, px3, target):

        if (target == "Gear Peg"):
            cv2.circle(source, (px1 + (px2 - px1) / 2, px3), 10, (255, 0, 0), -1)
            return (px1 + (px2 - px1) / 2, px3)
        elif (target == "High Goal"):
            cv2.circle(source, (px3, (px1 + (px2 - px1) / 2)), 10, (255, 0, 0), -1)
            return (px3, px1 + (px2 - px1) / 2)

    def getContouredImage(self):
        return ImageAnalysis.EDITED_IMAGE


def startCamera():
    global VIDEO_STREAM

    for i in range(0, 5):
        VIDEO_STREAM = cv2.VideoCapture(i)
        if (VIDEO_STREAM.isOpened()):
            print("Camera found on port: %d" % (i))
            dashboardTable.putString("Camera Status: ", "Camera on port: %d" % (i))
            print("Width resolution: %d" % (VIDEO_STREAM.get(3)))
            print("Height resolution: %d" % (VIDEO_STREAM.get(4)))
            break
        if (not VIDEO_STREAM.isOpened()):
            dashboardTable.putString("Camera Status: ", "Camera Not Found!")
            print("Camera not found!")
            continue


def startServer():
    if (not VIDEO_STREAM.isOpened()):
        return
    else:
        try:
            # server = ThreadedHTTPServer(('10.11.55.109', 8080), CamHandler)
            server = ThreadedHTTPServer((serverIP, 8080), CamHandler)
            print "Server started"
            dashboardTable.putString("Server Status", "Server started")

            #            try:
            #                urlTest = httplib.HTTPConnection("10.95.52.83:8080/cam.mjpg")
            #                urlTest.connect()
            #            except httplib.HTTPException as ex:
            #                print "Server connection failed"
            #                return

            server.serve_forever()

        except KeyboardInterrupt:
            VIDEO_STREAM.release()
            server.socket.close()


# *****************************************************************#
class ThreadedHTTPServer(ThreadingMixIn, HTTPServer):
    """Handle requests in a separate thread."""


class CamHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        if self.path.endswith('.mjpg'):
            self.send_response(200)
            self.send_header('Content-type', 'multipart/x-mixed-replace; boundary=--jpgboundary')
            self.end_headers()
            while True:
                try:
                    rc, img = VIDEO_STREAM.read()
                    if not rc:
                        continue

                    analysis = ImageAnalysis()
                    #analysis.setImageFrame(img)

                    contourModifiedImage = analysis.startAnalysis(img)

                    imgRGB = cv2.cvtColor(contourModifiedImage, cv2.COLOR_BGR2RGB)
                    # jpg = Image.fromarray(imgRGB)
                    jpg = Image.fromarray(imgRGB)
                    tmpFile = StringIO.StringIO()
                    jpg.save(tmpFile, 'JPEG')
                    self.wfile.write("--jpgboundary")
                    self.send_header('Content-type', 'image/jpeg')
                    self.send_header('Content-length', str(tmpFile.len))
                    self.end_headers()
                    jpg.save(self.wfile, 'JPEG')
                    time.sleep(0.05)
                except KeyboardInterrupt:
                    break
            return
        if self.path.endswith('.html'):
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            self.wfile.write('<html><head></head><body>')
            # self.wfile.write('<img src="http://10.95.52.8:8080/cam.mjpg"/>')
            self.wfile.write('<img src="http://%d:8080/cam.mjpg' % (serverIP))
            self.wfile.write('</body></html>')
            return


main()
