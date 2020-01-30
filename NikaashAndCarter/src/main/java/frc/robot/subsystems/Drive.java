package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
//import frc.robot.OI;
import frc.robot.commands.DriveJoystick;
//import frc.robot.Robot;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class Drive extends Subsystem {
  //drive constants
  final double inchesConversion = 268.3;
  final double degConversion = 2;
  final int RIGHT = 1;
  final int LEFT = -1;

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

  public void driveBrake(){
    FL.setIdleMode(IdleMode.kBrake);
    ML.setIdleMode(IdleMode.kBrake);
    BL.setIdleMode(IdleMode.kBrake);
    FR.setIdleMode(IdleMode.kBrake);
    MR.setIdleMode(IdleMode.kBrake);
    BR.setIdleMode(IdleMode.kBrake);
  }

  public void driveCoast(){
    FL.setIdleMode(IdleMode.kCoast);
    ML.setIdleMode(IdleMode.kCoast);
    BL.setIdleMode(IdleMode.kCoast);
    FR.setIdleMode(IdleMode.kCoast);
    MR.setIdleMode(IdleMode.kCoast);
    BR.setIdleMode(IdleMode.kCoast);
  }

  public void driveStop(){
    FL.stopMotor();
    ML.stopMotor();
    BL.stopMotor();
    FR.stopMotor();
    MR.stopMotor();
    BR.stopMotor();
  }

  public void goP(int direction, double speed, double distance, double maxError){
    double targetVal = distance*inchesConversion;
    double maxErrorVal = maxError*inchesConversion;
    double kP = 0.2;
    while (Math.abs(getDrive()) < targetVal - maxErrorVal){
      double error = targetVal - Math.abs(getDrive());
      double pVal = error*kP;
      setDrive(pVal*direction*speed, pVal*direction*speed);
    }
    driveBrake();
    driveStop();
  }

  public void turnP(int direction, double speed, double distance, double maxError){
    double targetVal = distance*degConversion;
    double maxErrorVal = maxError*degConversion;
    double kP = 0.2;
    while ((Math.abs(getDriveL()) < targetVal - maxErrorVal) || (Math.abs(getDriveR()) < targetVal - maxErrorVal)){
      double error = targetVal - ((Math.abs(getDriveL()) +  Math.abs(getDriveR()))/2);
      double pVal = error*kP;
      setDrive(pVal*direction*speed*-1, pVal*direction*speed);
    }
    driveBrake();
    driveStop();
  }

  public void swingP(double speedL, double speedR, double distL, double distR, double maxErrorL, double maxErrorR){
     double targetValL = distL*inchesConversion;
     double targetValR = distR*inchesConversion;
     double maxErrorValL = maxErrorL*inchesConversion;
     double maxErrorValR = maxErrorR*inchesConversion;
     double kP = 0.2;
     while ((Math.abs(getDriveL()) < targetValL - maxErrorValL) || (Math.abs(getDriveR()) < targetValR - maxErrorValR)){
      double errorL = targetValL - Math.abs(getDriveL());
      double errorR = targetValR - Math.abs(getDriveR());
      double pValL = errorL*kP;
      double pValR = errorR*kP;
      setDrive(pValL*speedL, pValR*speedR);
     }
     driveBrake();
     driveStop();
  }

  public void alignP(){
    double kP = 0.2;
    while (Math.abs(Robot.limelight.getLimeX()) > 0){
      double error = Robot.limelight.getLimeX();
      double pVal = error*kP;
      setDrive(pVal*-1, pVal);
    }
  }

  public void swingEncoder(double speedL, double speedR, double distL, double distR){
    
  }

  public void driveJam(){
    FL.set(-0.1);
    ML.set(0.1);
    BL.set(-0.1);
    FR.set(-0.1);
    MR.set(0.1);
    BR.set(-0.1);
  }
}
