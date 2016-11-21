package system_manager;

/**
 * Created by clientrace on 11/21/16.
 */
public class SystemManager {

    private final static int INITIALIZATION = 0;
    private final static int INPUT = 1;
    private final static int IMG_PROC = 2;
    private final static int ANN = 3;
    private final static int OUTPUT = 4;
    private final static int DISPLAY = 5;

    private static int state;

    public static void main(String[] args){
        state = INITIALIZATION;

        while(true){
            switch (state){
                case INITIALIZATION:{

                }break;

                case INPUT:{

                }break;

                case IMG_PROC:{

                }break;
            }
        }
    }
}
