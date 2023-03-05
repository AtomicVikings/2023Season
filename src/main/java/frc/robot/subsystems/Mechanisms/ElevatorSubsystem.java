package frc.robot.subsystems.Mechanisms;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private final CANSparkMax elevatorPrimary = new CANSparkMax(MechConstants.kMechElevatorPrimaryId, MotorType.kBrushless);
    private final CANSparkMax elevatorSecondary = new CANSparkMax(MechConstants.kMechElevatorSecondaryId, MotorType.kBrushless);
    
    private final CANSparkMax intakePrimary = new CANSparkMax(MechConstants.kMechIntakePrimaryId, MotorType.kBrushless);
    private final CANSparkMax intakeSecondary = new CANSparkMax(MechConstants.kMechIntakeSecondaryId, MotorType.kBrushless);

    private final WPI_TalonFX anglePrimary = new WPI_TalonFX(MechConstants.kMechAnglePrimaryId);
    private final WPI_TalonFX angleSecondary = new WPI_TalonFX(MechConstants.kMechAngleSecondaryId);

    private final MotorControllerGroup intakeGroup = new MotorControllerGroup(intakePrimary, intakeSecondary);
    private final MotorControllerGroup elevatorGroup = new MotorControllerGroup(elevatorPrimary, elevatorSecondary);
    private final MotorControllerGroup angleGroup = new MotorControllerGroup(anglePrimary, angleSecondary);

    public ElevatorSubsystem () {
        elevatorPrimary.setInverted(MechConstants.kMechPrimaryInverted);
        elevatorSecondary.setInverted(MechConstants.kMechSecondaryInverted);
        intakePrimary.setInverted(MechConstants.kMechPrimaryInverted);
        intakeSecondary.setInverted(MechConstants.kMechSecondaryInverted);
        anglePrimary.setInverted(MechConstants.kMechPrimaryInverted);
        angleSecondary.setInverted(MechConstants.kMechSecondaryInverted);
    }

    public void setAngle ( double setValue ) {
        elevatorGroup.set(setValue);
    }

    public void setIntake (double setValue) {
        intakeGroup.set(setValue);
    }

    public void setExtender (double setValue) {
        angleGroup.set(setValue);
    }

}

