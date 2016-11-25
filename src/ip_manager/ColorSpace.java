package ip_manager;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by clientrace on 11/21/16.
 */
public class ColorSpace {

    public static final int HSV = 0;
    public static final int GRAY = 1;
    public static final int LAB = 2;
    public static int state;

    private int[] info;
    private ImageData imageData;
    private Mat imgHSV;
    private Mat imgGrayScale;
    private Mat imgLab;

    public void init_ColorSpace(ImageData imageData) {
        System.out.println("Initializing ColorSpaces...");
        state = HSV;
        this.imageData = imageData;
        imgHSV = new Mat();
        imgGrayScale = new Mat();
        imgLab = new Mat();
    }//init_ColorSpace

    public void execute_ColorSpace(){
        //Add error handler here
        System.out.println("Executing ColorSpaces...");
        boolean done = false;
        while (!done){
            switch (state){
                case HSV:{
                    System.out.println("\tConverting img to HSV...");
                    Imgproc.cvtColor(imageData.getImgOrig(), imgHSV, Imgproc.COLOR_BGR2HSV);
                    state = GRAY;
                }break;
                case GRAY:{
                    System.out.println("\tConverting img to GRAY...");
                    Imgproc.cvtColor(imageData.getImgOrig(), imgGrayScale, Imgproc.COLOR_BGR2GRAY);
                    state = LAB;
                }break;
                case LAB:{
                    System.out.println("\tConverting img to LAB...");
                    Imgproc.cvtColor(imageData.getImgOrig(), imgLab, Imgproc.COLOR_BGR2Lab);
                    done = true;
                }break;
            }
        }
    }//execute_ColorSpace

    public void setInfo(int[] info) {
        this.info = info;
    }

    public int[] getInfo() {
        return info;
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
