package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.climb.EngageClimb;
import frc.robot.commands.climb.RunWinch;
import frc.robot.commands.climb.ToggleHook;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive c_defaultdrive = new DefaultDrive(m_drivetrain);

  private final SampleAuto m_sampleauto = new SampleAuto();

  private final Climb m_climb = new Climb();
  
  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {
    ControlMap.driver_button_X.whenPressed(new EngageClimb(m_climb));
    ControlMap.driver_button_B.whenPressed(new ToggleHook(m_climb));
    ControlMap.driver_button_RB.whileHeld(new RunWinch(m_climb));
    controlMap.driver_button_LB.whileHeld(new ReverseWinch(m_climb));
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_defaultdrive);
  }

  public Command getAutonomousCommand() {
    return m_sampleauto;
  }

}
