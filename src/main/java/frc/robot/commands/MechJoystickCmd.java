package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Mechanisms.ElevatorSubsystem;

public class MechJoystickCmd extends CommandBase {
    Supplier<Boolean> inButton, outButton;
    Supplier<Double> elevatorAxis, angleAxis, inCube;
    ElevatorSubsystem elevatorSubsystem;


    public MechJoystickCmd(ElevatorSubsystem elevatorSubsystem, Supplier<Double> elevatorAxis, Supplier<Double> angleAxis, Supplier<Boolean> inButton, Supplier<Boolean> outButton, Supplier<Double> inCube) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.elevatorAxis = elevatorAxis;
        this.angleAxis = angleAxis;
        this.inButton = inButton;
        this.outButton = outButton;
        this.inCube = inCube;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        elevatorSubsystem.setExtender(elevatorAxis.get());
        elevatorSubsystem.setAngle(angleAxis.get());

        if(inButton.get()) {
            elevatorSubsystem.setIntake(1);
        } else if (outButton.get()) {
            elevatorSubsystem.setIntake(-1);
        } else if (inCube.get() > .8) {
            elevatorSubsystem.setIntake(.2);

        } else {
            elevatorSubsystem.setIntake(0);
        }

        elevatorSubsystem.getElevatorPosition();
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.setExtender(0);
        elevatorSubsystem.setAngle(0);
        elevatorSubsystem.setIntake(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
