package ip_manager;

import java.awt.*;

/**
 * Created by clientrace on 11/26/16.
 */
public abstract class ImgProcessor {
    protected ImageData imageData;

    public void init(ImageData imageData){
        this.imageData = imageData;
    }
    public abstract void execute();
    public abstract void destroy();
    public ImageData getImageData(){
        return  imageData;
    }

}
