// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;


public class SetShooterSpeed extends Command {
  private final ShooterSubsystem m_shooter;
  private final DoubleSupplier m_leftSpeedSup;
  private final DoubleSupplier m_rightSpeedSup;

  /** Creates a new CenterAprilTag. */
  public SetShooterSpeed(ShooterSubsystem shooter, DoubleSupplier leftSpeedSup,  DoubleSupplier  rightSpeedSup) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
    m_leftSpeedSup = leftSpeedSup;
    m_rightSpeedSup = rightSpeedSup;
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double leftSpeedVal = MathUtil.applyDeadband(m_leftSpeedSup.getAsDouble(), Constants.stickDeadband);
    double rightSpeedVal = MathUtil.applyDeadband(m_rightSpeedSup.getAsDouble(), Constants.stickDeadband);

    m_shooter.setShooterSpeeds(leftSpeedVal, rightSpeedVal);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
