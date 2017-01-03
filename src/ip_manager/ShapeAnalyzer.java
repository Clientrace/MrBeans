package ip_manager;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by clientrace on 11/28/16.
 */
public class ShapeAnalyzer {

    public static final int INNER = 0;
    public static final int HULL = 1;
    public static final int ELLIPTICALITY = 2;

    private int state;
    private ArrayList<CoffeeBean> coffeeBean;

    public void init(){
        state = INNER;
    }

    public void execute(){
        boolean done = false;
        while(!done){
            switch (state){
                case INNER:{
                    for(int i=0;i<coffeeBean.size();i++){
                        Mat img = coffeeBean.get(i).getBeanImg().clone();
                        Imgproc.cvtColor(img,img,Imgproc.COLOR_BGR2GRAY);
                        Imgproc.threshold(img,img,0,55,Imgproc.THRESH_BINARY);
                        Imgcodecs.imwrite("test"+i+".png",img);
                    }
                    state = HULL;
                }
                case HULL:{
                    state = ELLIPTICALITY;
                }
                case ELLIPTICALITY:{
                    done = true;
                }
            }
        }
    }

    public void destroy(){

    }

    public void setCoffeeBean(ArrayList<CoffeeBean> coffeeBean){
        this.coffeeBean = coffeeBean;
    }

    public ArrayList<CoffeeBean> getCoffeeBean(){
        return coffeeBean;
    }

}