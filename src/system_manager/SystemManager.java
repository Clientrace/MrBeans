package system_manager;

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
    private int info[];

    private IPManager ipManager;
    private InputManCLI inputManCLI;

    public void init_SystemManager(){
        System.out.println("Initializing System Manager...");
        info = new int[6];
        inputManCLI = new InputManCLI();
        ipManager = new IPManager(inputManCLI.getImgOrig());
        inputManCLI.init_InputManager();
        ipManager.init_IPManager();
        state = INPUT;
    }//init_SystemManager

    public void execute_SystemManager(){
        while(true){
            switch (state){
                case INPUT:{
                    inputManCLI.execute_InputManager();
                }break;

                case IMG_PROC:{
                    ipManager.execute_IPManager();
                }break;
            }
        }
    }//execute_SystemManager
}
