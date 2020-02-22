/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wheel;

public class ExtendColorWheel extends CommandBase {
  private Wheel m_wheel; 
  /**
   * Creates a new ExtendColorWheel.
   */
  public ExtendColorWheel(Wheel m_wheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_wheel = m_wheel; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_wheel.engagePiston();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
