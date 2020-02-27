/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.climb.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.subsystems.Drivetrain;
/**
 * Add your docs here.
 */
public class Logger {


    public static String getStatus(){
        if(ReverseWinch.isRunning){
            return "WINCH_REVERSE";
        }
        else if(RunWinch.isRunning){
          return "WINCH_FORWARD";
        }
        //else if(colorwheel){
      
        //}
        else if(RunIntake.isRunning){
            return "INTAKE_RUNNING";
        }
        else if(RunOuttake.isRunning){
            return "OUTTAKE_RUNNING";
        }
        else if(DefaultDrive.isRunning){
            if (Drivetrain.isForward){
                return "DRIVETRAIN_FORWARD";
            }

            else if(!Drivetrain.isForward){
                return "DRIVETRAIN_REVERSE";
            }
        }
        return "RESTING";
    }

}
