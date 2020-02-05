/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class StartOuttake extends CommandBase {

  private double SPEED = 1;  // Fixed speed for intake sparks

  private final Intake m_intake;  // Local reference to intake subsystem

  public StartOuttake(Intake m_intake) {
    this.m_intake = m_intake;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    // If the intake mechanism is not retracted, end the outtake command
    if (m_intake.getIntakeMode()) {
      System.out.println("must retract intake before starting outtake");
      end(true);
    }
  }

  @Override
  public void execute() {
    m_intake.setOuttake(SPEED);  // Run outtake while held at specific speed
  }

  @Override
  public void end(boolean interrupted) {
    m_intake.setOuttake(0);  // Once done, end the command
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
