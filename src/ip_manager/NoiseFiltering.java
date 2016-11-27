package ip_manager;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by clientrace on 11/26/16.
 */
public class NoiseFiltering extends ImgProcessor{

    public static final int BLUR = 0;
    public static final int MORPH = 1;

    public static int state;

    private Mat input;
    private Mat output;

    private boolean done;

    public void init(){
        System.out.println("\tInitializing Noise Filtering...");
        state = BLUR;
        input = new Mat();
        output = new Mat();
        done = false;
    }

    public void execute(){
        while(!done){
            switch (state){
                case BLUR:{
                    System.out.println("\tApplying Blur Functions...");
                    Imgproc.medianBlur(input, input,9);
                    state = MORPH;
                }break;
                case MORPH:{
                    System.out.println("\tApplying Morphological Functions...");
                    Imgproc.erode(input, input,new Mat());
                    Imgproc.dilate(input, input,new Mat());
                    output = input;
                    done = true;
                }break;
            }
        }
    }

    public void destroy(){
        input.release();
        output.release();
    }

    public void setInput(Mat input){
        this.input = input;
    }

    public Mat getOutput(){
        return output;
    }


}
