package ip_manager;

import org.opencv.core.Mat;

/**
 * Created by clientrace on 11/21/16.
 */
public class Segmentation {

    private Mat imgOrig;

    public void init_Segmentation(Mat imgOrig){
        this.imgOrig = imgOrig.clone();
    }//init_Segmentation


    //execute Segmentation
    public void execute_Segmentation(){

    }//execute_Segmentation
}
