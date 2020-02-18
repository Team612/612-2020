/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {

  // Initilizing solenoids
  private DoubleSolenoid solenoid_engage_climb = new DoubleSolenoid(0, Constants.SOLENOID_ENGAGE_CLIMB[0],Constants.SOLENOID_ENGAGE_CLIMB[1]);
  private DoubleSolenoid solenoid_toggle_hook = new DoubleSolenoid(0, Constants.SOLENOID_TOGGLE_HOOK[0],Constants.SOLENOID_TOGGLE_HOOK[1]);
  private Spark spark_winch_climb = new Spark(Constants.SPARK_WINCH_CLIMB);

  public Climb() {
  }

  // TODO: make this a toggle
  /*public void engageClimb() {
    if(solenoid_engage_climb.get() == Value.kForward){
    solenoid_engage_climb.set(Value.kReverse);
    } else {
      solenoid_engage_climb.set(Value.kForward);
    }
  }*/
  public void engageClimb(){
    if (solenoid_engage_climb.get() == Value.kForward){
      solenoid_engage_climb.set(Value.kReverse);
    } else {
      solenoid_engage_climb.set(Value.kForward);
    }
  }
  public void toggleClimb() {

    // If the climb is engaged
    if (solenoid_engage_climb.get() == Value.kForward) {

      // Toggle the status of the hook solenoid
      if (solenoid_toggle_hook.get() == Value.kReverse) {
         solenoid_toggle_hook.set(Value.kForward);
      } else {
        solenoid_toggle_hook.set(Value.kReverse);
      }

    }
  }

  // Run the Winch to actually climb
  public void setWinch(double speed){
    System.out.println("Running Winch!");
    spark_winch_climb.set(speed);
  }

  @Override
  public void periodic() {
    System.out.println("climb: "+solenoid_engage_climb.get());
    System.out.println("toggle:"+solenoid_toggle_hook.get());
  }
  
}
