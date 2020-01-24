package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class IntakeJoystick extends Command {
  public IntakeJoystick() {
    requires(Robot.intake);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Math.abs(Robot.oi.getJoystick(OI.R2)) > .15){
      Robot.intake.intake(1, 1);
    }
    else if(Robot.oi.getController(OI.BTNR1)){
      Robot.intake.intake(-1, 1);
    }
    else{
      Robot.intake.intake(0, 0);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
