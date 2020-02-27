/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class RunWinch extends CommandBase {

  private Climb m_climb;

  private double WINCH_SPEED = 1;

  public static boolean isRunning = false;

  public RunWinch(Climb m_climb) {
    this.m_climb = m_climb;
    addRequirements(m_climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isRunning = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climb.setWinch(WINCH_SPEED);


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climb.setWinch(0);
    isRunning = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
