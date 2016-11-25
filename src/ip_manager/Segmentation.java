package ip_manager;

import org.opencv.core.Mat;

import java.awt.*;

/**
 * Created by clientrace on 11/21/16.
 */
public class Segmentation {

    public static final int DIST = 0;
    public static final int MARKERS = 1;
    public static final int WATERSHED = 2;
    public static final int CONTOURS = 3;
    public static int state;

    private ImageData imageData;
    private Mat dist;

    public void init_Segmentation(ImageData imageData){
        System.out.println("Initializing Segmentation...");
        state = MARKERS;
        this.imageData = imageData;
    }//init_Segmentation


    //execute Segmentation
    public void execute_Segmentation(){
        System.out.println("Executing Segmentation...");
        boolean done = false;
        while (!done){
            switch (state){
                case DIST:{
                    System.out.println("\tApplying Distance Transform...");
                    dist = new Mat();
                    state = MARKERS;
                }
                case MARKERS:{
                    System.out.println("\tGetting watershed markers...");
                    state = WATERSHED;
                }break;
                case WATERSHED:{
                    System.out.println("\tApplying watershed...");
                    state = CONTOURS;
                }break;
                case CONTOURS:{
                    System.out.println("\tGetting contours...");
                    done = true;
                }break;
            }
        }
    }//execute_Segmentation
}
