package system_manager;

import ann_manager.ANNManager;
import input_manager.InputManCLI;
import ip_manager.IPManager;

/**
 * Created by clientrace on 11/21/16.
 */
public class SystemManager {

    public static final int INPUT = 0;
    public static final int IMG_PROC = 1;
    public static final int ANN = 2;
    public static final int OUTPUT = 3;
    public static final int DISPLAY = 4;

    public static int state;

    public IPManager ipManager;
    public InputManCLI inputManCLI;
    public ANNManager annManager;

    public void init_SystemManager(){
        System.out.println("\tInitializing System Manager...");
        state = INPUT;

        inputManCLI = new InputManCLI();
        ipManager = new IPManager();
        annManager = new ANNManager();

        inputManCLI.init_InputManager(this);
        ipManager.init_IPManager();
        annManager.init_ANNManager();
    }//init_SystemManager

    public void execute_SystemManager(){
        while(true){
            switch (state){
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
