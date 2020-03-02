package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Intake extends SubsystemBase {

  // Spark objects for intake flywheel, belt, and outtake
  private final WPI_TalonSRX talon_intake = new WPI_TalonSRX(Constants.SPARK_INTAKE);
  private final WPI_TalonSRX talon_upper_belt = new WPI_TalonSRX(Constants.SPARK_UPPER_BELT);
  private final WPI_TalonSRX talon_lower_belt = new WPI_TalonSRX(Constants.SPARK_LOWER_BELT);
  private final WPI_TalonSRX talon_outtake = new WPI_TalonSRX(Constants.SPARK_OUTTAKE);

  // Piston objects for intake and arm grabber
  private final DoubleSolenoid solenoid_intake = new DoubleSolenoid(Constants.PCM_2, Constants.SOLENOID_INTAKE[0], Constants.SOLENOID_INTAKE[1]);
  private final DoubleSolenoid solenoid_wall = new DoubleSolenoid(Constants.PCM_2, Constants.SOLENOID_WALL[0], Constants.SOLENOID_WALL[1]);

  // Setting up analog input IR sensor
  private final AnalogInput infrared_upper = new AnalogInput(Constants.INFRARED_UPPER);
  private final AnalogInput infrared_lower = new AnalogInput(Constants.INFRARED_LOWER);
  private final AnalogInput infrared_jump = new AnalogInput(Constants.INFRARED_JUMP);
  //private final AnalogInput infrared_3 = new AnalogInput(Constants.INFRARED_3);

  // Infared threshold to detect balls
  private final double INFRARED_UPPER_THRESHOLD = 1.32;
  private final double INFRARED_JUMP_THRESHOLD = 0.85;
  private final double INFRARED_LOWER_THRESHOLD = .7;

  //Setting up delay values for usde with IR sensors
  private final double UPPER_DELAY = 0.2;
  private final double LOWER_DELAY = .5;

  // Check if ball is first read of interation
  public boolean firstReadUpper = true;
  public boolean firstReadLower = true;

  private boolean enable_ir_intake = true;

  public double new_speed; 

  public void extendIntake() {
    solenoid_intake.set(Value.kReverse);
  }

  public void retractIntake() {
    // Retract out the arm and intake to go back to original setup
    System.out.println("Retracted intake");
    solenoid_intake.set(Value.kForward);
    talon_intake.set(0);
    talon_lower_belt.set(0);
    talon_upper_belt.set(0);
  }

  public void setBelt(double belt_speed) {

    // set the intake and belt to a certain speed
    System.out.println("Running intake flywheels");

    // If the upper infrared senses a ball, stop the upper belt and engage the wall
    if (infrared_upper.getAverageVoltage() > INFRARED_UPPER_THRESHOLD) {
      System.out.println("Ball in chamber!");
      
      // If it is the first time the ball is detected
      solenoid_wall.set(Value.kForward);
      talon_upper_belt.set(0);
     
      if (infrared_lower.getAverageVoltage() > INFRARED_LOWER_THRESHOLD) {
        enable_ir_intake = false;
        System.out.println("Ball in lower chamber!");
        if(firstReadLower){
          //Timer.delay(LOWER_DELAY);// yeet this thing right now or else i swear i will do something i regret
          solenoid_wall.set(Value.kReverse);
          firstReadLower = false;
        }
        talon_lower_belt.set(0);
      } else {
        //solenoid_wall.set(Value.kForward);
        talon_lower_belt.set(belt_speed);
      }

    } else {
      talon_lower_belt.set(belt_speed);
      talon_upper_belt.set(belt_speed);
      solenoid_wall.set(Value.kReverse);
      talon_outtake.set(0);
    }

  }

  public void setOuttake(double speed) {
    solenoid_wall.set(Value.kReverse);
    // Set the outtake spark to a certain speed
    System.out.println("Running outtake flywheel");
    talon_outtake.set(speed);
    talon_lower_belt.set(speed);
    talon_upper_belt.set(speed);
  }

  // Set the intake flywheel to a certain speed
  public void setIntake(double speed) {
    System.out.println(infrared_jump.getAverageVoltage());
    if (enable_ir_intake){
      if (infrared_jump.getAverageVoltage() > INFRARED_JUMP_THRESHOLD) {
        talon_intake.set(-0.20);  // Status: Ball is is detected above intake
      } else {
        talon_intake.set(speed);
      }
    }
  }

  public Intake() {
    solenoid_wall.set(Value.kReverse);
    talon_upper_belt.setNeutralMode(NeutralMode.Coast);
    talon_lower_belt.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    System.out.println("Jump IR" + infrared_jump.getAverageVoltage());
    System.out.println("Lower IR" + infrared_lower.getAverageVoltage());
    System.out.println("Upper IR" + infrared_upper.getAverageVoltage());
    System.out.println("UpperBelt" + talon_upper_belt.get());
  }
}
