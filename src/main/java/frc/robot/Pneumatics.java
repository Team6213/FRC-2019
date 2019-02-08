/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.hal.JNIWrapper;
import edu.wpi.first.hal.CompressorJNI;
/**
 * Add your docs here.
 */
public class Pneumatics {
    Compressor c = new Compressor(0);
    boolean enabled = c.enabled();
    boolean pressureSwitch = c.getPressureSwitchValue();
    double current = c.getCompressorCurrent();

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
}
