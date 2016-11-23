
package org.usfirst.frc.team610.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
/* FOR SOME STUPID REASON THESE SAY "NEVER USED" SO UNCOMMENT WHEN U USE THEM BECAUSE THEY USE UP MEMORY
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
*/
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Talon leftFront, leftMid, leftBack;
	Talon rightFront, rightMid, rightBack;
	Talon catapult;
	Talon leftCancer, rightCancer;
	Joystick driver;
	Joystick operator;
	DigitalInput cam;
	Encoder rourke;
	double left;
	double right;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		leftFront = new Talon(0);
		leftMid = new Talon(1);
		leftBack = new Talon(2);

		rightFront = new Talon(3);
		rightMid = new Talon(4);
		rightBack = new Talon(5);

		catapult = new Talon(8);

		leftCancer = new Talon(7);
		rightCancer = new Talon(6);
		
		
		//Logitech controller 1 (one with opera+ on it)
		driver = new Joystick(1);
		
		cam = new DigitalInput(9);
		rourke = new Encoder(1, 2);

	}


	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	
	public void teleopPeriodic() {
		
		leftFront.set(left);
		leftMid.set(left);
		leftBack.set(left);
		
		rightFront.set(-right);
		rightMid.set(right);
		rightBack.set(right);
		   
		left = -driver.getRawAxis(1);
		right = driver.getRawAxis(1);
		
		left += driver.getRawAxis(2);
		right += driver.getRawAxis(2);
		
		
		SmartDashboard.putBoolean("Sensor", cam.get());
		
	if (driver.getRawButton(8) || (cam.get())) {
			catapult.set(-1.0);
			
			} else if (!cam.get()) {
				catapult.set(0.0);
		}

		//Eyebrow intake, only the spinners not the pneumatics
		
		if (driver.getRawButton(5)) {
			leftCancer.set(1.0);
			rightCancer.set(-1.0);

		} else if (driver.getRawButton(6)) {
			leftCancer.set(-1.0);
			rightCancer.set(1.0);

		} else {

			leftCancer.set(0.0);
			rightCancer.set(0.0);

		}

	}

	/*
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
