// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AutoBalanceCmd;
import frc.robot.commands.MechJoystickCmd;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.Drivetrain.SwerveSubsystem;
import frc.robot.subsystems.Mechanisms.ElevatorSubsystem;
import frc.robot.Constants.MechConstants;


public class RobotContainer {

  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
  private final ElevatorSubsystem mechSubsystem = new ElevatorSubsystem();
  private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
  private final Joystick mechJoystick = new Joystick(OIConstants.kMechControllerPort);
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //Auto Routines
  private static final String kNoAuto = "No Auto";
  private static final String kAutoBalance = "Auto Balance";
  private static String m_autoCommand = "";
  

  public RobotContainer() {
    swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
      swerveSubsystem,
      () -> -driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
      () -> driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
      () -> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis),
      () -> !driverJoystick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)
    ));

    mechSubsystem.setDefaultCommand(new MechJoystickCmd(
      mechSubsystem,
     () -> mechJoystick.getRawAxis(MechConstants.kMechExtenderIdx),
     () -> mechJoystick.getRawAxis(MechConstants.kMechAngleIdx),
     () -> mechJoystick.getRawButton(MechConstants.kMechInButton),
     () -> mechJoystick.getRawButton(MechConstants.kMechOutButton),
     () -> mechJoystick.getRawAxis(MechConstants.kMechCubeIntake)));
    
    configureButtonBindings();

    m_chooser.setDefaultOption("No Auto", kNoAuto);
    m_chooser.addOption("Auto Balance", kAutoBalance);
    SmartDashboard.putData("Auto Mode: ", m_chooser);
  }


  private void configureButtonBindings() {
    //TODO: This is depreciated, but it works for now. Will implement new method at a later date.
    new JoystickButton(driverJoystick, 2).whenPressed(() -> swerveSubsystem.zeroHeading());
    CameraServer.startAutomaticCapture();
  }

  public Command getAutonomousCommand() {
    m_autoCommand = m_chooser.getSelected();
    swerveSubsystem.zeroHeading();
    if (m_autoCommand == kAutoBalance) {
      return (new AutoBalanceCmd(swerveSubsystem, mechSubsystem));
    } else {
      return null;
    }
  }
}
