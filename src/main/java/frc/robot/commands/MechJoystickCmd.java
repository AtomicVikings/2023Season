package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Mechanisms.ElevatorArm;

public class MechJoystickCmd extends CommandBase {
    Supplier<Boolean> raiseButton, lowerButton;
    ElevatorArm elevatorSubsystem;


    public MechJoystickCmd(ElevatorArm elevatorSubsystem, Supplier<Boolean> raiseButton, Supplier<Boolean> lowerButton) {
        raiseButton = this.raiseButton;
        lowerButton = this.lowerButton;
        elevatorSubsystem = this.elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if(raiseButton.get()) {
            elevatorSubsystem.setMotor(.2);
        } else if (lowerButton.get()) {
            elevatorSubsystem.setMotor(-.2);
        } else {
            elevatorSubsystem.setMotor(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.setMotor(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
