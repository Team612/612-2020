package frc.robot;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.intake.*;
import frc.robot.controls.ControlMap;
import frc.robot.commands.climb.EngageClimb;
import frc.robot.commands.climb.ReverseWinch;
import frc.robot.commands.climb.RunWinch;
import frc.robot.commands.climb.ToggleHook;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Climb;
import frc.robot.commands.autonomous.EndRecord;
import frc.robot.commands.autonomous.StartRecord;
import frc.robot.commands.autonomous.StartReplay;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.wheel.ExtendColorWheel;
import frc.robot.commands.wheel.RotateWheel;
import frc.robot.commands.wheel.SpinToColor;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Wheel;

public class RobotContainer {

  // Drivetrain related subsystem and commands
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive c_drive = new DefaultDrive(m_drivetrain);
  
  private final Intake m_intake = new Intake();

  // Color wheel subsystem and commands
  private final Wheel m_wheel = new Wheel();

  // TODO: Replace auto_file with ShuffleBoard selector
  private String auto_file = "output.replay";
  private final StartReplay m_autocommand = new StartReplay(m_drivetrain, auto_file);

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

    ControlMap.ROTATE_WHEEL.toggleWhenPressed(new RotateWheel(m_wheel));
    ControlMap.SPIN_TO_COLOR.toggleWhenPressed(new SpinToColor(m_wheel));
    ControlMap.ENGAGE_COLOR_WHEEL.whenPressed(new ExtendColorWheel(m_wheel));

    // Intake control bindings
    ControlMap.RUN_INTAKE_REPLAY.toggleWhenPressed(new RunIntake(m_intake));
    ControlMap.RUN_OUTTAKE_REPLAY.whileHeld(new RunOuttake(m_intake));
    ControlMap.RUN_FLYWHEEL_REPLAY.whileHeld(new RunFlywheel(m_intake));
    
    if (Constants.ENABLE_RECORDING) {
      System.out.println("Hi friend");
      ControlMap.driver_button_A.whenPressed(new StartRecord(auto_file));
      ControlMap.driver_button_B.whenPressed(new EndRecord());
    }
    /*
    // Intake control bindings
    ControlMap.RUN_INTAKE.toggleWhenPressed(new RunIntake(m_intake));
    ControlMap.RUN_OUTTAKE.whileHeld(new RunOuttake(m_intake));
    ControlMap.RUN_FLYWHEEL.whileHeld(new RunFlywheel(m_intake));

    // Climb control bindings
    ControlMap.ENGAGE_CLIMB.whenPressed(new EngageClimb(m_climb));
    ControlMap.TOGGLE_HOOK.whenPressed(new ToggleHook(m_climb));
    ControlMap.RUN_WINCH.whileHeld(new RunWinch(m_climb));
    */
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_drive);
  }

  public Command getAutonomousCommand() {
    return m_autocommand;
  }

}
