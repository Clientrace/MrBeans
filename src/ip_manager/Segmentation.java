package ip_manager;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by clientrace on 11/21/16.
 */
public class Segmentation extends ImgProcessor{

    public static final int DIST = 0;
    public static final int MARKERS = 1;
    public static final int WATERSHED = 2;
    public static final int CONTOURS = 3;
    public static int state;

    private IPManager ipManager;
    private Mat bin;
    private Mat marker;
    private ArrayList<MatOf>

    public void init(IPManager ipManager){
        System.out.println("Initializing Segmentation...");
        this.ipManager = ipManager;
        state = MARKERS;
    }//init_Segmentation


    //execute Segmentation
    public void execute(){
        System.out.println("Executing Segmentation...");
        boolean done = false;
        while (!done){
            switch (state){
                case DIST:{
                    state = MARKERS;
                }
                case MARKERS:{
                    System.out.println("\tGetting watershed markers...");
                    bin = ipManager.imageData.getBgsOutput();
                    marker = new Mat(bin.size(), CvType.CV_32SC1);
                    for(int i=0; i < marker.cols(); i++){
                        for(int j=0; j < marker.rows(); j++){
                            marker.put(j, i, 1);
                        }
                    }
                    state = WATERSHED;
                }break;
                case WATERSHED:{
                    System.out.println("\tApplying watershed...");
                    Imgproc.watershed(bin,marker);
                    state = CONTOURS;
                }break;
                case CONTOURS:{
                    System.out.println("\tGetting contours...");

                    done = true;
                }break;
            }
        }
    }//execute_Segmentation

    public void destroy(){

    }
}
