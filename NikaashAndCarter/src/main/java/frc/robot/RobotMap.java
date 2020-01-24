/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  //drive
  public static int FRWheel = 5;
  public static int MRWheel = 4;
  public static int BRWheel = 3;
  public static int FLWheel = 2;
  public static int MLWheel = 1;
  public static int BLWheel = 0;
  public static int Flywheel1 = 6;
  public static int Flywheel2 = 7;
  //intake
  public static int Intake = 8;
  public static int IntakeEncoderA = 1;
  public static int IntakeEncoderB = 2; 
  public static int Feeder = 9;
  public static int Indexer = 10;
  public static int IndexerEncoderA = 3;
  public static int IndexerEncoderB = 4;
  //vision
  public static int Limelight = 0;
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
