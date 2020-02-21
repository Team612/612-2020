/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class EngageClimb extends CommandBase {
  
  private Climb m_climb;  // Local reference to climb subsystem

  public EngageClimb(Climb m_climb) {
    this.m_climb = m_climb;
    addRequirements(m_climb);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_climb.toggleClimb();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
  
}
