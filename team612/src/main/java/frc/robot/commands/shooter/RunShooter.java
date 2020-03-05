/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunShooter extends CommandBase {

  private final Shooter m_shooter;

  private final double SPEED_DELAY = 4;  // Second count until shooter is at "full speed"
  private final Timer shooter_timer = new Timer();  // Timer to measure until full speed is reached

  public RunShooter(Shooter m_shooter) {
    this.m_shooter = m_shooter;
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter_timer.reset();
    shooter_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (shooter_timer.get() > SPEED_DELAY) {
      // TODO: Run the lower belt to feed the balls in
    }
    System.out.println("Time: " + shooter_timer.get());
    m_shooter.setShooter(0.75);  // Run the shooter at a fixed speed (.75 for 16 inches back)
    m_shooter.setBelt(1.0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.setShooter(0.0);
    m_shooter.setBelt(0.0);
    shooter_timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
}
