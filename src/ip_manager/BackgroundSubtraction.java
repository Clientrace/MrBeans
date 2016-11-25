package ip_manager;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * Created by clientrace on 11/21/16.
 */
public class BackgroundSubtraction {

    //to be renamed:
    private Mat imgOrig;
    private Mat bgsOutput;
    private int info[] = new int[6];

    public void init_BackgroundSubtraction(Mat imgOrig){
        System.out.println("Initializing Background Subtraction...");
        this.imgOrig = imgOrig.clone();
        bgsOutput = new Mat();
    }//init_BackgroundSubtraction

    public void execute_BackgroundSubtraction(){
        System.out.println("Executing Background Subtraction...");
        Core.inRange(imgOrig,IPManager.THRESH_LOW,IPManager.THRESH_HIGH,bgsOutput);
    }//execute_BackgroundSubtraction

    public Mat getBgsOutput(){
        return bgsOutput;
    }

}
