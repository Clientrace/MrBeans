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

    private Mat bgsOutput;
    private IPManager ipManager;
    private int info[] = new int[6];

    public void init(IPManager ipManager){
        System.out.println("Initializing Background Subtraction...");
        this.ipManager = ipManager;
        state = THRESHOLD;
        bgsOutput = new Mat();
    }//init_BackgroundSubtraction

    public void execute(){
        System.out.println("Executing Background Subtraction...");
        boolean done = false;
        while(!done){
            switch (state){
                case THRESHOLD: {
                    System.out.println("\tApplying Thresholding...");
                    Core.inRange(ipManager.imageData.getImgHSV(),IPManager.THRESH_LOW,IPManager.THRESH_HIGH,bgsOutput);
                    state = BINARIZE;
                }
                break;
                case BINARIZE: {
                    System.out.println("\tApplying Binarization...");
                    Core.bitwise_not(bgsOutput,bgsOutput);
                    ipManager.imageData.setBgsOutput(bgsOutput);
                    done = true;
                }break;
            }
        }
    }//execute_BackgroundSubtraction

    public void destroy(){

    }

    public Mat getBgsOutput(){
        return bgsOutput;
    }

}
