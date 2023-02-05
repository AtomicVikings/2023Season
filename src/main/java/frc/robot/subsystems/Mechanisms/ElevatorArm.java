package frc.robot.subsystems.Mechanisms;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechConstants;

public class ElevatorArm extends SubsystemBase {
    private final CANSparkMax elevatorPrimary = new CANSparkMax(MechConstants.kMechPrimaryId, MotorType.kBrushless);
    private final CANSparkMax elevatorSecondary = new CANSparkMax(MechConstants.kMechSecondaryId, MotorType.kBrushless);
    private final MotorControllerGroup elevatorGroup = new MotorControllerGroup(elevatorPrimary, elevatorSecondary);

    public ElevatorArm () {
        elevatorPrimary.setInverted(MechConstants.kMechPrimaryInverted);
        elevatorSecondary.setInverted(MechConstants.kMechSecondaryInverted);
    }

    public void setMotor ( double setValue ) {
        elevatorGroup.set(setValue);
    }

}

