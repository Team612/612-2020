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
import frc.robot.subsystems.Intake;

public class RunShooter extends CommandBase {

  private final Shooter m_shooter;
  private final Intake m_intake;

  private final double SPEED_DELAY = 6;  // Second count until shooter is at "full speed"
  private final Timer shooter_timer = new Timer();  // Timer to measure until full speed is reached
  private double distance = 24; //in inches

  private double vi = 0;
  
  public RunShooter(Shooter m_shooter, Intake m_intake) {
    this.m_shooter = m_shooter;
    this.m_intake = m_intake;
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
    double vf = m_shooter.spark_shooter.getEncoder().getVelocity();
    double accel = (vf-vi);
    //System.out.println("Vf" + vf);
    //System.out.println("Vi" + vi);
    //System.out.println("time" + shooter_timer.get());
    //System.out.println("Accel" + accel);
    if(shooter_timer.get() > SPEED_DELAY){
      m_intake.runLowerBelt(1.0);
    }
    
      // TODO: Run the lower belt to feed the balls in
    

    //double speed = Math.sqrt((2.179*(10**-5))*(distance**2) + 0.3295); //speed calculation based on distance value in inches
    double speed = 0.46;
    //System.out.println("Time: " + shooter_timer.get());
    
    m_shooter.setShooter(speed);
    vi = vf;

    //shooter_timer.reset();
    }

  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.setShooter(0.0);
    m_intake.runIntake(0.0, 0.0);
    shooter_timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
}
