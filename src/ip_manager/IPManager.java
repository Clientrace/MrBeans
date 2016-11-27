package ip_manager;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import system_manager.SystemManager;

/**
 * Created by clientrace on 11/21/16.
 */
public class IPManager {

    //Default values:
    public static final int RED = 0;
    public static final int BLUE = 1;
    public static final int GREEN = 2;

    public static final Scalar THRESH_LOW = new Scalar(70,130,50);
    public static final Scalar THRESH_HIGH = new Scalar(140,255,255);
    public static final double OBJ_WIDTH_THRESH = 30;
    public static final double OBJ_HEIGHT_THRESH = 30;
    public static final int CHROMA_KEY = BLUE;



    private int[] info;
    private final int INIT = 0;
    private final int COLOR_SPACE = 1;
    private final int BGSUBTRACTION = 2;
    private final int SEGMENTATION = 3;
    private final int NOISEFILTER = 4;
    private final int DESTROY = 5;
    private int state;

    private ColorSpace colorSpace;
    private BackgroundSubtraction backgroundSubtraction;
    private Segmentation segmentation;
    private NoiseFiltering noiseFiltering;

    public ImageData imageData;
    private Mat imgOrig;

    public void init_IPManager(){
        System.out.println("\tInitializing IPManager");
        info = new int[6];
        state = INIT;
        imageData = new ImageData();
        colorSpace = new ColorSpace();
        backgroundSubtraction = new BackgroundSubtraction();
        segmentation = new Segmentation();
        noiseFiltering = new NoiseFiltering();
    }

    public void execute_IPManager(){
        System.out.println("\tExecuting IPManager:");
        if(imgOrig==null) {
            System.out.println("\t[Err]: Null image input");
            return;
        }
        boolean done = false;
        while(!done){
            switch (state){
                case INIT:{
                    System.out.println("\tInitializing Image Processors...");
                    colorSpace.init();
                    backgroundSubtraction.init();
                    segmentation.init();
                    noiseFiltering.init();
                    state = COLOR_SPACE;
                }break;
                case COLOR_SPACE:{
                    System.out.println("\tExecuting ColorSpaces...");
                    colorSpace.setInput(imgOrig.clone());
                    colorSpace.execute();
                    imageData.setImgOrig(imgOrig);
                    imageData.setImgHSV(colorSpace.getOutput()[colorSpace.HSV]);
                    imageData.setImgGRAY(colorSpace.getOutput()[colorSpace.GRAY]);
                    imageData.setImgLAB(colorSpace.getOutput()[colorSpace.LAB]);
                    state = BGSUBTRACTION;
                }break;
                case BGSUBTRACTION:{
                    System.out.println("\tExecuting Background Subtraction...");
                    backgroundSubtraction.setInput(imageData.getImgHSV().clone());
                    backgroundSubtraction.execute();
                    imageData.setBgsOutput(backgroundSubtraction.getOutput().clone());
                    state = NOISEFILTER;
                }break;

                case NOISEFILTER:{
                    System.out.println("\tExecuting Pre Noise Filtering...");
                    noiseFiltering.setInput(imageData.getBgsOutput().clone());
                    noiseFiltering.execute();
                    imageData.setNfOutput(noiseFiltering.getOutput());
                    state = SEGMENTATION;
                }break;

                case SEGMENTATION:{
                    System.out.println("\tExecuting Segmentation...");
                    segmentation.setInput(imageData.getBgsOutput().clone());
                    segmentation.execute();
                    imageData.setWsOutput(segmentation.getOutput());
                    imageData.setContours(segmentation.getContours());
                    state = DESTROY;
                }break;

                case DESTROY:{
                    System.out.println("\tDestroying Processors...");
                    backgroundSubtraction.destroy();
                    segmentation.destroy();
                    noiseFiltering.destroy();
                    done = true;
                }

            }
        }
    }

    public void setImgOrig(Mat imgOrig){this.imgOrig = imgOrig;}

    public ImageData getImageData(){
        return imageData;
    }

}
