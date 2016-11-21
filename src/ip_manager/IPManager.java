package ip_manager;

/**
 * Created by clientrace on 11/21/16.
 */
public class IPManager {

    private int[] info;
    private final int BGSUBTRACTION = 0;
    private final int SEGMENTATION = 1;
    private final int NOISEFILTER = 2;

    private int state;

    public IPManager(){

    }//IPManager

    public void init_IPManager(){
        info = new int[6];
        state = SEGMENTATION;
    }//init_IPManager

    public void execute_IPManager(){
        while(true){
            switch (state){
                case BGSUBTRACTION:{

                }break;
                case SEGMENTATION:{

                }break;
            }
        }
    }//execute_IPManager
}
