/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RunFlywheel extends CommandBase {

  private double SPEED = 1;  // Fixed speed for intake sparks

  private final Intake m_intake;  // Local reference to intake subsystem

  public RunFlywheel(Intake m_intake) {
    this.m_intake = m_intake;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_intake.setIntake(SPEED); // Run outtake while held at specific speed
  }

  @Override
  public void end(boolean interrupted) {
    m_intake.setIntake(0);  // Once done, end the command
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
