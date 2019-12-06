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

package frc.autonomous;

/**
 * Autonomous code that will be referenced in the Robot.java file
 */

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// blah blah coding

public class Auto {
    final double SpeedRate = 2.0;

    public void LeftSideAuto(DifferentialDrive robotDrive, Timer timer) {
        
    }

    public void defaultAuto(DifferentialDrive robotDrive, Timer timer) {
        if (timer.get() < CalcTime(10.25)) {
            robotDrive.arcadeDrive(1.0, 0.0);
        }else if(timer.get() > CalcTime(10.25) && timer.get() <  CalcTime(15.25)) {
            robotDrive.arcadeDrive(0.0, 0.5);
            
        }
    }

    public void sandstorm(DifferentialDrive robotDrive, XboxController m_Xbox){
        
    }

    public double CalcTime(double distance) {
        double time = distance / SpeedRate;
        return time; 

    }

}
