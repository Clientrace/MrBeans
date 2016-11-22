package ip_manager;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by clientrace on 11/21/16.
 */
public class ColorSpace {

    private int[] info;
    private Mat imgOrig;
    private Mat imgHSV;
    private Mat imgGrayScale;
    private Mat imgLab;

    public ColorSpace(Mat imgOrig){
        this.imgOrig = imgOrig.clone();
    }

    public void init_ColorSpace(){
        info = new int[6];
    }//init_ColorSpace

    public void execute_ColorSpace(){
        //Add error handler here
        Imgproc.cvtColor(imgOrig, imgHSV, Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(imgOrig, imgGrayScale, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(imgOrig, imgLab, Imgproc.COLOR_BGR2Lab);
    }//execute_ColorSpace

    public void setInfo(int[] info) {
        this.info = info;
    }

    public int[] getInfo() {
        return info;
    }

    public Mat getImgOrig() {
        return imgOrig;
    }

    public Mat getImgHSV() {
        return imgHSV;
    }

    public Mat getImgGrayScale() {
        return imgGrayScale;
    }

    public Mat getImgLab() {
        return imgLab;
    }
}
