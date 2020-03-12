/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  public AddressableLEDBuffer led_buffer = new AddressableLEDBuffer(30 * 5);
  public AddressableLED m_led = new AddressableLED(0);

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    m_led.setLength(led_buffer.getLength());
    m_led.start();
//     int r, g, b = 0; 
//     for (int i = 0; i < led_buffer.getLength(); i++) {
//  r = (int)(255 - 2.5*i);
// if(i < 50){
//   g = 0;
// } else {
//  g = (int)(0 + 2.5*i); 
//  b = 0; 
// }
// if(r < 6){
//   r = 0;
// }
// // if(g < 50){
// //   g = 0;
// // }
// if(b > 254){
//   b = 254;
// }
      
//         led_buffer.setRGB(i, r, g, b);
        
      
      
//       m_led.setData(led_buffer);
//       Timer.delay(.05);
//     }
    

    // Start reading usb cameras
    //CameraServer.getInstance().startAutomaticCapture();
    /*led_buffer.setRGB(0, 0, 0xff, 0);
    int sw = 0;
    for (int i = 0; i < led_buffer.getLength(); i++){
      //led_buffer.setRGB(i, 0, 0xff, 0);
      led_buffer.setRGB(i, (int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
      sw = sw == 2 ? 0 : sw + 1;
    }
    m_led.setLength(led_buffer.getLength());
    m_led.start();
    m_led.setData(led_buffer);*/
  }
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    rainbow();

    
  }
  
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    //if (m_autonomousCommand != null) {
    //  m_autonomousCommand.schedule();
    //}
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
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }

  private void rainbow(){
    int rainbowPixelhue = 0; 
    
    for (int k = 0; k < 150; k++){
      for(var i = 0; i < led_buffer.getLength(); i++){
      
      final var hue = (rainbowPixelhue = (i * 180 / led_buffer.getLength()) + 10*k) % 180; 
      led_buffer.setHSV(i, hue, 255, 255 );
      m_led.setData(led_buffer);
    Timer.delay(.005);

    }
    rainbowPixelhue +=5;
    rainbowPixelhue %= 180;

    //led_buffer.setRGB(79, 255, 0, 0);
//set led

// for(var i = 0; i < led_buffer.getLength(); i++){
      
//   final var hue = (rainbowPixelhue = (i * 180 / led_buffer.getLength())) % 180; 
//   led_buffer.setHSV(i, 0, 0, 0 );
//   m_led.setData(led_buffer);
//   Timer.delay(.02);

// }
// rainbowPixelhue +=3;
// rainbowPixelhue %= 180;
   
  
     
  }
}

  private void move(){
    AddressableLEDBuffer temp = new AddressableLEDBuffer(150);
    temp = led_buffer; 
    for(int i = 0; i < led_buffer.getLength(); i++){
      //  led_buffer.setHSV(index, h, s, v);
    }
  }

  // private void fade(){
  //   int r=0, g = 0, b = 0; 
  //   for(r = 0; r < 256; r ++){
  //     for (int i = 0; i < led_buffer.getLength(); i++){
  //       //led_buffer.setRGB(i, 0, 0xff, 0);
  //       led_buffer.setRGB(i, r, g, b);
  //       led_buffer.
        
  //     }
    
  //     m_led.setData(led_buffer);
  //   }
  //   for(b = 255; r > 0; b --){
  //     for (int i = 0; i < led_buffer.getLength(); i++){
  //       //led_buffer.setRGB(i, 0, 0xff, 0);
  //       led_buffer.setRGB(i, r, g, b);
        
  //     }
  //   }
  //   for(g = 0;g < 256; g ++){
  //     for (int i = 0; i < led_buffer.getLength(); i++){
  //       //led_buffer.setRGB(i, 0, 0xff, 0);
  //       led_buffer.setRGB(i, r, g, b);
        
  //     }
  //   }
  //   for(r = 255; r >0; r --){
  //     for (int i = 0; i < led_buffer.getLength(); i++){
  //       //led_buffer.setRGB(i, 0, 0xff, 0);
  //       led_buffer.setRGB(i, r, g, b);
        
  //     }
  //   }
  //   for(b = 0; b < 256; b ++){
  //     for (int i = 0; i < led_buffer.getLength(); i++){
  //       //led_buffer.setRGB(i, 0, 0xff, 0);
  //       led_buffer.setRGB(i, r, g, b);
        
  //     }
  //   }
  //   for(g = 255; g > 0;g --){
  //     for (int i = 0; i < led_buffer.getLength(); i++){
  //       //led_buffer.setRGB(i, 0, 0xff, 0);
  //       led_buffer.setRGB(i, r, g, b);
        
  //     }
    
  
  
}
