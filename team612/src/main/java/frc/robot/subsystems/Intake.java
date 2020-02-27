/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


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
  private final AnalogInput infared_upper = new AnalogInput(Constants.INFARED_UPPER);
  private final AnalogInput infared_lower = new AnalogInput(Constants.INFARED_LOWER);
  private final AnalogInput infared_jump = new AnalogInput(Constants.INFARED_JUMP);
  //private final AnalogInput infared_3 = new AnalogInput(Constants.INFARED_3);

  // Infared threshold to detect balls
  private final double INFARED_UPPER_THRESHOLD = 1.32;
  private final double INFARED_JUMP_THRESHOLD = 0.85;
  private final double INFRARED_LOWER_THRESHOLD = .7;

  //Setting up delay values for usde with IR sensors
  private final double UPPER_DELAY = 0.2;
  private final double LOWER_DELAY = 0.6;

  // Check if ball is first read of interation
  public boolean firstReadUpper = true;
  public boolean firstReadLower = true;

  public double new_speed; 

  public void extendIntake() {
    solenoid_intake.set(Value.kReverse);
  }

  public void retractIntake() {
    // Retract out the arm and intake to go back to original setup
    System.out.println("Retracted intake");
    solenoid_intake.set(Value.kForward);
    talon_lower_belt.set(0);
    talon_upper_belt.set(0);
  }

  public void setBelt(double belt_speed) {

    // set the intake and belt to a certain speed
    System.out.println("Running intake flywheels");

    // If the upper infared senses a ball, stop the upper belt and engage the wall
    if (infared_upper.getAverageVoltage() > INFARED_UPPER_THRESHOLD) {
      System.out.println("Ball in chamber!");
      
      // If it is the first time the ball is detected
      if (firstReadUpper) {
        solenoid_wall.set(Value.kForward);
        firstReadUpper = false;
      }
      Timer.delay(UPPER_DELAY);
      talon_upper_belt.set(0);
      
      System.out.println("Ball in upper chamber!");
     
      if (infared_lower.getAverageVoltage() > INFRARED_LOWER_THRESHOLD) {
        System.out.println("Ball in lower chamber!");
        if(firstReadLower){
        solenoid_wall.set(Value.kReverse);
        firstReadLower = false;
        }
        Timer.delay(LOWER_DELAY);
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
    solenoid_wall.set(Value.kForward);

    // Set the outtake spark to a certain speed
    System.out.println("Running outtake flywheel");
    talon_outtake.set(speed);
    talon_lower_belt.set(speed);
    talon_upper_belt.set(speed);
  }

  // Set the intake flywheel to a certain speed
  public void setIntake(double speed) {
    System.out.println(infared_jump.getAverageVoltage());
    if (infared_jump.getAverageVoltage() > INFARED_JUMP_THRESHOLD) {
      talon_intake.set(-0.20);  // Status: Ball is is detected above intake
    } else {
      talon_intake.set(speed);
    }
  }

  public Intake() {
    solenoid_wall.set(Value.kReverse);
    talon_upper_belt.setNeutralMode(NeutralMode.Brake);
    talon_lower_belt.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    System.out.println("Jump: " + infared_jump.getAverageVoltage());
    System.out.println("Upper: " + infared_upper.getAverageVoltage());
    System.out.println("Lower" + infared_lower.getAverageVoltage());
    System.out.println("---------");
    //System.out.println("LowerDelay" + LOWER_DELAY);
    //System.out.println("BooleanDwn" + firstReadLower);
    //System.out.println("BooleanUp" + firstReadUpper);

    // SmartDashboard.putNumber("IR Sensor Jump: ", infared_jump.getAverageVoltage());
    // SmartDashboard.putNumber("IR Sensor Intake: ", infared_upper.getAverageVoltage());
    //ControlMap.driver.setRumble(RumbleType.kLeftRumble, 1);
  }
}
