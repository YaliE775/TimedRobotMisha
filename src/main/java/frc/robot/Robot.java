// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    private DigitalInput beamBreakerIn;
    private DigitalOutput beamBreakerOut;

    private TalonSRX rightMaster;
    private TalonSRX leftMaster;

    private TalonSRX rightSlave;
    private TalonSRX leftSlave;

    @Override
    public void robotInit() {
        beamBreakerIn = new DigitalInput(1);
        beamBreakerOut = new DigitalOutput(0);

        beamBreakerOut.set(true);

        rightMaster = new TalonSRX(4);
        leftMaster = new TalonSRX(2);

        rightSlave = new TalonSRX(3);
        leftSlave = new TalonSRX(1);

        leftMaster.setInverted(true);
        leftSlave.setInverted(true);

        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);

        rightMaster.setNeutralMode(NeutralMode.Brake);
        rightSlave.setNeutralMode(NeutralMode.Brake);
        leftMaster.setNeutralMode(NeutralMode.Brake);
        leftSlave.setNeutralMode(NeutralMode.Brake);
        
    }

    @Override
    public void robotPeriodic() {
        SmartDashboard.putBoolean("Beam Breaker", beamBreakerIn.get());
    }

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {
        if (!beamBreakerIn.get()) {
            rightMaster.set(ControlMode.PercentOutput, 0.3);
            leftMaster.set(ControlMode.PercentOutput, 0.3);
        } else {
            rightMaster.set(ControlMode.PercentOutput, 0);
            leftMaster.set(ControlMode.PercentOutput, 0);
        }
        
    }

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {}

    @Override
    public void testPeriodic() {}

    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {}
}
