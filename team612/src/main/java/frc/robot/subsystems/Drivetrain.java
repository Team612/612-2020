/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // Deadzone and voltage output constants
  private final double DEADZONE = 0.1;
  private final double VOLTAGE_OUTPUT = 12;

  // Create spark motors
  private final WPI_TalonSRX spark_fr_drive = new WPI_TalonSRX(Constants.SPARK_FR_DRIVE);
  private final WPI_TalonSRX spark_fl_drive = new WPI_TalonSRX(Constants.SPARK_FL_DRIVE);
  private final WPI_TalonSRX spark_br_drive = new WPI_TalonSRX(Constants.SPARK_BR_DRIVE);
  private final WPI_TalonSRX spark_bl_drive = new WPI_TalonSRX(Constants.SPARK_BL_DRIVE);

  // Basic arcade drive for west coast drivetrain
  public void arcadeDrive(double x_axis , double y_axis){

    // Filter deadzone
    x_axis = Math.abs(x_axis) < DEADZONE ? 0.0 : x_axis;
    y_axis = Math.abs(y_axis) < DEADZONE ? 0.0 : y_axis;
    
    // Throttle calculation
    double leftCommand = y_axis - x_axis;
    double rightCommand = y_axis + x_axis;

    // Set each spark with calculated motor percentage
    spark_fr_drive.set(rightCommand);
    spark_br_drive.set(rightCommand);
    spark_fl_drive.set(leftCommand);
    spark_bl_drive.set(leftCommand);

    SmartDashboard.putNumber("Left Command", leftCommand);
    SmartDashboard.putNumber("Right Command", rightCommand);
  }

  public void configureVoltageOutput() {

    spark_fr_drive.setVoltage(VOLTAGE_OUTPUT);
    spark_br_drive.setVoltage(VOLTAGE_OUTPUT);
    spark_fl_drive.setVoltage(VOLTAGE_OUTPUT);
    spark_bl_drive.setVoltage(VOLTAGE_OUTPUT);

  }

  public Drivetrain() {
    
    // Invert spark for one side of drivetrain
    spark_br_drive.setInverted(true);
    spark_fr_drive.setInverted(true);
    configureVoltageOutput();

  }

  @Override
  public void periodic() {

    // SmartDashboard display variables
    SmartDashboard.putNumber("Front Left Spark Percent", spark_fl_drive.get());
    SmartDashboard.putNumber("Front Right Spark Percent", spark_fr_drive.get());
    SmartDashboard.putNumber("Back Left Spark Percent", spark_bl_drive.get());
    SmartDashboard.putNumber("Back Right Spark Percent", spark_br_drive.get());
    
  }
  
}
