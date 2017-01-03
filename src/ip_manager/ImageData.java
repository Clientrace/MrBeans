package ip_manager;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

import java.util.List;

/**
 * Created by clientrace on 11/21/16.
 */
public class ImageData {
    private Mat imgOrig;
    private Mat imgHSV;
    private Mat imgGRAY;
    private Mat imgLAB;
    private Mat imgBackgroudnSubtract;
    private Mat kmeansResult;
    private Mat bgsOutput;
    private Mat wsOutput;
    private Mat nfOutput;
    private Mat imgOutput;
    private List<MatOfPoint> contours;

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

    public Mat getWsOutput() {
        return wsOutput;
    }

    public void setWsOutput(Mat wsOutput) {
        this.wsOutput = wsOutput;
    }

    public Mat getImgOutput() {
        return imgOutput;
    }

    public void setImgOutput(Mat imgOutput) {
        this.imgOutput = imgOutput;
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

    public List<MatOfPoint> getContours() {
        return contours;
    }

    public void setContours(List<MatOfPoint> contours) {
        this.contours = contours;
    }

    public Mat getNfOutput() {
        return nfOutput;
    }

    public void setNfOutput(Mat nfOutput) {
        this.nfOutput = nfOutput;
    }

}
