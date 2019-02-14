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

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * Add your docs here.
 */
public class VisionProcessing {
    int IMG_HEIGHT;
    int IMG_WIDTH;
    UsbCamera cam;
    VisionPipeline pipe;
    VisionThread visionThread;

    public void VisionProcces(int IMG_WIDTH, int IMG_HEIGHT, VisionPipeline pipe){
        this.IMG_HEIGHT = IMG_HEIGHT;
        this.IMG_WIDTH = IMG_WIDTH;
        this.pipe = pipe;

    }

    public void startCapture(){
        cam = CameraServer.getInstance().startAutomaticCapture();
        cam.setResolution(IMG_WIDTH, IMG_HEIGHT);
    }

    public void visionInit(){
        startCapture();
        visionThread = new VisionThread(cam, pipeline, listener)
    }
}
