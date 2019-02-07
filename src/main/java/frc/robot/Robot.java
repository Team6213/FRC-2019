/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  double speedMod;
  double Speed;

  private final DifferentialDrive robotDrive
      = new DifferentialDrive(new Spark(0), new Spark(1));
  private final XboxController m_Xbox = new XboxController(0);
  private final Timer timer = new Timer();

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // double rTrigger = m_Xbox.getRawAxis(3);
    // double lTrigger = m_Xbox.getRawAxis(2);
    double lAnalog = m_Xbox.getRawAxis(0);

    //Controls speed
    speedMod = GetSpeed(m_Xbox);

    //Drives Robot
    if (m_Xbox.getRawAxis(3) > 0){
      robotDrive.arcadeDrive(m_Xbox.getRawAxis(3) * speedMod * -1, lAnalog * speedMod);
      System.out.println("Right trigger is pressed");
    }else if (m_Xbox.getRawAxis(3) > 0){
      robotDrive.arcadeDrive(m_Xbox.getRawAxis(3) * speedMod, lAnalog * speedMod);
      System.out.println("Left trigger is pressed");
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  ///////////////////Custom Methods///////////////////////////
  public double GetSpeed(XboxController S_Xbox){
    boolean aButton = S_Xbox.getAButton();
    boolean bButton = S_Xbox.getBButton();
    boolean yButton = S_Xbox.getYButton();
    boolean xButton = S_Xbox.getXButton();

    //Controls speed
    if(aButton) {
      Speed = 0.5;
      System.out.println("A is pushed");
    }
    if(bButton) {
      Speed = 0.75;
      System.out.println("B is pushed");
    } 
    if(yButton) {
      Speed = 1.0;
      System.out.println("Y is pushed");
    }
    if(xButton){
      Speed = 0.0;
      System.out.println("X is pushed");
    }

    return Speed;
  }
  
}
