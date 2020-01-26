package frc.robot.commands.drivetrain;

import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultDrive extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_drivetrain;

  public DefaultDrive(Drivetrain m_drivetrain) {
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    // Arcade drive function from subsystem
    m_drivetrain.arcadeDrive(ControlMap.driver.getRawAxis(ControlMap.right_axis_X), ControlMap.driver.getRawAxis(ControlMap.left_axis_Y));
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
