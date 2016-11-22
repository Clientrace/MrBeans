package ip_manager;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import java.util.List;

/**
 * Created by clientrace on 11/21/16.
 */
public class ImageData {
    private Mat imgOrig;
    private Mat imgOutput;

    private Mat imgNoiseFiltered;
    private Mat imgBackgroudnSubtract;
    private Mat kmeansResult;

    private List<MatOfPoint> contours;


    private int convexHullCount;

    private float ellipticality;

}
