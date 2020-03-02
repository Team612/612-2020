/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {

  private final Intake m_intake;
  private double INTAKE_SPEED = .8; 
  private double BELT_SPEED = .825;

  public RunIntake(Intake m_intake) {
    this.m_intake = m_intake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.extendIntake();
    m_intake.firstReadUpper = true;
    m_intake.firstReadLower = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.setIntake(INTAKE_SPEED);
    m_intake.setBelt(BELT_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.setIntake(0);
    m_intake.setBelt(BELT_SPEED);
    m_intake.retractIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
