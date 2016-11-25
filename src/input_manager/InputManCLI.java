package input_manager;

import ip_manager.IPManager;
import ip_manager.Imshow;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import system_manager.SystemManager;

import java.util.Scanner;

/**
 * Created by clientrace on 11/22/16.
 */
public class InputManCLI {
    private Mat imgOrig;
    private Mat procImg;
    private String imgPath;
    private SystemManager systemManager;

    public void init_InputManager(SystemManager systemManager){
        this.systemManager = systemManager;
        System.out.println("Initializing Input Manager...");
        imgPath = "path not set";
    }

    public void execute_InputManager(){
        Scanner reader = new Scanner(System.in);
        String options[];
        String command;
        boolean waitForInput = true;
        while(waitForInput){
            System.out.print("> ");
            options = reader.nextLine().split(" ");
            command = options[0];
            switch (command){
                case "setpath":{
                    imgPath = Invoke.setPath();
                    imgOrig = Imgcodecs.imread(imgPath);
                    systemManager.ipManager.setImgOrig(imgOrig);
                }break;
                case "path":{
                    System.out.println(imgPath);
                }break;
                case "exec":{
                    SystemManager.state = SystemManager.IMG_PROC;
                    waitForInput = false;
                }break;
                case "output":{
                    if(options.length>1){
                        if(Invoke.hasStringVal(options,"-bgs")) {
                            System.out.println("showing output...");
                            Mat output = systemManager.ipManager.getImageData().getBgsOutput();
                            new Imshow(Invoke.matToBuff(output));
                        }
                    }
                }break;
                case "exit": {
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
