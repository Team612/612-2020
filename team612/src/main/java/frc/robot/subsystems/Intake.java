/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.controls.ControlMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Intake extends SubsystemBase {

  // Spark objects for intake flywheel, belt, and outtake
  private final CANSparkMax talon_intake = new CANSparkMax(Constants.SPARK_INTAKE, MotorType.kBrushless);
  private final CANSparkMax talon_upper_belt = new CANSparkMax(Constants.SPARK_UPPER_BELT, MotorType.kBrushed);
  private final CANSparkMax talon_lower_belt = new CANSparkMax(Constants.SPARK_LOWER_BELT, MotorType.kBrushless);
  private final CANSparkMax talon_outtake = new CANSparkMax(Constants.SPARK_OUTTAKE, MotorType.kBrushless);

  // Piston objects for intake and arm grabber
  private final DoubleSolenoid solenoid_intake = new DoubleSolenoid(Constants.PCM_2, Constants.SOLENOID_INTAKE[0], Constants.SOLENOID_INTAKE[1]);
  private final DoubleSolenoid solenoid_wall = new DoubleSolenoid(Constants.PCM_2, Constants.SOLENOID_WALL[0], Constants.SOLENOID_WALL[1]);

  // Setting up analog input IR sensor
  private final AnalogInput infared_intake = new AnalogInput(Constants.INFARED_INTAKE);
  private final AnalogInput infared_jump = new AnalogInput(Constants.INFARED_JUMP);

  // Infared threshold to detect balls
  private final double INFARED_INTAKE_THRESHOLD = 0.775;
  private final double INFARED_JUMP_THRESHOLD = 0.75;

  private final double INTAKE_DELAY = 0.1;

  // Check if ball is first read of interation
  boolean firstRead = false;

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
    talon_lower_belt.set(belt_speed);

    // If the upper infared senses a ball, stop the upper belt and engage the wall
    if (infared_intake.getAverageVoltage() > INFARED_INTAKE_THRESHOLD) {
      System.out.println("Ball in chamber!");
      solenoid_wall.set(Value.kReverse);
      // If it is the first time the ball is detected
      if (firstRead) {
        Timer.delay(INTAKE_DELAY);  // Delay to allow ball to reach top
        firstRead = false;
      }
      talon_upper_belt.set(0);
    } else {
      firstRead = true;
      talon_upper_belt.set(-belt_speed);
      solenoid_wall.set(Value.kForward);
      talon_outtake.set(0);
    }

  }

  public void setOuttake(double speed) {
    solenoid_wall.set(Value.kForward);

    // Set the outtake spark to a certain speed
    System.out.println("Running outtake flywheel");
    talon_outtake.set(speed*.75);
    talon_lower_belt.set(speed);
    talon_upper_belt.set(-speed);
  }

  // Set the intake flywheel to a certain speed
  public void setIntake(double speed) {
    System.out.println(infared_jump.getAverageVoltage());
    if (infared_jump.getAverageVoltage() > INFARED_JUMP_THRESHOLD) {
      talon_intake.set(-0.1);  // Status: Ball is is detected above intake
    } else {
      talon_intake.set(speed);
    }
  }

  public Intake() {
    solenoid_wall.set(Value.kForward);
    talon_upper_belt.setNeutralMode(NeutralMode.Coast);
    talon_lower_belt.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    System.out.println(infared_jump.getAverageVoltage());
    System.out.println(infared_intake.getAverageVoltage());
    System.out.println("---------");
    SmartDashboard.putNumber("IR Sensor Jump: ", infared_jump.getAverageVoltage());
    SmartDashboard.putNumber("IR Sensor Intake: ", infared_intake.getAverageVoltage());
    ControlMap.driver.setRumble(RumbleType.kLeftRumble, 1);
  }
}
