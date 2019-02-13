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

package frc.robot;

////////////////////////Custom imports////////////////////////////////
import frc.pneumatics.Pneumatics;
import frc.autonomous.Auto;
import frc.vision.*;

/////////////////////////WPILib imports///////////////////////////////
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  //Lightning McQueen///
  double speedMod;
  double Speed;
  //////////////////////

  //PS74 Controller//
  private final XboxController m_Xbox = new XboxController(0);
  double rTrigger;
  double lTrigger;
  boolean rBumper;
  boolean lBumper;
  double lAnalog;
  double rAnalog;
  ///////////////////

  //Avatar: The Last Airpods//
  private Pneumatics ChomCheck = new Pneumatics();
  //////////

  //Bomb//
  private final Timer timer = new Timer();
  ////////

  private final DifferentialDrive robotDrive
      = new DifferentialDrive(new Spark(0), new Spark(1));
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

    UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
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
  public void teleopInit(){
    rTrigger = m_Xbox.getRawAxis(3);
    lTrigger = m_Xbox.getRawAxis(2);
    rBumper = m_Xbox.getBumper(Hand.kRight);
    lBumper = m_Xbox.getBumper(Hand.kLeft);
    lAnalog = m_Xbox.getRawAxis(0);
    //rAnalog = m_Xbox.getRawAxis();
  }

  @Override
  public void teleopPeriodic() {
    if (lBumper){
      ChomCheck.pushUp();
    }else{
      ChomCheck.stayStill();
    }
    if (m_Xbox.getBumper(Hand.kRight)){
      ChomCheck.goBack();
    }
    
    //Controls speed
    speedMod = GetSpeed(m_Xbox);

    //Drives Robot
    if (rTrigger > 0){
      robotDrive.arcadeDrive(rTrigger * speedMod, lAnalog);
      System.out.println("Right trigger is pressed");
    }else if (lTrigger > 0){
      robotDrive.arcadeDrive(lTrigger * speedMod * -1, lAnalog);
      System.out.println("Left trigger is pressed");
    }else{
      robotDrive.arcadeDrive(0, lAnalog);
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
