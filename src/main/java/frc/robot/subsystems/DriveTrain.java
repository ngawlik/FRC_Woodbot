/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

public class DriveTrain extends Subsystem {
  private final SpeedController motorLeft = new Jaguar(0);
  private final SpeedController motorRight = new Jaguar(1);
  private final DifferentialDrive drive = new DifferentialDrive(motorLeft, motorRight);
  private final Encoder encoderLeft = new Encoder(0, 1);
  private final Encoder encoderRight = new Encoder(2, 3);

  public DriveTrain() {
    // Set encoder resolution
    encoderLeft.setDistancePerPulse(RobotMap.DRIVE_ENCODER_RESOLUTION);
    encoderRight.setDistancePerPulse(RobotMap.DRIVE_ENCODER_RESOLUTION);
		
    // Set encoder samples to average
    encoderLeft.setSamplesToAverage(RobotMap.DRIVE_ENC_SAMPLES_TO_AVERAGE);
    encoderRight.setSamplesToAverage(RobotMap.DRIVE_ENC_SAMPLES_TO_AVERAGE);
		
    // Start encoders
    encoderLeft.reset();
    encoderRight.reset();

    // Set motor drive directions (left backward, right forward)
    motorLeft.setInverted(RobotMap.DRIVE_MOTOR_LEFT_TELE_DIR);
    motorRight.setInverted(RobotMap.DRIVE_MOTOR_RIGHT_TELE_DIR);
  }

  public void driveArcade(double moveEffort, double rotateEffort) {
    drive.arcadeDrive(moveEffort, rotateEffort, true);
  }

  public void driveTank(double leftEffort, double rightEffort) {
    drive.tankDrive(leftEffort, rightEffort, true);
  }

  public double getDistLeft() {
    return encoderLeft.getDistance();
  }

  public double getDistRight() {
    return encoderRight.getDistance();
  }

  public double getRateLeft() {
    return encoderLeft.getRate();
  }

  public double getRateRight() {
    return encoderRight.getRate();
  }

	public void resetEncoder() {
		encoderLeft.reset();
		encoderRight.reset();
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArcadeDrive());
  }
}
