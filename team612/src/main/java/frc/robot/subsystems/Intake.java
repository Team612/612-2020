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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Intake extends SubsystemBase {

  // Spark objects for intake flywheel, belt, and outtake
  private final WPI_TalonSRX spark_intake = new WPI_TalonSRX(Constants.SPARK_INTAKE);
  private final WPI_TalonSRX spark_upper_belt = new WPI_TalonSRX(Constants.SPARK_UPPER_BELT);
  private final WPI_TalonSRX spark_lower_belt = new WPI_TalonSRX(Constants.SPARK_LOWER_BELT);
  private final WPI_TalonSRX spark_outtake = new WPI_TalonSRX(Constants.SPARK_OUTTAKE);

  // Piston objects for intake and arm grabber
  private final DoubleSolenoid solenoid_intake = new DoubleSolenoid(0, Constants.SOLENOID_INTAKE[0], Constants.SOLENOID_INTAKE[1]);
  private final DoubleSolenoid solenoid_wall = new DoubleSolenoid(0, Constants.SOLENOID_WALL[0], Constants.SOLENOID_WALL[1]);

  // Setting up analog input IR sensor
  private final AnalogInput infared_intake = new AnalogInput(Constants.INFARED_INTAKE);
  private final AnalogInput infared_jump = new AnalogInput(Constants.INFARED_JUMP);
  private final double INFARED_INTAKE_THRESHOLD = 0.775;
  private final double INFARED_JUMP_THRESHOLD = 0.75;

  public void extendIntake() {
    solenoid_intake.set(Value.kReverse);
  }

  public void retractIntake() {
    // Retract out the arm and intake to go back to original setup
    System.out.println("Retracted intake");
    solenoid_intake.set(Value.kForward);
    spark_lower_belt.set(0);
    spark_upper_belt.set(0);
  }

  public void setBelt(double belt_speed) {

    // set the intake and belt to a certain speed
    System.out.println("Running intake flywheels");
    spark_lower_belt.set(-belt_speed);

    // If the upper infared senses a ball, stop the upper belt and engage the wall
    if (infared_intake.getAverageVoltage() > INFARED_INTAKE_THRESHOLD) {
      System.out.println("Ball in chamber!");
      solenoid_wall.set(Value.kReverse);
      spark_upper_belt.set(0);
    } else {
      spark_upper_belt.set(belt_speed);
      solenoid_wall.set(Value.kForward);
      spark_outtake.set(0);
    }

  }

  public void setOuttake(double speed) {
    solenoid_wall.set(Value.kForward);

    // Set the outtake spark to a certain speed
    System.out.println("Running outtake flywheel");
    spark_outtake.set(speed);
    spark_lower_belt.set(-speed);
    spark_upper_belt.set(speed);
  }

  // Set the intake flywheel to a certain speed
  public void setIntake(double speed) {

    System.out.println(infared_jump.getAverageVoltage());
    if (infared_jump.getAverageVoltage() > INFARED_JUMP_THRESHOLD) {
      spark_intake.set(0);  // Status: Ball is is detected above intake
    } else {
      spark_intake.set(speed);
    }
  
  }

  public Intake() {
    solenoid_wall.set(Value.kForward);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("IR Sensor Jump: ", infared_jump.getAverageVoltage());
    SmartDashboard.putNumber("IR Sensor Intake: ", infared_intake.getAverageVoltage());
  }

}