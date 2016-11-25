package input_manager;

import org.opencv.core.Mat;

import javax.swing.*;
import java.io.File;

/**
 * Created by clientrace on 11/26/16.
 */
public class Invoke {


    public static String setPath(){
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Set Image");
        int res = fileChooser.showOpenDialog(null);
        if(res == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Path set to: "+selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        }
        return null;
    }


}
