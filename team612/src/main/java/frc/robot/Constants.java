package frc.robot;

public final class Constants {

    public static boolean ENABLE_ARCADE = true;

    // PCM Ports
    public static final int PCM_1 = 0;
    public static final int PCM_2 = 1;

    /* -------- Drivetrain Subsystem -------- */

    
    // Drivetrain talon ports
    public static int SPARK_FR_DRIVE = 2;//59
    public static int SPARK_FL_DRIVE = 13;
    public static int SPARK_BR_DRIVE = 3;
    public static int SPARK_BL_DRIVE = 5;

    // Forward and reverse channel for drive double solenoid
    public static int[] ULTRASONIC_DRIVE = {2,4};

    // Ping and echo channel port for drive ultrasonic
    public static int[] SOLENOID_DRIVE = {0,1};//1

    /* -------- Intake Subsystem -------- */

    // Spark ports for intake
    public static final int SPARK_INTAKE = 4;
    public static final int SPARK_UPPER_BELT = 1;
    public static final int SPARK_LOWER_BELT = 7;
    public static final int SPARK_OUTTAKE = 0;

    // Solenoid port arrays (forward, reverse)
    public static final int[] SOLENOID_WALL = {4,5};//1
    public static final int[] SOLENOID_INTAKE = {2,3};//1
    public static final int INFARED_INTAKE = 3;
    public static final int INFARED_JUMP = 1;

    /* -------- Climb Subsystem -------- */

    // Climb solenoids
    public static final int[] SOLENOID_ENGAGE_CLIMB = {7,6};//1
    public static final int[] SOLENOID_TOGGLE_HOOK = {0,1};//0

    // Climb sparks

    public static final int SPARK_WINCH_CLIMB = 14;

    //Color Wheel 15, PCM: 0 , {2,3}; 
    public static final int SPARK_WHEEL = 15;
    public static final int[] COLOR_PISTON = {2,3};
    

}