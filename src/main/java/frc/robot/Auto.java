/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Autonomous code that will be referenced in the Robot.java file
 */

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Auto {
    double SpeedRate = 2.0;

    public void LeftSideAuto(DifferentialDrive robotDrive, Timer timer) {
        
    }

    public void defaultAuto(DifferentialDrive robotDrive, Timer timer) {
        if (timer.get() < CalcTime(10.25)) {
            robotDrive.arcadeDrive(1.0, 0.0);
        }else if(timer.get() > CalcTime(10.25) && timer.get() <  CalcTime(15.25)) {
            robotDrive.arcadeDrive(0.0, 0.5);
            
        }
    }
    public double CalcTime(double distance) {
        double time = distance / SpeedRate;
        return time; 

    }
}
