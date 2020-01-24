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


/**
 * Add your docs here.
 */
public class Flywheel extends Subsystem {
  CANSparkMax Fly1 = new CANSparkMax(RobotMap.Flywheel1, MotorType.kBrushless);
  CANSparkMax Fly2 = new CANSparkMax(RobotMap.Flywheel2, MotorType.kBrushless);
  CANEncoder Fly1Encoder = new CANEncoder(Fly1);
  CANEncoder Fly2Encoder = new CANEncoder(Fly2);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new FlywheelPID());
  }

  public void setFlywheel(double speed){
    Fly1.set(speed);
    Fly2.set(speed);
  }

  public void flywheelPID(double speed){
    Fly1.set(speed);
    Fly2.set(speed);
  }

}
