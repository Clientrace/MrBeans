package input_manager;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import system_manager.SystemManager;

import java.util.Scanner;

/**
 * Created by clientrace on 11/22/16.
 */
public class InputManCLI {
    private Mat imgOrig;
    private String imgPath;

    public void init_InputManager(){
        System.out.println("Initializing Input Manager...");
        imgPath = new String();
    }

    public void execute_InputManager(){
        Scanner reader = new Scanner(System.in);
        String command;
        while(true){
            System.out.print("> ");
            command = reader.nextLine();
            switch (command){
                case "setpath":{
                    imgPath = Invoke.setPath();
                }break;
                case "exec":{
                    System.out.println("Executing...");
                    SystemManager.state = SystemManager.IMG_PROC;
                }break;
                case "exit":{
                    System.out.println("Terminating System...");
                    System.exit(0);
                }break;
            }
        }
    }

    public void destroy_InputManager(){

    }



    public Mat getImgOrig() {
        return imgOrig;
    }

    public void setImgOrig(Mat imgOrig) {
        this.imgOrig = imgOrig;
    }

}
