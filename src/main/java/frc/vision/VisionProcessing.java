/*---------------------------------------------------------------------------------------*/                                                                            
/*            66666666       222222222222222         1111111       333333333333333       */
/*           6::::::6       2:::::::::::::::22      1::::::1      3:::::::::::::::33     */
/*          6::::::6        2::::::222222:::::2    1:::::::1      3::::::33333::::::3    */
/*         6::::::6         2222222     2:::::2    111:::::1      3333333     3:::::3    */
/*        6::::::6                      2:::::2       1::::1                  3:::::3    */
/*       6::::::6                       2:::::2       1::::1                  3:::::3    */
/*      6::::::6                     2222::::2        1::::1          33333333:::::3     */
/*     6::::::::66666           22222::::::22         1::::l          3:::::::::::3      */
/*    6::::::::::::::66       22::::::::222           1::::l          33333333:::::3     */
/*    6::::::66666:::::6     2:::::22222              1::::l                  3:::::3    */
/*    6:::::6     6:::::6   2:::::2                   1::::l                  3:::::3    */
/*    6:::::6     6:::::6   2:::::2                   1::::l                  3:::::3    */
/*    6::::::66666::::::6   2:::::2       222222   111::::::111   3333333     3:::::3    */
/*     66:::::::::::::66    2::::::2222222:::::2   1::::::::::1   3::::::33333::::::3    */
/*       66:::::::::66      2::::::::::::::::::2   1::::::::::1   3:::::::::::::::33     */
/*         666666666        22222222222222222222   111111111111    333333333333333       */
/*                                                                                    ©  */
/*---------------------------------------------------------------------------------------*/

package frc.vision;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * When tracking reflective tape, it is best to use bright blue leds.
 * Please refrain from using anything else.
 * 
 */
public class VisionProcessing {
    int IMG_HEIGHT;
    int IMG_WIDTH;
    UsbCamera cam1;
    UsbCamera cam2;
    double centerX = 0.0;
    String pipe;
    VisionThread visionThread;
    final Object imgLock = new Object();


	public VisionProcessing(int IMG_WIDTH, int IMG_HEIGHT, String pipe){
        this.IMG_HEIGHT = IMG_HEIGHT;
        this.IMG_WIDTH = IMG_WIDTH;
        this.pipe = pipe;

    }

    public void startCapture(){
        cam1 = CameraServer.getInstance().startAutomaticCapture(0);
        cam1.setResolution(IMG_WIDTH, IMG_HEIGHT);
    }

    public void visionInit(){
        startCapture();

        switch(pipe){
            case "BallVisionTracking":
                ballTracking();
                break;
            case "ReflTapeTracking":
                tapeTracking();
                break;
            default:
                System.out.println("ERROR: INVALID PIPELINE");
                break;
        }  
    }
    
    public void ballTracking(){
        visionThread = new VisionThread(cam1, new BallVisionTracking(), pipeline ->{
            System.out.println("Creating vision thread for " + pipe);
                if (!pipeline.findBlobsOutput().empty()){
                    Rect r = Imgproc.boundingRect(pipeline.findBlobsOutput());
                    synchronized (imgLock) {
                        centerX = r.x + (r.width / 2);
                    }
                }else if(pipeline.findBlobsOutput().empty()){
                    System.out.println("No targets for " + pipe + " found");
                }else{
                    System.out.println("ERROR: SOMETHINGS WRONG WITH " + pipe + "PIPELINE");
                }
            });
            visionThread.start();
            System.out.println("Thread for " + pipe + " started.");
    }

    public void tapeTracking(){
        visionThread = new VisionThread(cam1, new ReflTapeTracking(), pipeline ->{
            System.out.println("Creating vision thread for " + pipe);
                if (!pipeline.filterContoursOutput().isEmpty()){
                    Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
                    synchronized (imgLock) {
                        centerX = r.x + (r.width / 2);
                    }
                }else if(pipeline.filterContoursOutput().isEmpty()){
                    System.out.println("No targets for " + pipe + " found");
                }else{
                    System.out.println("ERROR: SOMETHINGS WRONG WITH " + pipe + "PIPELINE");
                }
            });
            visionThread.start();
            System.out.println("Thread for " + pipe + " started.");
    }
    //Getters
    public double getCenterX(){
        return centerX;
    }

    public Object getImgLock(){
        return imgLock;
    }


}
