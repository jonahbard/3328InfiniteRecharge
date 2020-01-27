/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.FlywheelPID;

/*import frc.robot.OI;
import frc.robot.commands.DriveJoystick;
import frc.robot.Robot;*/
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.*;

public class Flywheel extends Subsystem {
  CANSparkMax Fly1 = new CANSparkMax(RobotMap.Flywheel1, MotorType.kBrushless);
  CANSparkMax Fly2 = new CANSparkMax(RobotMap.Flywheel2, MotorType.kBrushless);
  CANEncoder Fly1Encoder = new CANEncoder(Fly1);
  CANEncoder Fly2Encoder = new CANEncoder(Fly2);

  double powerConversion = 1;

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

  public void flywheelPID(double targetSpeed){
    double kP = 1.2;
    while(true){
      double speedError = targetSpeed - getFlyVelocity();
      double pVal = speedError*kP;
      double finalPower = targetSpeed + pVal;
      setFlywheel(finalPower);
    }
  }

}
