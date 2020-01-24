package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.RobotMap;
import frc.robot.commands.IntakeJoystick;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  public TalonSRX IntakeMotor = new TalonSRX(RobotMap.Intake);
  Encoder intakEncoder = new Encoder(RobotMap.IntakeEncoderA, RobotMap.IntakeEncoderB, false, Encoder.EncodingType.k4X);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeJoystick());
    // Set the default command for a subsystem here.
  }
  public void intake(int direction, double speed){
    IntakeMotor.set(ControlMode.PercentOutput, speed*direction);
  }
}
