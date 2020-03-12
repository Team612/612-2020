package frc.robot;

public final class Constants {

    // Run arcade or tank drive
    public static boolean ENABLE_ARCADE = true;

    /* -------- Drivetrain Subsystem -------- */

    // Drivetrain talon ports
    public static int TALON_FR_DRIVE = 1;
    public static int TALON_FL_DRIVE = 14;
    public static int TALON_BR_DRIVE = 0;
    public static int TALON_BL_DRIVE = 15;

    // Forward and reverse channel for drive double solenoid
    public static int[] ULTRASONIC_DRIVE = {2,4};

    // Ping and echo channel port for drive ultrasonic
    public static int[] SOLENOID_DRIVE = {12,13};

    /* -------- Intake Subsystem -------- */

    // Spark ports for intake
    public static final int SPARK_INTAKE = 7;
    public static final int SPARK_UPPER_BELT = 9;
    public static final int SPARK_LOWER_BELT = 8;
    public static final int SPARK_OUTTAKE = 4;

    // Solenoid port arrays (forward, reverse)
    public static final int[] SOLENOID_WALL = {2,3};
    public static final int[] SOLENOID_INTAKE = {4,5};
    public static final int INFARED_INTAKE = 1;
    public static final int INFARED_JUMP = 3;

    /* -------- Climb Subsystem -------- */

    // Climb solenoids
    public static final int[] SOLENOID_ENGAGE_CLIMB = {6,7};
    public static final int[] SOLENOID_TOGGLE_HOOK = {0,1};

    // Climb sparks
    public static final int SPARK_WINCH_CLIMB = 11;

    //led ports
    //public static final int LED_PWM = 42069;

}
