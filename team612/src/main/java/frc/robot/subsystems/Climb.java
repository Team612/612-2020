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

public class Climb extends SubsystemBase {

  // Right solenoids for climb
  private DoubleSolenoid solenoid_rb_climb = new DoubleSolenoid(Constants.SOLENOID_RB_CLIMB.x, Constants.SOLENOID_RB_CLIMB.y);
  private DoubleSolenoid solenoid_rm_climb = new DoubleSolenoid(Constants.SOLENOID_RM_CLIMB.x, Constants.SOLENOID_RM_CLIMB.y);
  private DoubleSolenoid solenoid_rt_climb = new DoubleSolenoid(Constants.SOLENOID_RT_CLIMB.x, Constants.SOLENOID_RT_CLIMB.y);

  // Left solenoids for climb
  private DoubleSolenoid solenoid_lb_climb = new DoubleSolenoid(Constants.SOLENOID_LB_CLIMB.x, Constants.SOLENOID_LB_CLIMB.y);
  private DoubleSolenoid solenoid_lm_climb = new DoubleSolenoid(Constants.SOLENOID_LM_CLIMB.x, Constants.SOLENOID_LM_CLIMB.y);
  private DoubleSolenoid solenoid_lt_climb = new DoubleSolenoid(Constants.SOLENOID_LT_CLIMB.x, Constants.SOLENOID_LT_CLIMB.y);

  // State of climb hook (true = engaged)
  private boolean hookIsForward = false;

  // Return the current hook state
  public boolean getHookState(){
    return hookIsForward;
 }

  public void extendClimb(){

    // First, move the bottom climb piston up
    solenoid_rb_climb.set(Value.kForward);
    solenoid_lb_climb.set(Value.kForward);
    //solenoid_rb_climb.set(Value.kOff);
    //solenoid_lb_climb.set(Value.kOff);
    

    // Second, move the middle climb piston up
    solenoid_rm_climb.set(Value.kForward);
    solenoid_lm_climb.set(Value.kForward);
    //solenoid_rm_climb.set(Value.kOff);
    //solenoid_lm_climb.set(Value.kOff);

    // Third, move the top (hook) climb piston up
    solenoid_rt_climb.set(Value.kForward);
    solenoid_lt_climb.set(Value.kForward);
    //solenoid_rt_climb.set(Value.kOff);
    //solenoid_lt_climb.set(Value.kOff);

    hookIsForward = true;  // Set the initial state of the hook

  }

  public void toggleHook(){

    // If the hook is engaged
    if (getHookState()) {
      // Disengage the hook
      solenoid_rt_climb.set(Value.kReverse);
      solenoid_lt_climb.set(Value.kReverse);      
    } else {
      // Else, engage the hook
      solenoid_rt_climb.set(Value.kForward);
      solenoid_lt_climb.set(Value.kForward);      
    }

    // Set the hook pistons off and toggle the boolean value
    //solenoid_rt_climb.set(Value.kOff);
    //solenoid_lt_climb.set(Value.kOff);
    hookIsForward = !hookIsForward;

  }

  public Climb() {
  }

  @Override
  public void periodic() {
  }

}
