package ip_manager;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.awt.*;

/**
 * Created by clientrace on 11/21/16.
 */
public class IPManager {

    //Default values:
    public static final Scalar THRESH_LOW = new Scalar(70,130,50);
    public static final Scalar THRESH_HIGH = new Scalar(140,255,255);
    public static Mat procImg;

    private int[] info;
    private final int INIT = 0;
    private final int COLOR_SPACE = 1;
    private final int BGSUBTRACTION = 2;
    private final int SEGMENTATION = 3;
    private final int NOISEFILTER = 4;

    private ColorSpace colorSpace;
    private BackgroundSubtraction backgroundSubtraction;
    private Segmentation segmentation;
    private NoiseFiltering noiseFiltering;

    private Mat imgOrig;
    private int state;

    public void init_IPManager(){
        System.out.println("Initializing IPManager...");
        info = new int[6];
        state = INIT;
        colorSpace = new ColorSpace();
        backgroundSubtraction = new BackgroundSubtraction();
        segmentation = new Segmentation();
        noiseFiltering = new NoiseFiltering();
    }//init_IPManager

    public void execute_IPManager(){
        System.out.println("Executing IPManager...");
        boolean done = false;
        while(!done){
            switch (state){
                case INIT:{
                    procImg = imgOrig;
                    colorSpace.init_ColorSpace(imgOrig);
                    backgroundSubtraction.init_BackgroundSubtraction(colorSpace);
                    segmentation.init_Segmentation(colorSpace);
                    noiseFiltering.init_NoiseFiltering(colorSpace);
                    state = COLOR_SPACE;
                }break;
                case COLOR_SPACE:{
                    colorSpace.execute_ColorSpace();
                    state = BGSUBTRACTION;
                }break;
                case BGSUBTRACTION:{
                    backgroundSubtraction.execute_BackgroundSubtraction();
                    procImg = backgroundSubtraction.getBgsOutput();
                    state = SEGMENTATION;
                }break;

                case SEGMENTATION:{
                    segmentation.execute_Segmentation();
                    state = NOISEFILTER;
                }break;

                case NOISEFILTER:{
                    done = true;
                }break;

            }
        }
    }//execute_IPManager

    public void setImgOrig(Mat imgOrig){
        this.imgOrig = imgOrig;
    }
}
