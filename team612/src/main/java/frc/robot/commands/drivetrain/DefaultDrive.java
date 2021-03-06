package frc.robot.commands.drivetrain;

import frc.robot.Constants;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultDrive extends CommandBase {

  // Remove PMD warnings
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;  // Local version of drivetrain

  public DefaultDrive(Drivetrain m_drivetrain) {
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize(){
  }

  @Override
  public void execute(){
    // Pass values from joystick to west coast drive with deadzone value
    if (Constants.ENABLE_ARCADE) {
      m_drivetrain.arcadeDrive(ControlMap.driver.getRawAxis(4), ControlMap.driver.getRawAxis(1));
    } else {
      m_drivetrain.tankDrive(ControlMap.driver.getRawAxis(1), ControlMap.driver.getRawAxis(5));
    }
  }

  @Override
  public void end(boolean interrupted){
  }

  @Override
  public boolean isFinished(){
    return false;
  }
  

}