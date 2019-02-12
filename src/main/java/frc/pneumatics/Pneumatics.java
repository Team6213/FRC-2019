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

package frc.pneumatics;

import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.hal.JNIWrapper;
import edu.wpi.first.hal.CompressorJNI;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

//Code for Pneumatics///
public class Pneumatics {
    Compressor c = new Compressor(0);
    boolean enabled = c.enabled();
    boolean pressureSwitch = c.getPressureSwitchValue();
    double current = c.getCompressorCurrent();
    Solenoid SS1 = new Solenoid(1);
    DoubleSolenoid SD1 = new DoubleSolenoid(6, 7);

    //Compressor Code///
    public void CheckC(){
        System.out.println("Enabled: " + enabled);
        System.out.println("Pressure Switch: " + pressureSwitch);
        System.out.println("Current: " + current + "\n");
    }
    
    public void GoTest(){
        c.setClosedLoopControl(true);
        CheckC();
        c.setClosedLoopControl(false);
        CheckC();
    }

    public void cOn(){
        c.setClosedLoopControl(true);
    }

    public void cOff(){
        c.setClosedLoopControl(false);
    }

    //Solenoid Code//
    public void testSS1(){
        SS1.set(true);
        SS1.set(false);
    }

    public void testSD1(){
        SD1.set(DoubleSolenoid.Value.kForward);
        SD1.set(DoubleSolenoid.Value.kOff);
        SD1.set(DoubleSolenoid.Value.kReverse);
    }

    public void pushUp(){
        SD1.set(DoubleSolenoid.Value.kForward);
    }

    public void stayStill(){
        SD1.set(DoubleSolenoid.Value.kOff);
    }

    public void goBack(){
        SD1.set(DoubleSolenoid.Value.kReverse);
    }
}
