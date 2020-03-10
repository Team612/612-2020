package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.controls.ControlMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Intake extends SubsystemBase {

  // Spark objects for intake flywheel, belt, and outtake
  private final WPI_TalonSRX talon_intake = new WPI_TalonSRX(Constants.TALON_INTAKE);
  private final WPI_TalonSRX talon_lower_belt = new WPI_TalonSRX(Constants.TALON_LOWER_BELT);

  private final WPI_TalonSRX talon_index = new WPI_TalonSRX(Constants.TALON_INDEX);

  // Piston objects for intake and arm grabber
  private final DoubleSolenoid solenoid_intake = new DoubleSolenoid(Constants.PCM_2, Constants.SOLENOID_INTAKE[0], Constants.SOLENOID_INTAKE[1]);
  private final DoubleSolenoid solenoid_wall = new DoubleSolenoid(Constants.PCM_2, Constants.SOLENOID_WALL[0], Constants.SOLENOID_WALL[1]);

  // Setting up analog input IR sensor
  private final AnalogInput infrared_lower = new AnalogInput(Constants.INFRARED_LOWER);
  private final AnalogInput infrared_jump = new AnalogInput(Constants.INFRARED_JUMP);

  // Infared threshold to detect balls
  private final double INFRARED_JUMP_THRESHOLD = 0.85;
  private final double INFRARED_LOWER_THRESHOLD = .8; 

  public void extendIntake() {
    solenoid_intake.set(Value.kReverse);
  }

  public void toggleIntake() {
    if (solenoid_intake.get() == Value.kForward){
      solenoid_intake.set(Value.kReverse);
    } else {
      solenoid_intake.set(Value.kForward);
    }
  }

 


  public void retractIntake() {
    // Retract out the arm and intake to go back to original setup
    System.out.println("Retracted intake");
    solenoid_intake.set(Value.kForward);
    talon_intake.set(0);
    talon_lower_belt.set(0);
  }

  public void jogIntake(){
    talon_intake.set(ControlMap.gunner.getRawAxis(2) - ControlMap.gunner.getRawAxis(3));
  }

  public void runIntake(double intake_speed, double belt_speed) {

    if (infrared_lower.getAverageVoltage() > INFRARED_LOWER_THRESHOLD) {
      talon_lower_belt.set(0);
    } else {
      talon_lower_belt.set(belt_speed);
    }
    talon_intake.set(intake_speed);

  }

  public void runIndex(double speed) {
    talon_index.set(speed);
  }

  public void runLowerBelt(double speed){
    talon_lower_belt.set(speed);
  }

  // Set the intake flywheel to a certain speed
  public void setIntake(double speed) {
    System.out.println(infrared_jump.getAverageVoltage());
      if (infrared_jump.getAverageVoltage() > INFRARED_JUMP_THRESHOLD) {
        talon_intake.set(-speed);  // Status: Ball is is detected above intake
      } else {
        talon_intake.set(speed);
      }
    
  }

  public Intake() {
    solenoid_wall.set(Value.kReverse);
    
    talon_lower_belt.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    //System.out.println(infrared_lower.getAverageVoltage());
  }
}
