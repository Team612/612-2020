/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private static Spark spark_outtake = new Spark(Constants.SPARK_Outtake);
  private final Ultrasonic ultrasonic_outtake = new Ultrasonic(1, 2);
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    
 
  }
public void runOuttake(double speed){
  System.out.println("Helo");
spark_outtake.set(speed);
}

  public double getDistance() {
    return ultrasonic_outtake.getRangeInches();
  }


  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
