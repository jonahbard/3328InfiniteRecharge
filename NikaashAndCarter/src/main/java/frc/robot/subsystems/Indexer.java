package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.RobotMap;
import frc.robot.commands.IndexerJoystick;;

/**
 * Add your docs here.
 */
public class Indexer extends Subsystem {
  public TalonSRX IndexerMotor = new TalonSRX(RobotMap.Indexer);
  Encoder indexerEncoder = new Encoder(RobotMap.IndexerEncoderA, RobotMap.IndexerEncoderB, false, Encoder.EncodingType.k4X);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IndexerJoystick());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void index(double speed){
    IndexerMotor.set(ControlMode.PercentOutput, speed);
  }
}
