/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // Deadzone and voltage output constants
  private final double DEADZONE = 0.1;
  private final double INFRARED_TARGET = 5;
  private final double INFRARED_DEADZONE = 0.564;

  // Create spark motors
  private final WPI_TalonSRX spark_fr_drive = new WPI_TalonSRX(Constants.SPARK_FR_DRIVE);
  private final WPI_TalonSRX spark_fl_drive = new WPI_TalonSRX(Constants.SPARK_FL_DRIVE);
  private final WPI_TalonSRX spark_br_drive = new WPI_TalonSRX(Constants.SPARK_BR_DRIVE);
  private final WPI_TalonSRX spark_bl_drive = new WPI_TalonSRX(Constants.SPARK_BL_DRIVE);

  // Infrared to tell distance to wall
  private final AnalogInput infrared_distance = new AnalogInput(Constants.INFRARED_DISTANCE);

  // Basic arcade drive for west coast drivetrain
  public void arcadeDrive(double x_axis, double y_axis) {  
    //sets up deadzones
    x_axis = Math.abs(x_axis) < DEADZONE ? 0.0 : x_axis;
    y_axis = Math.abs(y_axis) < DEADZONE ? 0.0 : y_axis;

    //WPI_Talon SRX Caps voltage at 1.0
    double leftCommand = y_axis - x_axis;
    double rightCommand = y_axis + x_axis;
    
    // right side motor controls
    spark_fr_drive.set(-rightCommand/2);
    spark_br_drive.set(-rightCommand/2);

    //left side motor controls
    spark_fl_drive.set(leftCommand/2);
    spark_bl_drive.set(leftCommand/2);
  }

  public double getInfraredVoltage() {
    return infrared_distance.getVoltage();
  }

  // Drive backwards smoothly to infrared distance target, returns true if at target
  public boolean driveToTarget() {
    // PID calculations (only proportional constant)
    double error = (1/infrared_distance.getVoltage()) - INFRARED_TARGET;
    double kP = 0.5;
    double motor_output = error * kP;
    System.out.println(error);
    System.out.println("Motor Output:" + motor_output);
    if (Math.abs(motor_output) < INFRARED_DEADZONE) {
      return true;
    } else {
      arcadeDrive(0, motor_output/2);
      return false;
    }
    
  }

  public Drivetrain() {
  }

  @Override
  public void periodic() {
  }
  
}
