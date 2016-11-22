package input_manager;

import org.opencv.core.Mat;

/**
 * Created by clientrace on 11/22/16.
 */
public class InputManager {
    private Mat imgOrig;

    public Mat getImgOrig() {
        return imgOrig;
    }

    public void setImgOrig(Mat imgOrig) {
        this.imgOrig = imgOrig;
    }
}
