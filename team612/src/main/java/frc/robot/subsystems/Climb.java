/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Climb extends SubsystemBase {

  // Climb winch spark
  private WPI_TalonSRX talon_winch_climb = new WPI_TalonSRX(Constants.TALON_WINCH_CLIMB);

  // Climb solenoids
  private DoubleSolenoid solenoid_engage_climb = new DoubleSolenoid(Constants.PCM_2, Constants.SOLENOID_ENGAGE_CLIMB[0],Constants.SOLENOID_ENGAGE_CLIMB[1]);
  private DoubleSolenoid solenoid_toggle_hook = new DoubleSolenoid(Constants.PCM_1, Constants.SOLENOID_TOGGLE_HOOK[0],Constants.SOLENOID_TOGGLE_HOOK[1]);

  // Toggle the climb mechanism up or down
  public void toggleClimb() {
    if (solenoid_engage_climb.get() == Value.kForward){
      solenoid_engage_climb.set(Value.kReverse);
    } else {
      solenoid_engage_climb.set(Value.kForward);
    }
  }
  
  // Toggle the climb hook up or down
  public void toggleHook() {
    // If the climb is engaged
    if (solenoid_engage_climb.get() == Value.kForward) {
      if (solenoid_toggle_hook.get() == Value.kReverse) {
         solenoid_toggle_hook.set(Value.kForward);
      } else {
        solenoid_toggle_hook.set(Value.kReverse);
      }
    }
  }

  // Run the winch to actually climb
  public void setWinch(double speed){
    talon_winch_climb.set(speed);
  }

  public Climb() {
    solenoid_engage_climb.set(Value.kReverse);
    solenoid_toggle_hook.set(Value.kReverse);
  }
  
  @Override
  public void periodic() {
  }
  
}
