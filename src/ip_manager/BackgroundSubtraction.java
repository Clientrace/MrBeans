package ip_manager;

import org.opencv.core.Core;
import org.opencv.core.Mat;


/**
 * Created by clientrace on 11/21/16.
 */
public class BackgroundSubtraction extends ImgProcessor{

    public static final int THRESHOLD = 0;
    public static final int BINARIZE = 1;
    public static int state;

    private Mat input;
    private Mat output;

    private boolean done;

    public void init(){
        System.out.println("\tInitializing Background Subtraction...");
        state = THRESHOLD;
        input = new Mat();
        output = new Mat();
        done = false;
    }

    public void execute(){
        while(!done){
            switch (state){
                case THRESHOLD: {
                    System.out.println("\tApplying Thresholding...");
                    Core.inRange(input,IPManager.THRESH_LOW,IPManager.THRESH_HIGH,output);
                    state = BINARIZE;
                }
                break;
                case BINARIZE: {
                    System.out.println("\tApplying Binarization...");
                    Core.bitwise_not(output,output);
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
