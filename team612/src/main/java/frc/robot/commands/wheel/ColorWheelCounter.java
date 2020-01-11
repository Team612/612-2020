/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ColorWheelCounter extends Command {

  private String intial_color;
  private String current_color;
  private double revolution_count;
  private String target_color;
  
  public ColorWheelCounter() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    inital_color = "red";
    target_color = "green";
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    current_color = "red";

    if(current_color.equals(inital_color)){
     revolution_count += 0.5;
    }

    if(revolution_count >= 3.0 && revolution_count <= 5.0){
      //stop boi
    }


    //if current_color is deteced then set intial_color = current_color

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
