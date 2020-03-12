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

public class RunShooter extends CommandBase {

  private final Intake m_intake;

  private double shooter_speed = 0.52; // .405 for 2ft from the edge, .52 for 10 ft from the edge
  private double feeder_speed = 1;
  private double index_speed = 0.75;

  private Timer shooter_timer = new Timer();
  private Timer spin_timer = new Timer();

  private int i;
  private double acceleration;
  private double sampledVelocity;
  private double prev_velocity;

  public RunShooter(Intake m_intake) {
    this.m_intake = m_intake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter_timer.start();
    shooter_timer.reset();
    spin_timer.start();
    shooter_timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    double current_velocity = m_intake.getShooterVelocity();
    if (i > 3) {
      acceleration = (current_velocity - prev_velocity)/spin_timer.get();
      spin_timer.reset();
      i=0;
      prev_velocity = current_velocity;
    }

    System.out.println("Accel: " + acceleration);
    
    if (acceleration < 60) {
      m_intake.runFeeder(feeder_speed);
      m_intake.runIndexer(index_speed);
    } else {
      m_intake.runFeeder(0);
      m_intake.runIndexer(0);
    }

    m_intake.shoot(shooter_speed);

    i++;
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter_timer.stop();
    spin_timer.stop();
    m_intake.shoot(0);
    m_intake.runFeeder(0);
    m_intake.runIndexer(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
