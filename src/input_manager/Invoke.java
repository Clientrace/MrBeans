package input_manager;

import ip_manager.Imshow;
import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
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
            System.out.println("\tPath set to: "+selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    public static void showImg(Mat img){
        new Imshow(matToBuff(img));
    }

    public static BufferedImage matToBuff(Mat img){
        int type = 0;
        if (img.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        }
        else if (img.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(img.width(),img.height(),type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        img.get(0, 0,data);
        return image;
    }

    public static boolean hasStringVal(String[] group, String val){
        if(group==null)
            return false;
        for(int i=0;i<group.length;i++){
            if(val.trim().equals(group[i].trim()))
                return true;
        }
        return false;
    }

}
