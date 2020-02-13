/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  

  Compressor compressor = new Compressor(1);
  Compressor compressor_1 = new Compressor(0);

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    compressor.setClosedLoopControl(true);
    compressor_1.setClosedLoopControl(true);
    compressor.start();
    compressor_1.start();
    
    // Initialize and start compressor
    
    
  }
  
  @Override
  public void robotPeriodic() {
    compressor.clearAllPCMStickyFaults();
    compressor_1.clearAllPCMStickyFaults();
    System.out.println(compressor.getCompressorShortedStickyFault());
    System.out.println(compressor_1.getCompressorShortedStickyFault());
    System.out.println("The compressor is enabled? : "+compressor.enabled());
    System.out.println("The compressor2 is enabled? : "+compressor_1.enabled());
    CommandScheduler.getInstance().run();
   // System.out.println("This Code Was Made By Prahalad");

    //System.out.println(compressor.getCompressorCurrentTooHighFault());

  }
  
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
   // System.out.println("This Code Was Made By Prahalad");

  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    System.out.println("This Code Was Made By nobody");

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
  
}
