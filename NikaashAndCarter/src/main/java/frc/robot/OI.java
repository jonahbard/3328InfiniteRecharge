package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.command.Command;

public class OI {
  //constants for joystick
  //joystick axes
  public static final int RIGHT_AXIS_X = 1;
  public static final int RIGHT_AXIS_Y = 2;
  public static final int LEFT_AXIS_Y = 3;
  public static final int LEFT_AXIS_X = 4;
  //button numbers
  public static final int R2 = 5;
  public static final int L2 = 6;
  public static final int BTNX = 1;
  public static final int BTNO = 2;
  public static final int BTNSQUARE = 3;
  public static final int BTNTRIANGLE = 4;
  public static final int BTNL1 = 5;
  public static final int BTNR1 = 6;
  public static final int BTNOPTIONS = 7;
  public static final int BTNSHARE = 8;
  //controller deadzones
  public static final double STICK_DEADZONE = 0.1;
  public static final double TRIGGER_DEADZONE = 0.1;

  //joystick definitions
  public Joystick Controller = new Joystick(0);
  Button ButtonX = new JoystickButton(Controller, 1);
  Button ButtonO = new JoystickButton(Controller, 2);
  Button ButtonSquare = new JoystickButton(Controller, 3);
  Button ButtonTriangle = new JoystickButton(Controller, 4);
  Button ButtonL1 = new JoystickButton(Controller, 5);
  Button ButtonR1 = new JoystickButton(Controller, 6);
  Button ButtonOptions = new JoystickButton(Controller, 7);
  Button ButtonShare = new JoystickButton(Controller, 8);

  public double getJoystick(int analogNumber){
    return Controller.getRawAxis(analogNumber);
  }
  public boolean getController(int buttonNumber){
    return Controller.getRawButton(buttonNumber);
  }

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
