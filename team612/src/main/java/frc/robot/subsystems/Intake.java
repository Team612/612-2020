/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
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

  // Piston objects for intake and arm grabber
  private DoubleSolenoid solenoid_intake = new DoubleSolenoid(Constants.SOLENOID_INTAKE[0], Constants.SOLENOID_INTAKE[1]);
  private DoubleSolenoid solenoid_wall = new DoubleSolenoid(Constants.SOLENOID_WALL[0], Constants.SOLENOID_WALL[1]);

  //Setting up analog input IR sensor
  AnalogInput IRSensor = new AnalogInput(1);
  double IRThreshhold = 0;

  public void extendIntake() {
    // Push out the arm and intake forward
    solenoid_intake.set(Value.kForward);
  }

  public void retractIntake(){
    // Retract out the arm and intake to go back to original setup
    solenoid_intake.set(Value.kReverse);
    System.out.println("Retracted intake");
  }

  public void setFlyWheels(double intake_speed, double belt_speed){
    // set the intake and belt to a certain speed
    System.out.println("Running intake flywheels");

    spark_intake.set(intake_speed);
    spark_lower_belt.set(-belt_speed);

    

    if (IRSensor.getAverageVoltage() > 0.7) {
      System.out.println("Ball in chamber!");
      spark_upper_belt.set(0);
    } else {
      spark_upper_belt.set(belt_speed);
    }
    // If balls fall out, apply a negative constant to outtake to keep balls in 
    spark_outtake.set(-0.2);

  }

  public void setOuttake(double speed){
    // Set the outtake spark to a certain speed
    spark_outtake.set(speed);
    System.out.println("Running outtake flywheel");

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

    System.out.println(IRSensor.getVoltage());
  }


}
