package ip_manager;

import org.opencv.core.Mat;


/**
 * Created by clientrace on 11/26/16.
 */
public abstract class ImgProcessor {
    protected IPManager ipManager;

    public abstract void init(IPManager ipManager);
    public abstract void execute();
    public abstract void destroy();

}
