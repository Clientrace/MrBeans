package ip_manager;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.awt.*;

/**
 * Created by clientrace on 11/21/16.
 */
public class BackgroundSubtraction {

    //to be renamed:
    private Mat bgsOutput;
    private ColorSpace colorSpace;
    private int info[] = new int[6];

    public void init_BackgroundSubtraction(ColorSpace colorSpace){
        System.out.println("Initializing Background Subtraction...");
        this.colorSpace = colorSpace;
        bgsOutput = new Mat();
    }//init_BackgroundSubtraction

    public void execute_BackgroundSubtraction(){
        System.out.println("Executing Background Subtraction...");
        Core.inRange(colorSpace.getImgHSV(),IPManager.THRESH_LOW,IPManager.THRESH_HIGH,bgsOutput);
        Core.bitwise_not(bgsOutput,bgsOutput);
    }//execute_BackgroundSubtraction

    public Mat getBgsOutput(){
        return bgsOutput;
    }

}
