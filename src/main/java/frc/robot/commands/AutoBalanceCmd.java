package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Drivetrain.SwerveSubsystem;
import frc.robot.subsystems.Mechanisms.AutoBalance;
import frc.robot.subsystems.Mechanisms.ElevatorSubsystem;

public class AutoBalanceCmd extends CommandBase {
    SwerveSubsystem swerveSub;
    ElevatorSubsystem mechSub;
    AutoBalance autoB;
    ChassisSpeeds cs;
    SwerveModuleState forwardState = new SwerveModuleState();    
    
    public AutoBalanceCmd(SwerveSubsystem swerveSub, ElevatorSubsystem mechSub) { 
        this.swerveSub = swerveSub;
        this.mechSub = mechSub;
        cs = new ChassisSpeeds();
        addRequirements(swerveSub);
    }

    @Override
    public void initialize() {
        forwardState.speedMetersPerSecond = 0;
        autoB = new AutoBalance();
    }

    @Override
    public void execute() {
        
        //Get scale of chassis speed 
        double autoVal = autoB.scoreAndBalance();
        cs.vxMetersPerSecond = -(autoVal) * DriveConstants.kPhysicalMaxSpeedMetersPerSecond;
        if (autoVal == 0) {
            end(isFinished());
        }
        //Commit speed to drivetrain
        SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(cs);
        swerveSub.setModuleStates(moduleStates);
        
    }

    

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
    
    @Override
    public void end(boolean interrupted) {
        swerveSub.stopModules();
        super.end(interrupted);
    }
}
