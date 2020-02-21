/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // Controller deadzone constant
  private final double DEADZONE = 0.1;

  // Talons for drivetrain
  private WPI_TalonSRX talon_fr_drive = new WPI_TalonSRX(Constants.TALON_FR_DRIVE);
  private WPI_TalonSRX talon_fl_drive = new WPI_TalonSRX(Constants.TALON_FL_DRIVE);
  private WPI_TalonSRX talon_br_drive = new WPI_TalonSRX(Constants.TALON_BR_DRIVE);
  private WPI_TalonSRX talon_bl_drive = new WPI_TalonSRX(Constants.TALON_BL_DRIVE);

  // Ultrasonic sensor for drive
  private Ultrasonic ultrasonic_drive = new Ultrasonic(Constants.ULTRASONIC_DRIVE[0], Constants.ULTRASONIC_DRIVE[1]);

  // Double solenoid for changing gears
  private DoubleSolenoid solenoid_drive = new DoubleSolenoid(1, Constants.SOLENOID_DRIVE[0], Constants.SOLENOID_DRIVE[1]);
  
  // 

  // Basic arcade drive function
  public void arcadeDrive(double x_axis, double y_axis) {  
    //sets up deadzones
    x_axis = Math.abs(x_axis) < DEADZONE ? 0.0 : x_axis;
    y_axis = Math.abs(y_axis) < DEADZONE ? 0.0 : y_axis;

    //WPI_Talon SRX Caps voltage at 1.0
    double leftCommand = y_axis - x_axis;
    double rightCommand = y_axis + x_axis;
    
    // right side motor controls
    talon_fr_drive.set(-rightCommand);
    talon_br_drive.set(-rightCommand);

    //left side motor controls
    talon_fl_drive.set(leftCommand);
    talon_bl_drive.set(leftCommand);
  }

  // Basic tank drive function
  public void tankDrive(double left_command, double right_command) {
    left_command = Math.abs(left_command) < DEADZONE ? 0.0 : left_command;
    right_command = Math.abs(right_command) < DEADZONE ? 0.0 : right_command;

    talon_fr_drive.set(-right_command);
    talon_br_drive.set(-right_command);

    talon_fl_drive.set(left_command);
    talon_bl_drive.set(left_command);
  }

  // Get distance in inches from ultrasonic in drive
  public double getDistance() {
    return ultrasonic_drive.getRangeInches();
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
    // Prepare and enable ultrasonic
    ultrasonic_drive.setEnabled(true);
    ultrasonic_drive.setAutomaticMode(true);
  }

  // Periodic loop for ShuffleBoard values
  @Override
  public void periodic() {
    // Shuffle board values
    SmartDashboard.putNumber("Back Left Drive Talon", talon_bl_drive.get());
    SmartDashboard.putNumber("Back Right Drive Talon", talon_br_drive.get());
    SmartDashboard.putNumber("Front Left Drive Talon", talon_fl_drive.get());
    SmartDashboard.putNumber("Front Right Drive Talon", talon_fr_drive.get());
    SmartDashboard.putNumber("Ultrasonic Distance", getDistance());
    System.out.println("This Code Was Made By Prahalad");
    System.out.println("Kai be our senpai");
  }
  
}