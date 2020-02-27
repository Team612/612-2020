package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.intake.*;
import frc.robot.controls.ControlMap;
import frc.robot.commands.climb.EngageClimb;
import frc.robot.commands.climb.RunWinch;
import frc.robot.commands.climb.ToggleHook;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {

  // Drivetrain subsystem and command
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive c_drive = new DefaultDrive(m_drivetrain);

  // Intake subsystem
  private final Intake m_intake = new Intake();

  private final SampleAuto m_sampleauto = new SampleAuto();

  private final Climb m_climb = new Climb();
  
  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {

    // Drivetrain gear shift controls
    ControlMap.driver_button_RB.whenPressed(new SetHighGear(m_drivetrain));
    ControlMap.driver_button_LB.whenPressed(new SetLowGear(m_drivetrain));

    // Intake control bindings
    ControlMap.RUN_INTAKE.toggleWhenPressed(new RunIntake(m_intake));
    ControlMap.RUN_OUTTAKE.whileHeld(new RunOuttake(m_intake));
    ControlMap.RUN_FLYWHEEL.whileHeld(new RunFlywheel(m_intake));

    // Climb control bindings
    ControlMap.ENGAGE_CLIMB.whenPressed(new EngageClimb(m_climb));
    ControlMap.TOGGLE_HOOK.whenPressed(new ToggleHook(m_climb));
    ControlMap.RUN_WINCH.whileHeld(new RunWinch(m_climb));
    
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_drive);
  }

  public Command getAutonomousCommand() {
    return m_sampleauto;
  }

}
