/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class SampleAuto extends CommandBase {
  /**
   * Creates a new ReplayRobot.
   */
  private Timer timer = new Timer();

  private final Drivetrain m_drivetrain;
  private final Intake m_intake;

  private Timer outtake_timer = new Timer();

  boolean FIRST_TIME = false;

  public SampleAuto(Drivetrain m_drivetrain, Intake m_intake) {
    this.m_drivetrain = m_drivetrain;
    this.m_intake = m_intake;
    addRequirements(m_drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    FIRST_TIME = true;
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Constants.SCORE_AUTO) {
      
      if (timer.get() < 1.8) {
        m_drivetrain.arcadeDrive(0, -.70);
      } else {
        outtake_timer.start();
        m_drivetrain.arcadeDrive(0, 0);
        if (FIRST_TIME) {
          m_intake.setOuttake(1);
          //Timer.delay(2); // what can i say except dElETe ThiS
          m_intake.setOuttake(0);
          FIRST_TIME = false;
        } else {
          m_intake.setOuttake(0);
        }
      }
    } else {
      if (timer.get() < .5) {
        m_drivetrain.arcadeDrive(0, -.70);
      } else {
        m_drivetrain.arcadeDrive(0, 0);
      }
    }
    System.out.println(timer.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
