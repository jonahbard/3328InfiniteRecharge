/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
//import frc.robot.OI;
import frc.robot.commands.DriveJoystick;
//import frc.robot.Robot;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class Drive extends Subsystem {

  double inchesConversion = 34;

  CANSparkMax FL = new CANSparkMax(RobotMap.FLWheel, MotorType.kBrushless);
  CANSparkMax ML = new CANSparkMax(RobotMap.MLWheel, MotorType.kBrushless);
  CANSparkMax BL = new CANSparkMax(RobotMap.BLWheel, MotorType.kBrushless);
  CANSparkMax FR = new CANSparkMax(RobotMap.FRWheel, MotorType.kBrushless);
  CANSparkMax MR = new CANSparkMax(RobotMap.MRWheel, MotorType.kBrushless);
  CANSparkMax BR = new CANSparkMax(RobotMap.BRWheel, MotorType.kBrushless);
  CANEncoder leftFrontEncoder = new CANEncoder(FL);
  CANEncoder leftMiddleEncoder = new CANEncoder(ML);
  CANEncoder leftBackEncoder = new CANEncoder(BL);
  CANEncoder rightFrontEncoder = new CANEncoder(FR);
  CANEncoder rightMiddleEncoder = new CANEncoder(MR);
  CANEncoder rightBackEncoder = new CANEncoder(BR);


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveJoystick());
  }

  public double getDriveFR(){
    return rightFrontEncoder.getPosition();
  }
  public double getDriveMR(){
    return rightMiddleEncoder.getPosition();
  }

  public double getDriveBR(){
    return rightBackEncoder.getPosition();
  }

  public double getDriveFL(){
    return leftFrontEncoder.getPosition();
  }

  public double getDriveML(){
    return leftMiddleEncoder.getPosition();
  }

  public double getDriveBL(){
    return leftBackEncoder.getPosition();
  }

  public double getDriveL(){
    return (getDriveFL() + getDriveML() + getDriveBL())/3;
  }

  public double getDriveR(){
    return (getDriveFR() + getDriveMR() + getDriveBR())/3;
  }

  public double getDrive(){
    return (getDriveR() + getDriveL())/2;
  }
  public void setDriveL(double speed){
    FL.set(speed);
    ML.set(speed);
    BL.set(speed);
  }
  public void setDriveR(double speed){
    FR.set(speed);
    MR.set(speed);
    BR.set(speed);
  }
  public void setDrive(double leftSpeed, double rightSpeed){
    setDriveL(leftSpeed);
    setDriveR(rightSpeed);
  }

  public void goP(int direction, double speed, double distance, double maxError){
    double targetVal = distance*inchesConversion;
    double maxErrorVal = distance*inchesConversion;
    double error = targetVal - Math.abs(getDrive());
    double kP = 0.2;
    double pVal = error*kP;
    while (Math.abs(getDrive()) < targetVal - maxErrorVal){
      setDrive(pVal*direction*speed, pVal*direction*speed);
    }
  }


  public void turnP(int direction, double speed, double distance, double maxError){
    double targetVal = distance*inchesConversion;
    double maxErrorVal = distance*inchesConversion;
    double error = targetVal - (Math.abs(getDriveL()) +  Math.abs(getDriveL())/2);
    double kP = 0.2;
    double pVal = error*kP;
    while ((Math.abs(getDriveL()) < targetVal - maxErrorVal) || (Math.abs(getDriveL()) < targetVal - maxErrorVal)){
      setDrive(pVal*direction*speed*-1, pVal*direction*speed);
    }
  }
}