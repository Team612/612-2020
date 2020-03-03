/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase {
  
  
  private double distance = 0.2;
  private double kP = .9;

  private Shooter m_shooter;

/**
   * 
   * Creates a new RunShooter.
   */
  public RunShooter(Shooter m_shooter) {
    this.m_shooter = m_shooter;
    addRequirements(m_shooter);
  
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //distance = m_shooter.getDistance();
    System.out.println(distance);
    System.out.println(distance * kP);
    m_shooter.runOuttake(.75);
    System.out.println("----");
  


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.runOuttake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
