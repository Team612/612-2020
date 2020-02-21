/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

/**
 * Add your docs here.
 */
public class LED {
    AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(20); // change later
    AddressableLED m_led = new AddressableLED(Constants.LED_PWM);

    public LED(){
        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
    }    
    
    /*public void batcaveStyle(boolean is_forward){
        
    }

    public void turning(boolean is_turning){

    }*/

    public void winch(){
        // blue
        for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, 0, 0, 255);
         }

         m_led.setData(m_ledBuffer);

         for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, 0, 0, 0);
         }
         
         m_led.setData(m_ledBuffer);
    }
    public void intake(){
        //yellow
        for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, 255, 255, 0);
         }

         m_led.setData(m_ledBuffer);

         for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, 0, 0, 0);
         }
         
         m_led.setData(m_ledBuffer);
    
    }
    public void outtake(){
    //pink
    for (int i = 0; i < m_ledBuffer.getLength(); i++) {
        // Sets the specified LED to the RGB values for red
        m_ledBuffer.setRGB(i, 255, 192, 203);
     }

     m_led.setData(m_ledBuffer);

     for (int i = 0; i < m_ledBuffer.getLength(); i++) {
        // Sets the specified LED to the RGB values for red
        m_ledBuffer.setRGB(i, 0, 0, 0);
     }
     
     m_led.setData(m_ledBuffer);
    }
    public void colorWheal(boolean isSpinning){
    //red || green

    }




}
