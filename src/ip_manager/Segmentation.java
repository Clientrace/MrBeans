package ip_manager;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.awt.*;

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
    private Mat dist;
    private Mat sureBg;
    private Mat bin;

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
                    System.out.println("\tApplying Distance Transform...");
                    bin = ipManager.imageData.getBgsOutput();
                    dist = new Mat(bin.size(), CvType.CV_8UC1);
                    sureBg = new Mat(bin.size(), CvType.CV_8UC1);

                    Imgproc.distanceTransform(bin, dist, Imgproc.CV_DIST_L2, Imgproc.CV_DIST_MASK_PRECISE);
                    Core.normalize(dist, dist, 1 ,255, Core.NORM_MINMAX);
                    dist.convertTo(dist, CvType.CV_8UC1);

                    Mat temp = dist.clone();
                    Mat dest;

                    int prevcon;
                    int curon;
                    int count;

                    for(count = 0; 255 - count * 15 > 0; count++){
                        int total = Math.abs(255-count*15);
                        Imgproc.threshold(temp, dest, 255 - count * 15, 255, Imgproc.THRESH_BINARY);

                    }

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

    public void destroy(){

    }
}
