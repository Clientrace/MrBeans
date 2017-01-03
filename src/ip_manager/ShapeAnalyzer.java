package ip_manager;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by clientrace on 11/28/16.
 */
public class ShapeAnalyzer {

    public static final int HULL = 0;
    public static final int ELLIPTICALITY = 1;

    private int state;
    private ArrayList<CoffeeBean> coffeeBean;

    public void init(){
        state = HULL;
    }

    public void execute(){
        boolean done = false;
        while(!done){
            switch (state){
                case HULL:{
                    state = ELLIPTICALITY;
                    for(int i=0;i<coffeeBean.size();i++){
                        MatOfInt hull = new MatOfInt();
                        Imgproc.convexHull(coffeeBean.get(i).getContour(),hull);
                        Size s = hull.size();
                        double w = hull.width();
                        double h = hull.height();
                        coffeeBean.get(i).setConvexHullWidth(w);
                        coffeeBean.get(i).setConvexHullHeight(h);
                    }
                    state = ELLIPTICALITY;
                }
                case ELLIPTICALITY:{
                    for(int i=0;i<coffeeBean.size();i++){
                        MatOfPoint2f contours2f = new MatOfPoint2f(coffeeBean.get(i).getContour());
                        double perimeter = Imgproc.arcLength(contours2f,true);
                        double area = Imgproc.contourArea(coffeeBean.get(i).getContour());
                        double ellipticality = (4*area*Math.PI)/(float)(Math.pow(perimeter,2));
                        coffeeBean.get(i).setEllipticality(ellipticality);
                    }
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