package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Wheel extends SubsystemBase {

  // Spark object to move color wheel mechanism
  private final WPI_TalonSRX talon_wheel = new WPI_TalonSRX(Constants.TALON_WHEEL);

  // Target values for each color value (RGB values from manual)
  private final Color kRedTarget = ColorMatch.makeColor(.561, .232, .114);
  private final Color kBlueTarget = ColorMatch.makeColor(.143, .427, .429);
  private final Color kGreenTarget = ColorMatch.makeColor(.197, .561, .240);
  private final Color kYellowTarget = ColorMatch.makeColor(.361, .524, .113);
  //private String colourVal;

  // Piston for engaging the spin of colour wheel
  public DoubleSolenoid colorPiston = new DoubleSolenoid(Constants.PCM_1, Constants.COLOR_PISTON[0], Constants.COLOR_PISTON[1]);

  // The matcher that map the sensor value to one of the targets
  public ColorMatch colorMatcher = new ColorMatch();

  // Create the color sensor object
  public ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

  public void createMatches() {
    // Create the color matches
    colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kGreenTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorMatcher.addColorMatch(kYellowTarget);

    // Create the threshold for the matcher
    colorMatcher.setConfidenceThreshold(0.2);
  }
   
  public char getClosestColor() {

    // Read the color sensor and get the closest match
    ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor()); 

    // Get string version of color
    if (match.color == kBlueTarget) {
      SmartDashboard.putString("Current Colour", "Blue");
      return 'B';
    } else if (match.color == kGreenTarget) {
      SmartDashboard.putString("Current Colour", "Green");
      return 'G';
    } else if (match.color == kRedTarget) {
      SmartDashboard.putString("Current Colour", "Red");
      return 'R';
    } else {
      SmartDashboard.putString("Current Colour", "Yellow");
      return 'Y';
    }
    
  }

  // Run the spinner at a certain speed
  public void setSpinner(double speed) {
    talon_wheel.set(speed);
  }

  // Engage the color wheel piston
  public void engagePiston() {
    if (colorPiston.get() == Value.kForward){
      colorPiston.set(Value.kReverse);
    } else {
      colorPiston.set(Value.kForward);
    }
  }

  public Wheel() {
    createMatches();  // Create the matches for the color matcher
  }

  @Override
  public void periodic() {
  }

}
