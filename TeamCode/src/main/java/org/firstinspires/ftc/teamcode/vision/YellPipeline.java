package org.firstinspires.ftc.teamcode.vision;

import static java.lang.Thread.sleep;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class YellPipeline extends OpenCvPipeline {
    public static boolean debug = false; // Toggle for debug mode
    static final Scalar YELLOW = new Scalar(255, 255, 0);
    static Scalar lowerYellow = new Scalar(20, 100, 100); // Lower bound for yellow-ish in HSV
    static Scalar upperYellow = new Scalar(30, 255, 255); // Upper bound for yellow-ish in HSV
    static boolean found = false;
    int sel;

    Rect center;
    Mat region;
    Mat hsvMat = new Mat();
    Mat mask = new Mat();

    Point yellowestPoint = new Point(); // The point of the most yellow
    double maxVal; // Maximum value of the yellow intensity

    public YellPipeline() {
        center = new Rect(152, 38, 225, 265);
    }

    @Override
    public void init(Mat firstFrame) {
        // Initialization if necessary
    }

    @Override
    public Mat processFrame(Mat input) {
        try {
            // Convert the frame to HSV color space
            Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);

            if (!debug) {
                // Apply color threshold to isolate yellow regions
                Core.inRange(hsvMat, lowerYellow, upperYellow, mask);

                // Define a region of interest (ROI) from the mask
                region = new Mat(mask, center);

                // Find the location of the "yellowest" point within the region
                MinMaxLocResult mmr = Core.minMaxLoc(region);

                // Get the maximum value and location of the yellowest point
                maxVal = mmr.maxVal;
                yellowestPoint = new Point(mmr.maxLoc.x + center.x, mmr.maxLoc.y + center.y); // Offset by the center region

                found = maxVal > 0; // A non-zero maxVal indicates a yellow object was found

                region.release();
            } else {
                // Draw a rectangle around the region being processed
                Imgproc.rectangle(input, center, YELLOW, 2);
            }

            // Optionally delay for debug purposes
            sleep(100);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            // Handle any exceptions
        }

        // Optionally, draw a small circle at the yellowest point if detected
        if (found && !debug) {
            Imgproc.circle(input, yellowestPoint, 5, YELLOW, 2);
        }

        return input;
    }

    public String getAnalysis() {
        if (found) {
            return String.format("Yellowest point at (%.2f, %.2f) with intensity: %.2f", yellowestPoint.x, yellowestPoint.y, maxVal);
        } else {
            return "No yellow object detected";
        }
    }
}
