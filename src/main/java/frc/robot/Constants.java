// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class ModuleConstants {
    public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
    public static final double kDriveMotorGearRatio = 1 / 8.14;
    public static final double kTurningMotorGearRatio = 1 / 21.43;
    public static final double kTurnEncoderCountsPerRot = 2048 * 21.43;
    public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
    public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
    public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
    public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;
    public static final double kPTurning = .2;
    public static final double kITurning = 0;
    public static final double kDTurning = 0.006;
  }

  public final static class DriveConstants {
    // Drive Dimensions
    public static final double kTrackWidth = Units.inchesToMeters(23.75);
    public static final double kWheelBase = Units.inchesToMeters(23.75);
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
      new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
      new Translation2d(kWheelBase / 2, kTrackWidth / 2),
      new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
      new Translation2d(-kWheelBase / 2, kTrackWidth / 2));
    

    // Drive Ports and Settings
    public static final int kFrontLeftDriveMotorPort = 10;
    public static final int kFrontRightDriveMotorPort = 6;
    public static final int kBackLeftDriveMotorPort = 12;
    public static final int kBackRightDriveMotorPort = 5;

    public static final int kFrontLeftTurningMotorPort = 9;
    public static final int kFrontRightTurningMotorPort = 7;
    public static final int kBackLeftTurningMotorPort = 11;
    public static final int kBackRightTurningMotorPort = 8;

    public static final boolean kFrontLeftTurningEncoderReversed = false;
    public static final boolean kFrontRightTurningEncoderReversed = false;
    public static final boolean kBackLeftTurningEncoderReversed = false;
    public static final boolean kBackRightTurningEncoderReversed = false;

    public static final boolean kFrontLeftDriveEncoderReversed = false;
    public static final boolean kFrontRightDriveEncoderReversed = false;
    public static final boolean kBackLeftDriveEncoderReversed = false;
    public static final boolean kBackRightDriveEncoderReversed = false;

    public static final int kFrontLeftDriveAbsoluteEncoderPort = 2;
    public static final int kFrontRightDriveAbsoluteEncoderPort = 3;
    public static final int kBackLeftDriveAbsoluteEncoderPort = 0;
    public static final int kBackRightDriveAbsoluteEncoderPort = 1;

    public static final boolean kFrontLeftDriveAbsoluteEncoderReversed = true;
    public static final boolean kBackLeftDriveAbsoluteEncoderReversed = true;
    public static final boolean kFrontRightDriveAbsoluteEncoderReversed = true;
    public static final boolean kBackRightDriveAbsoluteEncoderReversed = true;

    public static final double kFrontLeftDriveAbsoluteEncoderOffsetRad =  4.06;
    public static final double kBackLeftDriveAbsoluteEncoderOffsetRad =   5.99;
    public static final double kFrontRightDriveAbsoluteEncoderOffsetRad = 3.15;
    public static final double kBackRightDriveAbsoluteEncoderOffsetRad =  1.40;

    // Limiters
    public static final double kPhysicalMaxSpeedMetersPerSecond = 4.17;
    public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
    public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI * .2;
    public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond * .9;
    public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
    public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 2;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kMechControllerPort = 1;

    public static final int kDriverYAxis = 1;
    public static final int kDriverXAxis = 0;
    public static final int kDriverRotAxis = 4;
    public static final int kDriverFieldOrientedButtonIdx = 1;
    public static final double kDeadband = 0.3;
  }

  public static final class MechConstants {
    public static final int kMechElevatorPrimaryId = 19;
    public static final int kMechElevatorSecondaryId = 20;
    public static final int kMechIntakePrimaryId = 17;
    public static final int kMechIntakeSecondaryId = 18;
    public static final int kMechAnglePrimaryId = 15;
    public static final int kMechAngleSecondaryId = 16;


    public static final int kMechExtenderIdx = 1; //Joystick axis
    public static final int kMechAngleIdx = 5;
    public static final int kMechCubeIntake = 2;

    public static final int kMechInButton = 5;  //Joystick buttons
    public static final int kMechOutButton = 6;
    

    //this applies to both intake and elevator
    public static final boolean kMechPrimaryInverted = false;
    public static final boolean kMechSecondaryInverted = true;
  }
}
