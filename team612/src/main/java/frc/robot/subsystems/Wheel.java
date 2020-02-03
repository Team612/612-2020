package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import org.ejml.dense.row.decomposition.svd.SafeSvd_DDRM;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Wheel extends SubsystemBase {

  // Spark object to move color wheel mechanism
  private final Spark spark_wheel = new Spark(Constants.SPARK_WHEEL);

  // Target values for each color value (RGB values from manual)
  private final Color kBlueTarget = ColorMatch.makeColor(.143, .427, .429);
  private final Color kGreenTarget = ColorMatch.makeColor(.197, .561, .240);
  private final Color kRedTarget = ColorMatch.makeColor(.561, .232, .114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  //private String colourVal;

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
    colorMatcher.setConfidenceThreshold(0.00);
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
    } else if (match.color == kYellowTarget) {
      SmartDashboard.putString("Current Colour", "Yellow");
      return 'Y';
    } else {
      SmartDashboard.putString("Current Colour", "Error");
      return 0;
    }
    
  }

  public void setSpinner(double speed) {
    spark_wheel.set(speed);
  }

  public Wheel() {
    createMatches();  // Create the matches for the color matcher
  }

  @Override
  public void periodic() {
  }

}
