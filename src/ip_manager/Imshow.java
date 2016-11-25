package ip_manager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by clientrace on 8/26/16.
 */

//shows analyzer output
public class Imshow extends JFrame{

    private BufferedImage display;


    /**
     * Used to display Mat on a swing window frame. With default 800 x 600 window size
     * @param display
     * @param title
     */
    public Imshow(BufferedImage display, String title){
        this.display = display;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);
        setTitle(title);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * used to display Mat on a swing window frame.
     * @param display
     * @param title
     * @param w
     * @param h
     */
    public Imshow(BufferedImage display, String title, int w, int h){
        setFocusableWindowState(false);
        this.display = display;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(w,h);
        setTitle(title);
        setVisible(true);
        setLocationRelativeTo(null);
    }



    public Imshow(BufferedImage display){
        this.display = display;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);
        setTitle("diplay");
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void paint(Graphics g) {
        g.drawImage(display, 0, 0, this.getWidth(), this.getHeight(), null);
    }


}
