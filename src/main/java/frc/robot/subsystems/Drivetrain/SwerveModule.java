package frc.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.lib.Math.EncoderConvert;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ModuleConstants;

public class SwerveModule {
    private final WPI_TalonFX driveMotor;
    private final WPI_TalonFX turnMotor;
    private final TalonFXSensorCollection driveEncoder;
    private final TalonFXSensorCollection turnEncoder;
       

    private final AnalogInput absoluteEncoder;
    private final boolean absoluteEncoderReversed;
    private final double absoluteEncoderOffsetRad;

    private final PIDController turnPID;

    public SwerveModule(int driveMotorId, int turnMotorId, boolean driveMotorReversed, boolean turnMotorReversed, int absoluteEncoderId,
     double absoluteEncoderOffset, boolean absoluteEncoderReversed) {

        this.absoluteEncoderOffsetRad = absoluteEncoderOffset;
        this.absoluteEncoderReversed = absoluteEncoderReversed;
        absoluteEncoder = new AnalogInput(absoluteEncoderId);

        driveMotor = new WPI_TalonFX(driveMotorId);
        turnMotor = new WPI_TalonFX(turnMotorId);
        driveMotor.setInverted(driveMotorReversed);
        turnMotor.setInverted(turnMotorReversed);

        driveEncoder = new TalonFXSensorCollection(driveMotor);
        turnEncoder = new TalonFXSensorCollection(turnMotor);

        turnPID = new PIDController(ModuleConstants.kPTurning, ModuleConstants.kITurning, ModuleConstants.kDTurning);
        turnPID.enableContinuousInput(-Math.PI, Math.PI);
        
        
        resetEncoders();
    }

    public double getDrivePosition() {
        // Returns the integrated position in Radians from -PI to PI
        return EncoderConvert.encoderToRad(driveEncoder.getIntegratedSensorAbsolutePosition());
    } 

    public double getTurningPosition() {
        // Returns the integrated position in Radians from -PI to PI
        return MathUtil.angleModulus(EncoderConvert.encoderToRad(turnEncoder.getIntegratedSensorPosition()));
    }

    public double getDriveVelocity() {
        // Returns the integrated velocity in Meters/Sec
        return EncoderConvert.rotToMeters(driveEncoder.getIntegratedSensorVelocity());
    }

    public double getTurningVelocity() {
        // Returns the integrated velocity in Meters/Sec
       return EncoderConvert.rotToMeters(turnEncoder.getIntegratedSensorVelocity());
    }

    public double getAbsoluteEncoderRad() {
        // Returns the absolute encoder (Thrifty Bot Absolute Encoder) in radians
        double angle = absoluteEncoder.getVoltage() / RobotController.getVoltage5V();
        angle *= 2.0 * Math.PI;
        if (angle - absoluteEncoderOffsetRad < 0) {
            angle = angle - absoluteEncoderOffsetRad + 2 * Math.PI;
        } else {
            angle -= absoluteEncoderOffsetRad;
        }
       
        return angle * (absoluteEncoderReversed ? -1.0 : 1.0);
        
    }

    public void resetEncoders() {
        driveEncoder.setIntegratedSensorPosition(0, 0);
        turnEncoder.setIntegratedSensorPosition(EncoderConvert.radToEncoder(getAbsoluteEncoderRad()), 0);

    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurningPosition()));
    }

    public void setDesiredState(SwerveModuleState state) {
        if (Math.abs(state.speedMetersPerSecond) < 0.001) {
            stop();
            return;
        }
        
        state = SwerveModuleState.optimize(state, getState().angle);
        driveMotor.set(state.speedMetersPerSecond / DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        turnMotor.set(turnPID.calculate(getTurningPosition(), state.angle.getRadians()));
        SmartDashboard.putString("Swerve[" + absoluteEncoder.getChannel() + "] state", state.toString());

    }

    public void stop() {
        driveMotor.set(0);
        turnMotor.set(0);
    }

}
