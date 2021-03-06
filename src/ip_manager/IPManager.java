package ip_manager;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import system_manager.SystemManager;

import java.util.ArrayList;

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
    private final int SIEVE = 5;
    private final int SHAPE = 6;
    private final int COLOR = 8;
    private final int DESTROY = 9;
    private int state;

    private ArrayList<CoffeeBean> coffeeBean;
    private ColorSpace colorSpace;
    private BackgroundSubtraction backgroundSubtraction;
    private Segmentation segmentation;
    private NoiseFiltering noiseFiltering;
    private SieveAnalyzer sieveAnalyzer;
    private ShapeAnalyzer shapeAnalyzer;
    private ColorAnalyzer colorAnalyzer;

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
        sieveAnalyzer = new SieveAnalyzer();
        shapeAnalyzer = new ShapeAnalyzer();
        colorAnalyzer = new ColorAnalyzer();
        coffeeBean = new ArrayList<>();
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
                    for(int i=0;i<imageData.getContours().size();i++) {
                        Rect r = Imgproc.boundingRect(imageData.getContours().get(i));
                        Mat img =   imgOrig.submat(r);
                        Mat cimg = imageData.getWsOutput().submat(r);
                        coffeeBean.add(new CoffeeBean(imageData.getContours().get(i),img,cimg));
                    }
                    Imgcodecs.imwrite("res/imgproc/ws.png",imageData.getWsOutput());
                    state = SIEVE;
                }break;

                case SIEVE:{
                    System.out.println("\tExecuting Sieve Analyzer...");
                    sieveAnalyzer.setCoffeeBean(coffeeBean);
                    sieveAnalyzer.init();
                    sieveAnalyzer.execute();
                    coffeeBean = sieveAnalyzer.getCoffeeBean();
                    state = SHAPE;

                }break;

                case SHAPE:{
                    System.out.println("\tExecuting Shape Analyzer...");
                    shapeAnalyzer.setCoffeeBean(coffeeBean);
                    shapeAnalyzer.init();
                    shapeAnalyzer.execute();
                    coffeeBean = shapeAnalyzer.getCoffeeBean();
                    state = DESTROY;
                }

                case DESTROY:{
                    //print coffee bean data:
                    for(int i=0;i<coffeeBean.size();i++){
                        System.out.println("No. "+i);
                        System.out.println("width: "+coffeeBean.get(i).getWidth());
                        System.out.println("height: "+coffeeBean.get(i).getHeight());
                        System.out.println("pixelCount: "+coffeeBean.get(i).getPixelCount());
                        System.out.println("hull width: "+coffeeBean.get(i).getConvexHullWidth());
                        System.out.println("hull height: "+coffeeBean.get(i).getConvexHullHeight());
                        System.out.println("ellipticality: "+coffeeBean.get(i).getEllipticality()+"\n");
                    }
                    System.out.println("\tDestroying Processors...");
                    backgroundSubtraction.destroy();
                    segmentation.destroy();
                    noiseFiltering.destroy();
                    sieveAnalyzer.destroy();
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
