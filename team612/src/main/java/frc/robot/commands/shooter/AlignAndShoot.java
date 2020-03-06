/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import frc.robot.RobotContainer;
import frc.robot.commands.shooter.AlignShooter;
import frc.robot.commands.shooter.RunShooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AlignAndShoot extends SequentialCommandGroup {
  /**
   * Creates a new AlignAndShoot.
   */

  public AlignAndShoot() {
    // Add your commands in the super() call, e.g.
    super(new AlignShooter(RobotContainer.m_drivetrain), new RunShooter(RobotContainer.m_shooter));
  }
}