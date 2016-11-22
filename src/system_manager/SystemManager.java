package system_manager;

import input_manager.InputManager;
import ip_manager.IPManager;

/**
 * Created by clientrace on 11/21/16.
 */
public class SystemManager {

    private final  int INITIALIZATION = 0;
    private final int INPUT = 1;
    private final int IMG_PROC = 2;
    private final int ANN = 3;
    private final int OUTPUT = 4;
    private final int DISPLAY = 5;

    private int state;
    private int info[];

    private IPManager ipManager;
    private InputManager inputManager;

    public void init_SystemManager(){

        info = new int[6];
        state = INITIALIZATION;
        inputManager = new InputManager();
        ipManager = new IPManager(inputManager.getImgOrig());
    }//init_SystemManager

    public void execute_SystemManager(){
        while(true){
            switch (state){
                case INITIALIZATION:{
                    ipManager.init_IPManager();
                }break;

                case INPUT:{

                }break;

                case IMG_PROC:{
                    ipManager.execute_IPManager();
                }break;
            }
        }
    }//execute_SystemManager
}
