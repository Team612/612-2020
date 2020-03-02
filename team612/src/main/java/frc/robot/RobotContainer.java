package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.SampleAuto;

import frc.robot.commands.drivetrain.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.climb.*;
import frc.robot.commands.wheel.*;

import frc.robot.subsystems.*;

import frc.robot.controls.ControlMap;

public class RobotContainer {
  
  // Subsystem objects
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Intake m_intake = new Intake();
  private final Wheel m_wheel = new Wheel();
  private final Climb m_climb = new Climb();

  // Default command objects
  private final DefaultDrive c_drive = new DefaultDrive(m_drivetrain);

  // Autonomous command
  private final SampleAuto c_sampleauto = new SampleAuto(m_drivetrain, m_intake);
  
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
    ControlMap.RUN_WINCH.whileHeld(new ReverseWinch(m_climb));
    //ControlMap.REVERSE_WINCH.whileHeld(new ReverseWinch(m_climb));
    
    // Color Wheel control bindings
    ControlMap.ROTATE_WHEEL.toggleWhenPressed(new RotateWheel(m_wheel));
    ControlMap.SPIN_TO_COLOR.toggleWhenPressed(new SpinToColor(m_wheel));
    ControlMap.ENGAGE_COLOR_WHEEL.whenPressed(new ExtendColorWheel(m_wheel));
    
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_drive);
  }

  public Command getAutonomousCommand() {
    return c_sampleauto;
  }

}
