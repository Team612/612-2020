package frc.robot.commands.wheel;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wheel;


public class SpinToColor extends CommandBase {
    
  private char[] colorPattern = {'R', 'Y', 'B', 'G'};  // Pattern of wheel colors
  
  private int offset = 2;  // Color offset (actual vs. target)

  private char targetColor; // Actual target value (FMS)
  private char sensorTarget;  // Sensor target value (Since two colors behind on wheel) 
  private char currentColor;  // Current sensor value
  private char initialColor;

  private boolean isComplete = false;  // Variable to end the command

  private Wheel m_wheel;  // Local subsystem from wheel

  private double spinnerSpeed = 0.25;
  private boolean isFirst = false;

  public SpinToColor(Wheel m_wheel) {
    // Create and add requirements for subsystem
    this.m_wheel = m_wheel;
    addRequirements(m_wheel);
      // Get game data and end function if values not in drivetrain

  }

  @Override
  public void initialize() {
    getGameData();
    isComplete = false;
    initialColor = m_wheel.getClosestColor();
    isFirst = true;
  }

  @Override
  public void execute() {
    
    currentColor = m_wheel.getClosestColor();  // Current sensor reading updating each loop

    if (currentColor == sensorTarget && isFirst) {
      m_wheel.setSpinner(spinnerSpeed);
    } else {
      isFirst = false;

      m_wheel.setSpinner(spinnerSpeed);  // Run the spinner at full speed

      if (currentColor == sensorTarget) {
        isComplete = true;  // Once on the target value, stop the command
      }

      // SmartDashboard color values
      SmartDashboard.putString("Current Color Reading", String.valueOf(currentColor));
      SmartDashboard.putString("Target Color", String.valueOf(targetColor));
      SmartDashboard.putString("Sensor Target Color", String.valueOf(sensorTarget));
    }

    System.out.println("color: "+ currentColor);
      System.out.println("target: "+ sensorTarget);
      System.out.println("game target"+ targetColor);
      System.out.println(isFirst);

  }

  @Override
  public void end(boolean interrupted) {
    m_wheel.setSpinner(0);  // Stop spinner
  }

  @Override
  public boolean isFinished() {
   // m_wheel.colorPiston.set(Value.kReverse);
    return isComplete;
  }

  /* ----- Custom Functions ----- */

  private int getIndex(char[] array) {
    for (int i = 0; i < array.length; i++) {
      if (targetColor == colorPattern[i]) {
        return i; 
      }
    }
    return -1;
  }

  private void getGameData() {
    // Check if the driver station has a color to be fetched
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      targetColor = gameData.charAt(0);  // Get the color char from driver station
      int targetIndex = (getIndex(colorPattern) + offset) % 4;  // Get the color two steps from FMS reading
      sensorTarget = colorPattern[targetIndex];  // Set the actual sensor color target (actual reading is always 2 colors off from where to stop)
    } else {  // If not, end the command
      System.out.println("No color in FMS!");
      end(true);
    }
  }
  
}
