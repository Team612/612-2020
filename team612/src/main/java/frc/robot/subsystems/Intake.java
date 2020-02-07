/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private boolean INTAKE_MODE = false;

  // Spark objects for intake flywheel, belt, and outtake
  private Spark spark_intake = new Spark (Constants.SPARK_INTAKE);
  private Spark spark_upper_belt = new Spark (Constants.SPARK_UPPER_BELT);
  private Spark spark_lower_belt = new Spark (Constants.SPARK_LOWER_BELT);
  private Spark spark_outtake = new Spark (Constants.SPARK_OUTTAKE);

  // Ultrasonic sensors
  //private Ultrasonic ultrasonic_belt_right = new Ultrasonic(Constants.ULTRASONIC_BELT_RIGHT[0],Constants.ULTRASONIC_BELT_RIGHT[1]);
  public static Ultrasonic ultrasonic_belt = new Ultrasonic(4, 5);
  private double ultrasonicThreshold = 9; // Ultrasonic Threshold in inches 

  boolean hasTimeOut = false;

  // Piston objects for intake and arm grabber
  private DoubleSolenoid solenoid_intake = new DoubleSolenoid(Constants.SOLENOID_INTAKE_FORWARD, Constants.SOLENOID_INTAKE_REVERSE);
  private DoubleSolenoid solenoid_arm = new DoubleSolenoid(Constants.SOLENOID_ARM_FORWARD, Constants.SOLENOID_ARM_REVERSE);

  public void extendIntake() {
    // Push out the arm and intake forward
    solenoid_intake.set(Value.kForward);
    System.out.println("Extend intake");

    
    /*
    // Stop pistons from running to conserve pressurized air
    solenoid_l_intake.set(Value.kOff);
    solenoid_r_intake.set(Value.kOff);
    solenoid_arm.set(Value.kOff);
    */
  }

  public void retractIntake(){
    // Retract out the arm and intake to go back to original setup
    solenoid_intake.set(Value.kReverse);
    System.out.println("retracted intake");


    /*
    // Stop pistons from running to conserve pressurized air
    solenoid_l_intake.set(Value.kOff);
    solenoid_r_intake.set(Value.kOff);
    solenoid_arm.set(Value.kOff);
    */
  }

  public void setFlyWheels(double intake_speed, double belt_speed){
    // set the intake and belt to a certain speed
    System.out.println("setting flywheel");
    spark_intake.set(intake_speed);
    spark_lower_belt.set(-belt_speed);

    // If balls fall out, apply a negative constant to outtake to keep balls in 
    spark_outtake.set(-0);

    System.out.println(isWithinThreshold(ultrasonic_belt.getRangeInches()));
    System.out.println(hasTimeOut);
    if (isWithinThreshold(ultrasonic_belt.getRangeInches()) ){
      if (!hasTimeOut) {
        Timer.delay(.5);
        hasTimeOut = true;
      }
       
      System.out.println("&^%$#%^&*&^%$%^&Stop!!!");
      spark_upper_belt.set(0);
      solenoid_arm.set(Value.kForward);
    } else {
      hasTimeOut = false;
      solenoid_arm.set(Value.kReverse);
      System.out.println("yeetus");
      spark_upper_belt.set(belt_speed);
    }

  }

  public boolean isWithinThreshold(double range){
    if(range <= ultrasonicThreshold){
      return true;
    }
    return false;
  }

  public void setOuttake(double speed){
    // Set the outtake spark to a certain speed
    spark_outtake.set(speed);
    System.out.println("running outtake flywheel");

  }

  public void setIntakeMode(boolean state) {
    INTAKE_MODE = state;
  }


  public boolean getIntakeMode() {
    return INTAKE_MODE;
  }

  public Intake() {
    // ultrasonic_belt_left.setEnabled(true);
    ultrasonic_belt.setEnabled(true);
    ultrasonic_belt.setAutomaticMode(true);
    

  }

  @Override
  public void periodic() {
    System.out.println("Kai's Ultrasonic! : "+ultrasonic_belt.getRangeInches());
  }


}
