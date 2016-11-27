package ip_manager;

import org.opencv.core.*;
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
    public static final int CLEAN = 4;
    public static int state;

    private Mat input;
    private Mat output;

    private Mat bg;
    private Mat kernel;
    private Mat marker;
    private ArrayList<MatOfPoint> contours;

    private boolean done;

    public void init(){
        System.out.println("\tInitializing Segmentation...");
        contours = new ArrayList<>();
        bg = new Mat();
        input = new Mat();
        kernel = new Mat();
        marker = new Mat();
        done = false;
        state = DIST;
    }


    public void execute(){
        while (!done){
            switch (state){
                case DIST:{
                    System.out.println("\tApplying distance transform...");
                    bg = new Mat(input.size(),CvType.CV_8UC1);
                    kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(3,3));
                    Imgproc.dilate(input,bg,kernel);
                    state = MARKERS;
                }
                case MARKERS:{
                    System.out.println("\tGetting watershed markers...");
                    marker = new Mat(input.size(), CvType.CV_32SC1);
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
                    Imgproc.cvtColor(input,input,Imgproc.COLOR_GRAY2BGR);
                    Imgproc.watershed(input,marker);
                    Imgproc.cvtColor(input,input,Imgproc.COLOR_BGR2GRAY);
                    state = CONTOURS;
                }break;
                case CONTOURS:{
                    System.out.println("\tGetting contours...");
                    input.convertTo(input,CvType.CV_8UC1);
                    Imgproc.findContours(input, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
                    output = input;
                    state = CLEAN;
                }break;
                case CLEAN:{
                    System.out.println("\tCleaning contours...");
                    for(int i=0; i<contours.size(); i++){
                        if(contours.get(i).width()<IPManager.OBJ_WIDTH_THRESH ||
                                contours.get(i).height()<IPManager.OBJ_HEIGHT_THRESH)
                            contours.remove(i);
                    }
                    done = true;
                }
            }
        }
    }

    public void destroy(){
        input.release();
        output.release();
        bg.release();
        kernel.release();
        marker.release();

        for(int i =0; i<contours.size();i++)
            contours.get(i).release();
    }

    public void setInput(Mat input){
        this.input = input;
    }

    public Mat getOutput(){
        return  output;
    }

    public ArrayList<MatOfPoint> getContours(){
        return contours;
    }

}
