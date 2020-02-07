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

public class StartIntake extends CommandBase {
  
  private double SPEED = 1;  // Fixed speed for intake sparks

  private final Intake m_intake;  // Local reference to intake subsystem

  public StartIntake(Intake m_intake) {
    this.m_intake = m_intake;
    addRequirements(m_intake);
    m_intake.setIntakeMode(false); // Start intake mode
    m_intake.retractIntake();

    
  }

  @Override
  public void initialize() {
    m_intake.setIntakeMode(true); // Start intake mode
    m_intake.extendIntake();  // First, extend the intake arm
    Timer.delay(1);
  }

  @Override
  public void execute() {
    // Run the flywheel at a fixed speed while command active
    m_intake.setFlyWheels(1, 1);
  }

  @Override
  public void end(boolean interrupted) {
    // When the command ends, stop the flywheel and retract the entire mechanism
    m_intake.setFlyWheels(0,0);  
    m_intake.retractIntake();
    m_intake.setIntakeMode(false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
  
}
