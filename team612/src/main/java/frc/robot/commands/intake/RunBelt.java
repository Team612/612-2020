/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RunBelt extends CommandBase {
  
  // Fixed speed for belt sparks
  private double BELT_SPEED = 1;

  private final Intake m_intake;  // Local reference to intake subsystem

  public RunBelt(Intake m_intake) {
    this.m_intake = m_intake;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_intake.setBelt(BELT_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    // When the command ends, stop the flywheel
    m_intake.setBelt(0);  
  }

  @Override
  public boolean isFinished() {
    return false;
  }
  
}
