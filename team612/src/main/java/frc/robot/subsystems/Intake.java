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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private boolean INTAKE_MODE = false;

  // Spark objects for intake flywheel, belt, and outtake
  private final Spark spark_intake = new Spark (Constants.SPARK_INTAKE);
  private final Spark spark_upper_belt = new Spark (Constants.SPARK_UPPER_BELT);
  private final Spark spark_lower_belt = new Spark (Constants.SPARK_LOWER_BELT);
  private final Spark spark_outtake = new Spark (Constants.SPARK_OUTTAKE);

  // Piston objects for intake and arm grabber
  private final DoubleSolenoid solenoid_intake = new DoubleSolenoid(Constants.SOLENOID_INTAKE[0], Constants.SOLENOID_INTAKE[1]);
  private final DoubleSolenoid solenoid_wall = new DoubleSolenoid(Constants.SOLENOID_WALL[0], Constants.SOLENOID_WALL[1]);

  //Setting up analog input IR sensor
  private final AnalogInput infared_intake = new AnalogInput(Constants.INFARED_INTAKE);
  private final double INFARED_THRESHOLD = 0.7;

  public void extendIntake() {
    // Push out the arm and intake forward
    solenoid_intake.set(Value.kForward);
    solenoid_wall.set(Value.kReverse);
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

    // If the upper infared senses a ball, stop the upper belt and engage the wall piston
    if (infared_intake.getAverageVoltage() > INFARED_THRESHOLD) {
      System.out.println("Ball in chamber!");
      solenoid_wall.set(Value.kReverse);
      spark_upper_belt.set(0);
    } else {
      spark_upper_belt.set(belt_speed);
      solenoid_wall.set(Value.kForward);
      spark_outtake.set(0);
    }
   

  }

  public void setOuttake(double speed){
    solenoid_wall.set(Value.kForward);

    // Set the outtake spark to a certain speed
    System.out.println("Running outtake flywheel");
    spark_outtake.set(speed);
    spark_lower_belt.set(-speed);
    spark_upper_belt.set(speed);

  }

  public Intake() {
    solenoid_wall.set(Value.kForward);
  }

  @Override
  public void periodic() {

    INTAKE_MODE = solenoid_intake.get() == Value.kForward;
    SmartDashboard.putBoolean("Intake Enabled", INTAKE_MODE);

  }


}
