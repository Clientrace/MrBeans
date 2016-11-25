package ip_manager;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import java.awt.*;
import java.util.List;

/**
 * Created by clientrace on 11/21/16.
 */
public class ImageData {
    private Mat imgOrig;
    private Mat imgHSV;
    private Mat imgGRAY;
    private Mat imgLAB;
    private Mat imgOutput;
    private Mat imgNoiseFiltered;
    private Mat imgBackgroudnSubtract;
    private Mat kmeansResult;
    private Mat bgsOutput;
    private Mat segmentationOutput;
    private Mat noiseFilterOutput;

    private List<MatOfPoint> contours;

    private int convexHullCount;

    private float ellipticality;

    public Mat getImgHSV() {
        return imgHSV;
    }

    public void setImgHSV(Mat imgHSV) {
        this.imgHSV = imgHSV;
    }

    public Mat getImgGRAY() {
        return imgGRAY;
    }

    public void setImgGRAY(Mat imgGRAY) {
        this.imgGRAY = imgGRAY;
    }

    public Mat getImgLAB() {
        return imgLAB;
    }

    public void setImgLAB(Mat imgLAB) {
        this.imgLAB = imgLAB;
    }

    public Mat getImgOrig() {
        return imgOrig;
    }

    public void setImgOrig(Mat imgOrig) {
        this.imgOrig = imgOrig;
    }

    public Mat getImgOutput() {
        return imgOutput;
    }

    public void setImgOutput(Mat imgOutput) {
        this.imgOutput = imgOutput;
    }

    public Mat getImgNoiseFiltered() {
        return imgNoiseFiltered;
    }

    public void setImgNoiseFiltered(Mat imgNoiseFiltered) {
        this.imgNoiseFiltered = imgNoiseFiltered;
    }

    public Mat getImgBackgroudnSubtract() {
        return imgBackgroudnSubtract;
    }

    public void setImgBackgroudnSubtract(Mat imgBackgroudnSubtract) {
        this.imgBackgroudnSubtract = imgBackgroudnSubtract;
    }

    public Mat getKmeansResult() {
        return kmeansResult;
    }

    public void setKmeansResult(Mat kmeansResult) {
        this.kmeansResult = kmeansResult;
    }

    public Mat getBgsOutput() {
        return bgsOutput;
    }

    public void setBgsOutput(Mat bgsOutput) {
        this.bgsOutput = bgsOutput;
    }

    public Mat getSegmentationOutput() {
        return segmentationOutput;
    }

    public void setSegmentationOutput(Mat segmentationOutput) {
        this.segmentationOutput = segmentationOutput;
    }

    public Mat getNoiseFilterOutput() {
        return noiseFilterOutput;
    }

    public void setNoiseFilterOutput(Mat noiseFilterOutput) {
        this.noiseFilterOutput = noiseFilterOutput;
    }

    public List<MatOfPoint> getContours() {
        return contours;
    }

    public void setContours(List<MatOfPoint> contours) {
        this.contours = contours;
    }

    public int getConvexHullCount() {
        return convexHullCount;
    }

    public void setConvexHullCount(int convexHullCount) {
        this.convexHullCount = convexHullCount;
    }

    public float getEllipticality() {
        return ellipticality;
    }

    public void setEllipticality(float ellipticality) {
        this.ellipticality = ellipticality;
    }
}
