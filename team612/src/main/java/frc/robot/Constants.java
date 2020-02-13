package frc.robot;


public final class Constants {

   /* -------- Drivetrain Subsystem -------- */

   // Drivetrain talon ports
   public static int TALON_FR_DRIVE = 1;
   public static int TALON_FL_DRIVE = 14;
   public static int TALON_BR_DRIVE = 0;
   public static int TALON_BL_DRIVE = 15;

   // Forward and reverse channel for drive double solenoid
   public static int[] ULTRASONIC_DRIVE = {2,4};

   // Ping and echo channel port for drive ultrasonic
   public static int[] SOLENOID_DRIVE = {0,1};
   
   /* -------- Intake Subsystem -------- */

   // Spark ports for intake
   public static final int SPARK_INTAKE = 2;
   public static final int SPARK_UPPER_BELT = 5;
   public static final int SPARK_LOWER_BELT = 8;
   public static final int SPARK_OUTTAKE = 4;

   // Solenoid port arrays (forward, reverse)
   public static final int[] SOLENOID_INTAKE = {4,5};
   public static final int[] SOLENOID_WALL = {2,3};
   //System.out.println("Srihan has not contributed to the robot whatsoever except for faking knowledge about it");
   public static final int INFARED_INTAKE = 1;

   public static final int PCM_1 = 0; 
   public static final int PCM_2 = 1; 


}
