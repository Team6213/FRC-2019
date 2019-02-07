package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Auto{

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
