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
    public static int state;

    private Mat input;
    private Mat[] output;

    boolean done;

    public void init() {
        System.out.println("\tInitializing ColorSpaces...");
        state = HSV;
        done = false;
        input = new Mat();
        output = new Mat[3];
        for(int i=0; i<3; i++)
            output[i] = new Mat();
    }

    public void execute(){
        while (!done){
            switch (state){
                case HSV:{
                    System.out.println("\tConverting img to HSV...");
                    Imgproc.cvtColor(input, output[HSV], Imgproc.COLOR_BGR2HSV);
                    state = GRAY;
                }break;
                case GRAY:{
                    System.out.println("\tConverting img to GRAY...");
                    Imgproc.cvtColor(input, output[GRAY], Imgproc.COLOR_BGR2GRAY);
                    state = LAB;
                }break;
                case LAB:{
                    System.out.println("\tConverting img to LAB...");
                    Imgproc.cvtColor(input, output[LAB], Imgproc.COLOR_BGR2Lab);
                    done = true;
                }break;
            }
        }
    }

    public void destroy(){

    }

    public void setInput(Mat input){
        this.input = input;
    }

    public Mat[] getOutput(){
        return output;
    }
}
