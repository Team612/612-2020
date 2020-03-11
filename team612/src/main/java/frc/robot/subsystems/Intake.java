package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.controls.ControlMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Intake extends SubsystemBase {

  // Spark max object for shooter belt
  public final CANSparkMax spark_shooter = new CANSparkMax(Constants.SPARK_SHOOTER, MotorType.kBrushless);

  // Spark objects for intake flywheel, belt, and outtake
  private final WPI_TalonSRX talon_intake = new WPI_TalonSRX(Constants.TALON_INTAKE);
  private final WPI_TalonSRX talon_lower_belt = new WPI_TalonSRX(Constants.TALON_LOWER_BELT);
  private final WPI_TalonSRX talon_index = new WPI_TalonSRX(Constants.TALON_INDEX);

  // Piston objects for intake and arm grabber
  private final DoubleSolenoid solenoid_intake = new DoubleSolenoid(Constants.PCM_2, Constants.SOLENOID_INTAKE[0], Constants.SOLENOID_INTAKE[1]);

  // Run the shooter belt to shoot
  public void shoot(double speed) {
    spark_shooter.set(speed);
  }

  // Run the lower belt ball feeder to the shooter
  public void runFeeder(double speed) {
    talon_lower_belt.set(speed);
  }

  public double getShooterVelocity() {
    return spark_shooter.getEncoder().getVelocity();
  }

  // Toggle the intake solenoids in/out
  public void toggleIntake() {
    if (solenoid_intake.get() == Value.kForward){
      solenoid_intake.set(Value.kReverse);
    } else {
      solenoid_intake.set(Value.kForward);
    }
  }

  // Run the talon to index the balls 1 by 1
  public void runIndexer(double speed) {
    talon_index.set(speed);
  }

  // Jog the intake flywheel to move stuck balls, controlled by triggers
  public void runIntake() {
    talon_intake.set(ControlMap.gunner.getRawAxis(2) - ControlMap.gunner.getRawAxis(3));
  }

  public Intake() {
    talon_lower_belt.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
  }

}
