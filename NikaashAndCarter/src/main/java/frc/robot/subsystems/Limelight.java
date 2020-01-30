/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {
  final double mountingAngle = 30;
  final double mountingHeight = 35;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  //read values periodically
  public double getLimeX(){
    return tx.getDouble(100.0);
  }
  public double getLimeY(){
    return ty.getDouble(0.0);
  }
  public double getLimeArea(){
    return ta.getDouble(0.0);
  }
  public boolean checkHorizon(){
    if (Math.abs(getLimeX()) <= 4){
      return true;
    }
    else{
      return false;
    }
  }
  public double getTargetDistance(){
    double limeDistance = (83.5 - mountingHeight)/(Math.tan(mountingAngle + getLimeY()));
    return limeDistance;
  }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setLight(){
    
  }
}
