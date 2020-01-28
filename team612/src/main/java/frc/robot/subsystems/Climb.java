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

  //right solenoids for climb]
  private DoubleSolenoid solenoid_rb_climb = new DoubleSolenoid(Constants.SOLENOID_RB.x, Constants.SOLENOID_RB.y);
  private DoubleSolenoid solenoid_rm_climb = new DoubleSolenoid(Constants.SOLENOID_RM.x, Constants.SOLENOID_RM.y);
  private DoubleSolenoid solenoid_rt_climb = new DoubleSolenoid(Constants.SOLENOID_RT.x, Constants.SOLENOID_RT.y);

  //left solenoids for climb
  private DoubleSolenoid solenoid_lb_climb = new DoubleSolenoid(Constants.SOLENOID_LB.x, Constants.SOLENOID_LB.y);
  private DoubleSolenoid solenoid_lm_climb = new DoubleSolenoid(Constants.SOLENOID_LM.x, Constants.SOLENOID_LM.y);
  private DoubleSolenoid solenoid_lt_climb = new DoubleSolenoid(Constants.SOLENOID_LT.x, Constants.SOLENOID_LT.y);

  //state of climb
  private boolean isForward = false;

  public Climb() {

  }

  
  public boolean getClimbState(){
    return isForward;
 }


  public void extendClimb(){

    solenoid_rb_climb.set(Value.kForward);
    solenoid_lb_climb.set(Value.kForward);
    solenoid_rb_climb.set(Value.kOff);
    solenoid_lb_climb.set(Value.kOff);

    solenoid_rm_climb.set(Value.kForward);
    solenoid_lm_climb.set(Value.kForward);
    solenoid_rm_climb.set(Value.kOff);
    solenoid_lm_climb.set(Value.kOff);

    solenoid_rt_climb.set(Value.kForward);
    solenoid_lt_climb.set(Value.kForward);
    solenoid_rt_climb.set(Value.kOff);
    solenoid_lt_climb.set(Value.kOff);

    isForward = true;

  }

  public void toggle(){

    if(getClimbState()){
      solenoid_rt_climb.set(Value.kReverse);
      solenoid_lt_climb.set(Value.kReverse);      
    }
    
    else{
      solenoid_rt_climb.set(Value.kForward);
      solenoid_lt_climb.set(Value.kForward);      
    }
     solenoid_rt_climb.set(Value.kOff);
      solenoid_lt_climb.set(Value.kOff);
      isForward = !isForward;
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
