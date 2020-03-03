package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.shooter.AlignShooter;
import frc.robot.commands.shooter.RunShooter;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

public class RobotContainer {

  // Drivetrain subsystem and command
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive c_defaultdrive = new DefaultDrive(m_drivetrain);

  private final Shooter m_shooter = new Shooter();

  private final SampleAuto m_sampleauto = new SampleAuto();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {
    ControlMap.driver_button_A.whenPressed(new AlignShooter(m_drivetrain));
    ControlMap.driver_button_B.whenPressed(new RunShooter(m_shooter));
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_defaultdrive);
  }

  public Command getAutonomousCommand() {
    return m_sampleauto;
  }

}
