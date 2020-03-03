/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // Controller deadzone constant
  private final double DEADZONE = 0.1;
  
  // Talons for drivetrain
  private CANSparkMax spark_fr_drive = new CANSparkMax(Constants.SPARK_FR_DRIVE, MotorType.kBrushless);
  private CANSparkMax spark_fl_drive = new CANSparkMax(Constants.SPARK_FL_DRIVE, MotorType.kBrushless);
  private CANSparkMax spark_br_drive = new CANSparkMax(Constants.SPARK_BR_DRIVE, MotorType.kBrushless);
  private CANSparkMax spark_bl_drive = new CANSparkMax(Constants.SPARK_BL_DRIVE, MotorType.kBrushless);

  // Double solenoid for changing gears
  private DoubleSolenoid solenoid_drive = new DoubleSolenoid(Constants.PCM_2, Constants.SOLENOID_DRIVE[0], Constants.SOLENOID_DRIVE[1]);

  // Basic arcade drive function
  public void arcadeDrive(double x_axis, double y_axis) {  
    //sets up deadzones
    x_axis = Math.abs(x_axis) < DEADZONE ? 0.0 : x_axis;
    y_axis = Math.abs(y_axis) < DEADZONE ? 0.0 : y_axis;

    //WPI_Talon SRX Caps voltage at 1.0
    double leftCommand = y_axis - x_axis;
    double rightCommand = y_axis + x_axis;
    
    // right side motor controls
    spark_fr_drive.set(-rightCommand);
    spark_br_drive.set(-rightCommand);

    //left side motor controls
    spark_fl_drive.set(leftCommand);
    spark_bl_drive.set(leftCommand);
  }

  // Basic tank drive function
  public void tankDrive(double left_command, double right_command) {
    left_command = Math.abs(left_command) < DEADZONE ? 0.0 : left_command;
    right_command = Math.abs(right_command) < DEADZONE ? 0.0 : right_command;

    spark_fr_drive.set(-right_command);
    spark_br_drive.set(-right_command);

    spark_fl_drive.set(left_command);
    spark_bl_drive.set(left_command);
  }

  // Shift the double solenoid to kForward
  public void shiftForward() {
    solenoid_drive.set(Value.kForward);
    System.out.println("Shifted Drive Forward");
  }

  // Shift the double solenoid to kReverse
  public void shiftReverse() {
    solenoid_drive.set(Value.kReverse);
    System.out.println("Shifted Drive Reverse");
  }

  public Drivetrain() {
  }

  @Override
  public void periodic() {
  }
  
}