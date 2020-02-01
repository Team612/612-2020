/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private boolean INTAKE_MODE = false;

  // Spark objects for intake flywheel, belt, and outtake
  private Spark spark_intake = new Spark(Constants.SPARK_INTAKE);
  private Spark spark_belt = new Spark(Constants.SPARK_BELT);
  private Spark spark_outtake = new Spark(Constants.SPARK_OUTTAKE);

  // Piston objects for intake and arm grabber
  private DoubleSolenoid solenoid_l_intake = new DoubleSolenoid(Constants.SOLENOID_L_INTAKE[0], Constants.SOLENOID_L_INTAKE[1]);
  private DoubleSolenoid solenoid_r_intake =  new DoubleSolenoid(Constants.SOLENOID_R_INTAKE[0], Constants.SOLENOID_R_INTAKE[1]);
  private DoubleSolenoid solenoid_arm = new DoubleSolenoid(Constants.SOLENOID_ARM[0], Constants.SOLENOID_ARM[1]);

  public void extendIntake() {
    // Push out the arm and intake forward
    solenoid_l_intake.set(Value.kForward);
    solenoid_r_intake.set(Value.kForward);
    solenoid_arm.set(Value.kForward);
    
    // Stop pistons from running to conserve pressurized air
    solenoid_l_intake.set(Value.kOff);
    solenoid_r_intake.set(Value.kOff);
    solenoid_arm.set(Value.kOff);
  }

  public void retractIntake(){
    // Retract out the arm and intake to go back to original setup
    solenoid_l_intake.set(Value.kReverse);
    solenoid_r_intake.set(Value.kReverse);
    solenoid_arm.set(Value.kReverse);
    
    // Stop pistons from running to conserve pressurized air
    solenoid_l_intake.set(Value.kOff);
    solenoid_r_intake.set(Value.kOff);
    solenoid_arm.set(Value.kOff);
  }

  public void setFlyWheel(double speed){
    // set the intake and belt to a certain speed
    spark_intake.set(speed);
    spark_belt.set(speed);

    // If balls fall out, apply a negative constant to outtake to keep balls in 
    spark_outtake.set(-0);
  }

  public void setOuttake(double speed){
    // Set the outtake spark to a certain speed
    spark_outtake.set(speed);
  }

  public void setIntakeMode(boolean state) {
    INTAKE_MODE = state;
  }

  public boolean getIntakeMode() {
    return INTAKE_MODE;
  }

  public Intake() {
  }

  @Override
  public void periodic() {
  }


}
