package ip_manager;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

/**
 * Created by clientrace on 1/3/17.
 */
public class CoffeeBean {
    private MatOfPoint contour;
    private Mat cropped;
    private Mat beanImg;

    //Shape:
    private double convexHullWidth;
    private double convexHullHeight;
    private int innerContourCount;
    private double ellipticality;

    //SieveAnalyzer:
    private double width;
    private double height;
    private int pixelCount;

    //Color Analyzer
    private double[] averageColor;

    public CoffeeBean(MatOfPoint contour, Mat beanImg, Mat cropped){
        this.contour = contour;
        this.beanImg = beanImg;
        this.cropped = cropped;
    }

    public Mat getBeanImg() {
        return beanImg;
    }

    public void setBeanImg(Mat beanImg) {
        this.beanImg = beanImg;
    }

    public MatOfPoint getContour() {
        return contour;
    }

    public void setContour(MatOfPoint contour) {
        this.contour = contour;
    }

    public Mat getCropped() {
        return cropped;
    }

    public void setCropped(Mat cropped) {
        this.cropped = cropped;
    }

    public double getConvexHullWidth() {
        return convexHullWidth;
    }

    public void setConvexHullWidth(double convexHullWidth) {
        this.convexHullWidth = convexHullWidth;
    }

    public double getConvexHullHeight() {
        return convexHullHeight;
    }

    public void setConvexHullHeight(double convexHullHeight) {
        this.convexHullHeight = convexHullHeight;
    }

    public int getInnerContourCount() {
        return innerContourCount;
    }

    public void setInnerContourCount(int innerContourCount) {
        this.innerContourCount = innerContourCount;
    }

    public double getEllipticality() {
        return ellipticality;
    }

    public void setEllipticality(double ellipticality) {
        this.ellipticality = ellipticality;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getPixelCount() {
        return pixelCount;
    }

    public void setPixelCount(int pixelCount) {
        this.pixelCount = pixelCount;
    }

    public double[] getAverageColor() {
        return averageColor;
    }

    public void setAverageColor(double[] averageColor) {
        this.averageColor = averageColor;
    }
}
