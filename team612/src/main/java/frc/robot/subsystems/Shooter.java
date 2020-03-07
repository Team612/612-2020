/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

  private final CANSparkMax spark_shooter = new CANSparkMax(Constants.SPARK_SHOOTER, MotorType.kBrushless);
  private final Spark spark_belt = new Spark(Constants.SPARK_BELT);

  public void setShooter(double speed) {
    spark_shooter.set(speed);
    System.out.println("Velocity:" + spark_shooter.getEncoder().getVelocity());
    System.out.println("-----");

  }

  public void setBelt(double speed) {
    spark_belt.set(speed);
  }

  public Shooter() {
  }
  
  @Override
  public void periodic() {
  }

}
