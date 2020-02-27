/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;


public class LED {
    AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(20); // change later
    AddressableLED m_led = new AddressableLED(Constants.LED_PWM);

    int drivetrain_packet_size = 5;
    public LED(){
        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
    }    
    
    /*public void batcaveStyle(boolean is_forward){
        
    }

    public void turning(boolean is_turning){

    }*/

    public void ledPeriodic(){
        switch(Logger.getStatus()){
            case "WINCH_REVERSE":
            winchReverse();

            case "WINCH_FORWARD":
            winchForward();

            case "INTAKE_RUNNING":
            intake();

            case "OUTTAKE_RUNNING":
            outtake();

            case "DRIVETRAIN_FORWARD":
            drivetrainForward();

            case "DRIVETRAIN_REVERSE":
            drivetrainReverse();

            case "RESTING": 
            resting();
            
            default:
            defaultLED();
            
        }
    }

    public void winchForward(){
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
    }
     public void winchReverse(){
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

    public void drivetrainForward(){
        // yellow 
        for(int i = 0; i < m_ledBuffer.getLength(); i += drivetrain_packet_size){
            
            for (int j = i; j < i + drivetrain_packet_size; j++){
                m_ledBuffer.setRGB(j, 255, 255, 0);
            }
            m_led.setData(m_ledBuffer);
            if (i > 0){
                for (int k = i - drivetrain_packet_size; k < i; k++){
                    m_ledBuffer.setRGB(k, 0, 0, 0);
                }
                m_led.setData(m_ledBuffer);
            }
        }
    }

    public void drivetrainReverse(){
       for(int i = m_ledBuffer.getLength() - 1; i > 0; i -= drivetrain_packet_size){
        
        // load up the ship
        for (int j = i; j > i - drivetrain_packet_size; j--){
            m_ledBuffer.setRGB(j, 255, 255, 0);
        }
        
        // send it over
        m_led.setData(m_ledBuffer);

        if (i < m_ledBuffer.getLength() - 1){ 
            for(int k = i; k < i - drivetrain_packet_size; k--){
                m_ledBuffer.setRGB(k, 0, 0, 0);

            }
            m_led.setData(m_ledBuffer);
        }
        m_led.setData(m_ledBuffer); //maybe 
       }
    }

    public void resting(){
        // blue
        for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, 205, 0, 255);
            }

            m_led.setData(m_ledBuffer);

            for (int i = 0; i < m_ledBuffer.getLength(); i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, 0, 0, 0);
            }
            m_led.setData(m_ledBuffer);
    }
    public void defaultLED(){
        String allianceColor = allianceColor();
        
        if(allianceColor.equals("R")){
            for (int j = 1; j < 100; j ++){
                for (int i = 0; i < m_ledBuffer.getLength(); i++){
                    m_ledBuffer.setHSV(i, 0, 100, j);
                }
            }
            for (int k = 100; k >= 1; k --){
                for (int i = 0; i < m_ledBuffer.getLength(); i++){
                    m_ledBuffer.setHSV(i, 0, 100, k);
                }
            }
        } else if (allianceColor.equals("B")){
            for (int j = 1; j < 100; j ++){
                for (int i = 0; i < m_ledBuffer.getLength(); i++){
                    m_ledBuffer.setHSV(i, 240, 100, j);
                }
            }
            for (int k = 100; k >= 1; k --){
                for (int i = 0; i < m_ledBuffer.getLength(); i++){
                    m_ledBuffer.setHSV(i, 240, 100, k);
                }
            }
        } else {
            System.out.println("Looooollllllll this is spedddddd sdfsd ");
        }

    }

    public String allianceColor(){
        if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Invalid)){
            return DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue) ? "B" : "R";
        }
        return "I";
    }

    public void colorWheel(boolean isSpinning){
    //red || green

    }






}
