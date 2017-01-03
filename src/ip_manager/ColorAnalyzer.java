package ip_manager;

import org.opencv.core.Mat;

import java.util.ArrayList;

/**
 * Created by clientrace on 11/28/16.
 */
public class ColorAnalyzer {
    public static final int AVE_COLOR = 0;
    public static final int HUE_SAT = 1;
    public static final int COLOR_PERCENTAGES = 2;

    private int state;
    private ArrayList<CoffeeBean> coffeeBean;

    public void init(){
        state = AVE_COLOR;
    }

    public void execute(){
        boolean done = false;
        while(!done){
            switch (state){
                case AVE_COLOR:{
                    for(int i=0;i<coffeeBean.size();i++){
                        int count = 0;
                        double ret[] = new double[3];
                        Mat img = coffeeBean.get(i).getBeanImg();
                        for(int j=0; j<img.rows();j++){
                            for(int k=0;k<img.cols();k++){
                                double[] bgr = img.get(j,k);
                                ret[0]+=bgr[0];
                                ret[1]+=bgr[1];
                                ret[2]+=bgr[2];
                                count++;
                            }
                        }
                        ret[0]/=count;
                        ret[1]/=count;
                        ret[2]/=count;
                        coffeeBean.get(i).setAverageColor(ret);
                    }
                    state = HUE_SAT;
                }break;
                case HUE_SAT:{

                }break;
            }
        }
    }

}
