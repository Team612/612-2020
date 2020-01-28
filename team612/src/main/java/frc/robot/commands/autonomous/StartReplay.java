package frc.robot.commands.autonomous;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;

public class StartReplay extends CommandBase {
  

  private String DIRECTORY = "/home/lvuser/";  // Directory to ROBORIO

  private boolean END_REPLAY = false;  // Boolean to end the function if neccesary

  // Variables modified by ParseReplay.java
  private JSONArray frames;  // Empty parser object to fetch information
  private double recording_voltage; 

  private int i;  // Current step in replay

  private boolean isComplete;
  
  private Drivetrain m_drivetrain;
  

  public StartReplay(Drivetrain m_drivetrain, String FILENAME) {
    JSONObject parsed = parse_json(DIRECTORY + FILENAME);
    this.m_drivetrain = m_drivetrain;
    // Extract voltage and array of steps from parsed json
    recording_voltage = (double) parsed.get("voltage");
    frames = (JSONArray) parsed.get("frames");
    addRequirements(m_drivetrain);
  }


  @Override
  public void initialize() {
    //System.out.println(frames);
    i=0;
    END_REPLAY=false;
  }


  @Override
  public void execute() {

    if (i < frames.size()) {  // Only replay for length of array

      JSONObject frame = (JSONObject) frames.get(i);  // Get new frame

      // Get axis values from 0th port (driver), in axes list
      double leftSide = (double) getValueFromReplay(frame, 0, "axes", ControlMap.left_y_axis);
      double rightSide = (double) getValueFromReplay(frame, 0, "axes", ControlMap.right_y_axis);

      // Factor in differing voltage in recorded vs current values
      leftSide = leftSide * getVoltageCompensation();
      rightSide = rightSide * getVoltageCompensation();

      System.out.println(leftSide);
      System.out.println(rightSide);
      System.out.println("------");
      m_drivetrain.tankDrive(leftSide, rightSide);



      //TODO: plug joystick values back into drivetrain.

      i++;  // Increase to next frame
    } else {
      END_REPLAY = true;  // End the command once done
      System.out.println("Done!");
    }

  }


  @Override
  public void end(boolean interrupted) {
  }


  @Override
  public boolean isFinished() {
    return END_REPLAY;
  }

  /* ----- Custom Functions Below ----- */

  private JSONObject parse_json(String file_name) {

    JSONParser jsonParser = new JSONParser();  // Create parser object

    try (FileReader reader = new FileReader(file_name)) {

      Object obj = jsonParser.parse(reader);  // Parsed object from JSON
      System.out.println(obj);
      return (JSONObject) obj;  // Cast and return JSON object

      // File opening error handling
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist!");
    } catch (IOException e) {
      System.out.println("Unable to read file!");
    } catch (ParseException e) {
      System.out.println("Invalid JSON format!");
    }

    // If nothing returned by parser end the command
    end(true);
    return null;

  }

  // Get factor to multiply motor values to factor battery depletion
  private double getVoltageCompensation() {
    return RobotController.getBatteryVoltage() / recording_voltage;
  }

  // Function simplify otherwise sloppy parsing in JSON
  private Object getValueFromReplay(JSONObject step, int joystick_port, String type, int port) {
    return (Object) ((JSONObject) ( (JSONObject) step.get(Integer.toString(joystick_port)) ).get(type) ).get(Integer.toString(port));
  }


}