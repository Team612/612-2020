/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

  public final CANSparkMax spark_shooter = new CANSparkMax(Constants.SPARK_SHOOTER, MotorType.kBrushless);

  public void setShooter(double speed) {
    spark_shooter.set(speed);
    //System.out.println("Velocity:" + spark_shooter.getEncoder().getVelocity());
    //System.out.println("Speed:" + spark_shooter.get());
  }
  
  public Shooter() {
  }
  
  @Override
  public void periodic() {
  }

}
