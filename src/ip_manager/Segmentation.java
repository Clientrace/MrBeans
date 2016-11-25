package ip_manager;

import org.opencv.core.Mat;

/**
 * Created by clientrace on 11/21/16.
 */
public class Segmentation {

    private ColorSpace colorSpace;

    public void init_Segmentation(ColorSpace colorSpace){
        System.out.println("Initializing Segmentation...");
        this.colorSpace = colorSpace;
    }//init_Segmentation


    //execute Segmentation
    public void execute_Segmentation(){
        System.out.println("Executing Segmentation...");
    }//execute_Segmentation
}
