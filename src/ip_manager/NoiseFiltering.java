package ip_manager;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

/**
 * Created by clientrace on 11/26/16.
 */
public class NoiseFiltering extends ImgProcessor{

    public static final int BLUR = 0;
    public static final int MORPH = 1;

    public static int state;

    private Mat inputImg;
    private Mat outputImg;
    private ArrayList<MatOfPoint> inputContours;
    private ArrayList<MatOfPoint> outputContours;

    private boolean done;

    public void init(){
        System.out.println("\tInitializing Noise Filtering...");
        state = BLUR;
        inputImg = new Mat();
        outputImg = new Mat();
        inputContours = new ArrayList<>();
        outputContours = new ArrayList<>();
        done = false;
    }

    public void execute(){
        while(!done){
            switch (state){
                case BLUR:{
                    System.out.println("\tApplying Blur Functions...");
                    Imgproc.medianBlur(inputImg,inputImg,9);
                    state = MORPH;
                }break;
                case MORPH:{
                    System.out.println("\tApplying Morphological Functions...");
                    Imgproc.erode(inputImg,inputImg,new Mat());
                    Imgproc.dilate(inputImg,inputImg,new Mat());
                    outputImg = inputImg;
                    done = true;
                }break;
            }
        }
    }

    public void destroy(){

    }

    public void setInput(Mat input){
        this.inputImg = input;
    }

    public Mat getOutput(){
        return outputImg;
    }


}
