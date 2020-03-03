package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ControlMap {

    // Controller objects
    public static Joystick driver = new Joystick(0);
    public static Joystick gunner = new Joystick(1);

    // Driver buttons
    public static JoystickButton driver_button_A    	= new JoystickButton(driver,1);
    public static JoystickButton driver_button_B 		= new JoystickButton(driver,2);
    public static JoystickButton driver_button_X    	= new JoystickButton(driver,3);
    public static JoystickButton driver_button_Y    	= new JoystickButton(driver,4);
    public static JoystickButton driver_button_LB   	= new JoystickButton(driver,5);
    public static JoystickButton driver_button_RB   	= new JoystickButton(driver,6);
    
    public static JoystickButton driver_button_BCK  	= new JoystickButton(driver,7);
    public static JoystickButton driver_button_STRT 	= new JoystickButton(driver,8);
    public static JoystickButton driver_button_LJ   	= new JoystickButton(driver,9);
    public static JoystickButton driver_button_RJ   	= new JoystickButton(driver,10);

    // Intake buttons
    public static JoystickButton RUN_INTAKE    	        = new JoystickButton(gunner,7);// back button
    public static JoystickButton RUN_OUTTAKE            = new JoystickButton(gunner,8);// start
    public static JoystickButton RUN_FLYWHEEL           = new JoystickButton(gunner,9); // Left joystick press

    // Climb buttons
    public static JoystickButton ENGAGE_CLIMB           = new JoystickButton(gunner,2); // B
    public static JoystickButton TOGGLE_HOOK            = new JoystickButton(gunner,3); // X
    public static JoystickButton RUN_WINCH              = new JoystickButton(gunner,10); // Joyright

    // Color wheel buttons
    public static JoystickButton ENGAGE_COLOR_WHEEL = new JoystickButton(gunner, 4); //Y
    public static JoystickButton ROTATE_WHEEL = new JoystickButton(gunner, 1); // A
    public static JoystickButton SPIN_TO_COLOR = new JoystickButton(gunner, 5);
    
    

}