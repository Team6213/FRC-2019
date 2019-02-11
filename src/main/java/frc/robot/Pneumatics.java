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
