package frc.robot.commands.autonomous;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.intake.RunIntake;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class StartReplay extends CommandBase {
  

  private String DIRECTORY = "/home/lvuser/";  // Directory to ROBORIO

  private boolean END_REPLAY = false;  // Boolean to end the function if neccesary

  // Variables modified by ParseReplay.java
  private JSONArray frames;  // Empty parser object to fetch information
  private double recording_voltage; 

  private int i;  // Current step in replay

  private boolean isComplete;
  
  private Drivetrain m_drivetrain;
  private Intake m_intake;
  
  String FILENAME;

  Timer timestamp = new Timer();

  public StartReplay(Drivetrain m_drivetrain, String FILENAME) {
    this.FILENAME = FILENAME;
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
  }


  @Override
  public void initialize() {

    timestamp.stop();
    timestamp.reset();
    timestamp.start();

    JSONObject parsed = parse_json(DIRECTORY + FILENAME);

    // Extract voltage and array of steps from parsed json
    recording_voltage = (double) parsed.get("voltage");
    frames = (JSONArray) parsed.get("frames");
   

    //System.out.println(frames);
    i=0;
    END_REPLAY=false;
  }


  @Override
  public void execute() {

    if (i < frames.size()) {  // Only replay for length of array

      JSONObject frame = (JSONObject) frames.get(i);  // Get new frame

      // Get axis values from 0th port (driver), in axes list
      double forward = (double) getValueFromReplay(frame, 0, "axes", 1);
      double turn = (double) getValueFromReplay(frame, 0, "axes", 4);

      // Factor in differing voltage in recorded vs current values
      forward *= getVoltageCompensation();
      turn *= getVoltageCompensation();

      System.out.println("Old" + (double) frame.get("time"));
      System.out.println("New" + timestamp.get());

      m_drivetrain.arcadeDrive(0, forward);
      System.out.println(i);

      
      /*
      boolean INTAKE = (boolean) getValueFromReplay(frame, 1, "buttons", 4);
      boolean OUTTAKE = (boolean) getValueFromReplay(frame, 1, "buttons", 4);
      boolean FLYWHEEL = (boolean) getValueFromReplay(frame, 1, "buttons", 4);
      */
      System.out.println(forward);
      System.out.println(turn);
      System.out.println(getVoltageCompensation());
      /*
      System.out.println(INTAKE);
      System.out.println(OUTTAKE);
      System.out.println(FLYWHEEL);
      */
      System.out.println("----------");

      /*
      ControlMap.RUN_INTAKE_REPLAY.setPressed(INTAKE);
      ControlMap.RUN_FLYWHEEL_REPLAY.setPressed(FLYWHEEL);
      ControlMap.RUN_OUTTAKE_REPLAY.setPressed(OUTTAKE);
      */

      //TODO: plug joystick values back into drivetrain.

      i++;  // Increase to next frame

    } else {
      END_REPLAY = true;  // End the command once done
      System.out.println("Done!");
    }

  }


  @Override
  public void end(boolean interrupted) {
    timestamp.stop();
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