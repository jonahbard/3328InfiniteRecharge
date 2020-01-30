/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.FlywheelPID;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/*import frc.robot.OI;
import frc.robot.commands.DriveJoystick;
import frc.robot.Robot;*/
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class Flywheel extends Subsystem {
  final double powerConversion = 1;
  final double hoodGearing = 0.2;
  final int SANCTION = 1;
  final int RETRACT = -1;
  public TalonSRX HoodMotor = new TalonSRX(RobotMap.HoodAdjuster);
  Encoder HoodEncoder = new Encoder(RobotMap.HoodEncoderA, RobotMap.HoodEncoderB, false, Encoder.EncodingType.k4X);
  CANSparkMax Fly1 = new CANSparkMax(RobotMap.Flywheel1, MotorType.kBrushless);
  CANSparkMax Fly2 = new CANSparkMax(RobotMap.Flywheel2, MotorType.kBrushless);
  CANEncoder Fly1Encoder = new CANEncoder(Fly1);
  CANEncoder Fly2Encoder = new CANEncoder(Fly2);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new FlywheelPID());
  }

  public void setFlywheel(double speed){
    Fly1.set(speed);
    Fly2.set(speed);
  }
  public double getFlyVelocity(){
    double FlySpeed = (Fly1.get() + Fly2.get())/2;
    return FlySpeed;
  }

  public double getFlyPower(){
    double FlyPower = (Fly1.getBusVoltage() + Fly2.getBusVoltage())/2;
    return FlyPower;
  }

  public double getFlyEncoder(){
    double FlyPosition = ((Fly1Encoder.getPosition()) + (Fly2Encoder.getPosition()))/2;
    return FlyPosition;
  }

  public void flywheelPID(double targetSpeed){
    double kP = 1.2;
    while(true){
      double speedError = targetSpeed - getFlyVelocity();
      double pVal = speedError*kP;
      double finalPower = targetSpeed + pVal;
      setFlywheel(finalPower);
    }
  }

  public double getHoodEncoder(){
    return HoodEncoder.get();
  }

  public void setHoodSpeed(double speed){
    HoodMotor.set(ControlMode.PercentOutput, speed);
  }
  public void setHood(int direction, double speed, double distance){
    while(Math.abs(getHoodEncoder()) < distance){
      setHoodSpeed(speed*direction);
    }
    setHoodSpeed(0);
  }
  public void hoodP(double maxError){
    double kP = 13*hoodGearing;
    while(Math.abs(getHoodEncoder() - (Robot.limelight.getTargetDistance())*kP) > maxError){;
      double errorDirection = (Math.abs(getHoodEncoder() - (Robot.limelight.getTargetDistance())*kP))/(getHoodEncoder() - (Robot.limelight.getTargetDistance())*kP);
      setHoodSpeed(1*errorDirection*-1);
    }
    setHoodSpeed(0);
  }
}
