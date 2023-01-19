package frc.lib.Math;

import frc.robot.Constants.ModuleConstants;

public class EncoderConvert {
    public static double encoderToRad(double encoderVal) {
        encoderVal /= ModuleConstants.kTurnEncoderCountsPerRot;
        return encoderVal * 2 * Math.PI;
    }

    public static double radToEncoder(double radians) {
        radians /= (Math.PI * 2);
        return radians * ModuleConstants.kTurnEncoderCountsPerRot;
    }

    public static double rotToMeters(double rotations) {
        rotations = rotations / 2048; // Converts to RPM
        rotations = rotations / ModuleConstants.kDriveMotorGearRatio; // Converts motor RPM to wheel RPM
        return rotations * ModuleConstants.kWheelDiameterMeters;
    }
}
