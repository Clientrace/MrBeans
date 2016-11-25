package ip_manager;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by clientrace on 11/21/16.
 */
public class ColorSpace extends ImgProcessor{

    public static final int HSV = 0;
    public static final int GRAY = 1;
    public static final int LAB = 2;
    public static final int VAL = 3;
    public static int state;

    private IPManager ipManager;

    private Mat imgHSV;
    private Mat imgGrayScale;
    private Mat imgLab;

    public void init(IPManager ipManager) {
        System.out.println("Initializing ColorSpaces...");
        this.ipManager = ipManager;
        state = HSV;
        imgHSV = new Mat();
        imgGrayScale = new Mat();
        imgLab = new Mat();
    }//init_ColorSpace

    public void execute(){
        //Add error handler here
        System.out.println("Executing ColorSpaces...");
        boolean done = false;
        while (!done){
            switch (state){
                case HSV:{
                    System.out.println("\tConverting img to HSV...");
                    Imgproc.cvtColor(ipManager.imageData.getImgOrig(), imgHSV, Imgproc.COLOR_BGR2HSV);
                    state = GRAY;
                }break;
                case GRAY:{
                    System.out.println("\tConverting img to GRAY...");
                    Imgproc.cvtColor(ipManager.imageData.getImgOrig(), imgGrayScale, Imgproc.COLOR_BGR2GRAY);
                    state = LAB;
                }break;
                case LAB:{
                    System.out.println("\tConverting img to LAB...");
                    Imgproc.cvtColor(ipManager.imageData.getImgOrig(), imgLab, Imgproc.COLOR_BGR2Lab);
                    state = VAL;
                }break;
                case VAL:{
                    System.out.println("\tCollecting output...");
                    ipManager.imageData.setImgHSV(imgHSV);
                    ipManager.imageData.setImgGRAY(imgGrayScale);
                    ipManager.imageData.setImgLAB(imgLab);
                    done = true;
                }break;
            }
        }
    }//execute_ColorSpace

    public void destroy(){

    }
}
