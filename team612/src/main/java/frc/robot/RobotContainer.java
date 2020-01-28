package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.commands.intake.StartIntake;
import frc.robot.commands.intake.StartOuttake;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive c_defaultdrive = new DefaultDrive(m_drivetrain);

  // Intake subsystem
  private final Intake m_intake = new Intake();

  private final SampleAuto m_sampleauto = new SampleAuto();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {

    // Controller mappings for intake commands
    ControlMap.driver_button_A.toggleWhenPressed(new StartIntake(m_intake));
    ControlMap.driver_button_B.whileHeld(new StartOuttake(m_intake));

  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_defaultdrive);
  }

  public Command getAutonomousCommand() {
    return m_sampleauto;
  }

}
