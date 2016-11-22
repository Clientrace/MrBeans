package ip_manager;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by clientrace on 11/21/16.
 */
public class BackgroundSubtraction {

    //to be renamed:
    private Mat imgRaw;
    private Mat bgsOutput;
    private int info[] = new int[6];

    //to be changed:
    private double THRESH_LOW[];
    private double THRESH_HIGH[];

    public BackgroundSubtraction(){
        this.imgRaw = imgRaw;
    }//BackgroundSubtraction

    public void init_BackgroundSubtraction(){
        
    }//init_BackgroundSubtraction

    public void execute_BackgroundSubtraction(){

    }//execute_BackgroundSubtraction

    public Mat getBgsOutput(){
        return bgsOutput;
    }//getBgsOutput

    public void setInfo(int[] info){
        this.info = info;
    }//setInfo

    public int[] getInfo(){
        return info;
    }//getInfo

}
