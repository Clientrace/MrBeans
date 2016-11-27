package ip_manager;

import input_manager.Invoke;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

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
    private Mat bg;
    private Mat bin;
    private Mat dist;
    private Mat kernel;
    private Mat marker;
    private ArrayList<MatOfPoint> contours;

    public void init(IPManager ipManager){
        System.out.println("Initializing Segmentation...");
        this.ipManager = ipManager;
        state = DIST;
    }//init_Segmentation


    //execute Segmentation
    public void execute(){
        System.out.println("Executing Segmentation...");
        boolean done = false;
        while (!done){
            switch (state){
                case DIST:{
                    System.out.println("\tApplying distance transform...");
                    bin = ipManager.imageData.getBgsOutput().clone();
                    bg = new Mat(bin.size(),CvType.CV_8UC1);
                    kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3,3));
                    Imgproc.dilate(bin,bg,kernel);
                    state = MARKERS;
                }
                case MARKERS:{
                    System.out.println("\tGetting watershed markers...");
                    marker = new Mat(bin.size(), CvType.CV_32SC1);
                    for(int i=0;i<marker.cols();i++){
                        for(int j=0;j<marker.rows();j++){
                            marker.put(j,i,1);
                            if(bg.get(j,i)[0]!=0)
                                marker.put(j,i,0);
                        }
                    }
                    state = WATERSHED;
                }break;
                case WATERSHED:{
                    System.out.println("\tApplying watershed...");
                    Imgproc.cvtColor(bin,bin,Imgproc.COLOR_GRAY2BGR);
                    Imgproc.watershed(bin,marker);
                    ipManager.imageData.setWsOutput(bin);
                    Imgproc.cvtColor(bin,bin,Imgproc.COLOR_BGR2GRAY);
                    state = CONTOURS;
                }break;
                case CONTOURS:{
                    System.out.println("\tGetting contours...");
                    contours = new ArrayList<>();
                    bin.convertTo(bin,CvType.CV_8UC1);
                    Imgproc.findContours(bin, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
                    done = true;
                }break;
            }
        }
    }//execute_Segmentation

    public void destroy(){

    }
}
