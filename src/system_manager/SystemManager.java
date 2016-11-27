package system_manager;

import ann_manager.ANNManager;
import input_manager.InputManCLI;
import ip_manager.IPManager;

/**
 * Created by clientrace on 11/21/16.
 */
public class SystemManager {

    public static final int INIT = 0;
    public static final int INPUT = 1;
    public static final int IMG_PROC = 2;
    public static final int ANN = 3;
    public static final int OUTPUT = 4;
    public static final int DISPLAY = 5;

    public static int state;
    private int info[];

    public IPManager ipManager;
    public InputManCLI inputManCLI;
    public ANNManager annManager;

    public void init_SystemManager(){
        System.out.println("Initializing System Manager...");
        state = INIT;
        info = new int[6];
        inputManCLI = new InputManCLI();
        ipManager = new IPManager();
        annManager = new ANNManager();
    }//init_SystemManager

    public void execute_SystemManager(){
        while(true){
            switch (state){
                case INIT:{
                    inputManCLI.init_InputManager(this);
                    ipManager.init_IPManager();
                    annManager.init_ANNManager();
                    state = INPUT;
                }break;

                case INPUT:{
                    inputManCLI.execute_InputManager();
                }break;

                case IMG_PROC:{
                    ipManager.setImgOrig(inputManCLI.getImgOrig());
                    ipManager.execute_IPManager();
                    state = ANN;
                }break;

                case ANN:{
                    state = DISPLAY;
                }break;

                case DISPLAY:{
                    state = INPUT;
                }
            }
        }
    }//execute_SystemManager
}
