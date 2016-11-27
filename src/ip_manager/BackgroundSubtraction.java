package ip_manager;

import org.opencv.core.Core;
import org.opencv.core.Mat;


/**
 * Created by clientrace on 11/21/16.
 */
public class BackgroundSubtraction extends ImgProcessor{

    public static final int THRESHOLD = 0;
    public static final int CHROMA_KEY = 1;
    public static final int BINARIZE = 2;
    public static int state;

    private Mat input;
    private Mat output;

    private boolean done;

    public void init(){
        System.out.println("\tInitializing Background Subtraction...");
        state = THRESHOLD;
        input = new Mat();
        output = new Mat();
        done = false;
    }

    public void execute(){
        while(!done){
            switch (state){
                case THRESHOLD: {
                    System.out.println("\tApplying Thresholding...");
                    Core.inRange(input,IPManager.THRESH_LOW,IPManager.THRESH_HIGH,output);
                    state = BINARIZE;
                }
                break;
                case BINARIZE: {
                    System.out.println("\tApplying Binarization...");
                    Core.bitwise_not(output,output);
                    state = CHROMA_KEY;
                }break;
                case CHROMA_KEY:{
                    System.out.println("\t Removing unwanted pixels...");
                    for(int i=0; i<output.rows(); i++){
                        for(int j=0; j<output.cols(); j++){
                            double[] bgr = input.get(i,j);
                            switch (IPManager.CHROMA_KEY){
                                case IPManager.BLUE:{
                                    if(bgr[0]>bgr[1] && bgr[0]>bgr[2]){
                                        if(bgr[1]<20 && bgr[2]<20)
                                            output.put(i,j,0);
                                    }
                                }break;
                                case IPManager.GREEN:{
                                    if(bgr[1]>bgr[2] && bgr[1]>bgr[0]){
                                        if(bgr[0]<20 && bgr[2]<20)
                                            output.put(i,j,0);
                                    }
                                }break;
                                case IPManager.RED:{
                                    if(bgr[2]>bgr[1] && bgr[2]>bgr[1]){
                                        if(bgr[1]<20 && bgr[0]<20)
                                            output.put(i,j,0);
                                    }
                                }break;
                            }
                        }
                    }
                    done = true;
                }
            }
        }
    }

    public void destroy(){
        input.release();
        output.release();
    }

    public void setInput(Mat input){
        this.input = input;
    }

    public Mat getOutput(){
        return output;
    }

}
