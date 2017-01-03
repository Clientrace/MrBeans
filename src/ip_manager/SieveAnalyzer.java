package ip_manager;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

/**
 * Created by clientrace on 11/28/16.
 */
public class SieveAnalyzer extends ImgProcessor{

    public static final int SIZES = 0;
    public static final int COUNT = 1;


    private int state;
    private ArrayList<CoffeeBean> coffeeBean;

    public void init(){
    }

    public void execute(){
        boolean done = false;
        while(!done){
            switch (state){
                case SIZES:{
                    ArrayList<RotatedRect> boundElps = new ArrayList<>();
                    ArrayList <Point> mjr1 =  new ArrayList <Point>();
                    ArrayList <Point> mjr2 = new ArrayList <Point>();
                    ArrayList <Point> mnr1 = new ArrayList <Point>();
                    ArrayList <Point> mnr2 = new ArrayList <Point>();

                    for(int i = 0; i< coffeeBean.size(); i++){
                        MatOfPoint2f contour2f = new MatOfPoint2f(coffeeBean.get(i).getContour().toArray());
                        boundElps.add(Imgproc.fitEllipse(contour2f));
                        contour2f.release();

                        Point pnts[] = new Point[4];
                        boundElps.get(i).points(pnts);

                        mjr1.add(getMidPoint(pnts[0],pnts[3]));
                        mjr2.add(getMidPoint(pnts[1],pnts[2]));

                        mnr1.add(getMidPoint(pnts[0],pnts[1]));
                        mnr2.add(getMidPoint(pnts[2],pnts[3]));
                        coffeeBean.get(i).setWidth(getDistance(mnr1.get(i),mnr2.get(i)));
                        coffeeBean.get(i).setHeight(getDistance(mjr1.get(i),mjr2.get(i)));
                    }
                    state = COUNT;
                }
                case COUNT:{
                    for(int i = 0; i< coffeeBean.size(); i++){
                        coffeeBean.get(i).setPixelCount(Core.countNonZero(coffeeBean.get(i).getCropped()));
                    }
                    done = true;
                }
            }
        }
    }

    public void destroy(){

    }

    public double getDistance(Point p1, Point p2){
        double x1 = p1.x;
        double x2 = p2.x;
        double y1 = p1.y;
        double y2 = p2.y;
        double pp1 = Math.pow((x2-x1),2);
        double pp2 = Math.pow((y2-y1),2);
        return Math.sqrt(pp1+pp2);
    }

    public Point getMidPoint(Point p1, Point p2){
        double x1 = p1.x;
        double y1 = p1.y;
        double x2 = p2.x;
        double y2 = p2.y;
        return new Point(((x1 + x2)/2),((y1+y2)/2));
    }

    public void setCoffeeBean(ArrayList<CoffeeBean> coffeeBean){this.coffeeBean = coffeeBean;}

    public ArrayList<CoffeeBean> getCoffeeBean(){
        return coffeeBean;
    }
}
