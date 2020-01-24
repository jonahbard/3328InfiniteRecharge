/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class DriveJoystick extends Command {
  public DriveJoystick() {
    requires(Robot.drive);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(Math.abs(Robot.oi.getJoystick(OI.LEFT_AXIS_Y)) > .15 || Math.abs(Robot.oi.getJoystick(OI.RIGHT_AXIS_Y)) > .15){
      Robot.drive.setDriveL(Math.pow((Robot.oi.getJoystick(OI.LEFT_AXIS_Y)*-1), 3));
      Robot.drive.setDriveR(Math.pow((Robot.oi.getJoystick(OI.RIGHT_AXIS_Y)*-1), 3));
     }
     else{
       Robot.drive.setDriveL(0);
       Robot.drive.setDriveR(0);
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
