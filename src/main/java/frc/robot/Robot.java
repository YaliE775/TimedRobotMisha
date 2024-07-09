// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Joystick driverJoystick;
    
    private TalonSRX rightMaster;
    private TalonSRX leftMaster;

    private TalonSRX rightSlave;
    private TalonSRX leftSlave;

    @Override
    public void robotInit() {
        driverJoystick = new Joystick(0);
        rightMaster = new TalonSRX(4);
        leftMaster = new TalonSRX(2);

        rightSlave = new TalonSRX(3);
        leftSlave = new TalonSRX(1);

        rightMaster.setInverted(true);
        rightSlave.setInverted(true);

        rightSlave.follow(rightMaster);
        leftSlave.follow(leftMaster);

        rightMaster.setNeutralMode(NeutralMode.Brake);
        rightSlave.setNeutralMode(NeutralMode.Brake);
        leftMaster.setNeutralMode(NeutralMode.Brake);
        leftSlave.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void robotPeriodic() {
        double x = driverJoystick.getRawAxis(0);
        double y = driverJoystick.getRawAxis(3);
        
        arcadeDrive(x, y);

    }

    public void arcadeDrive(double x, double y) {
        x = Math.max(-0.9, Math.min(0.9, x));
        double max = Math.max(Math.abs(x), Math.abs(y));

        double total = x + y;
        double difference = y - x;

        if(y >= 0) {
            if(x >= 0) {
                leftMaster.set(ControlMode.PercentOutput, max);
                rightMaster.set(ControlMode.PercentOutput, difference);
            } else {
                leftMaster.set(ControlMode.PercentOutput, total);
                rightMaster.set(ControlMode.PercentOutput, max);
            }
        } else {
            if(x >= 0) {
                leftMaster.set(ControlMode.PercentOutput, total);
                rightMaster.set(ControlMode.PercentOutput, -max);
            } else {
                leftMaster.set(ControlMode.PercentOutput, -max);
                rightMaster.set(ControlMode.PercentOutput, difference);
            }
        }

        System.out.println("right: " + rightMaster.getMotorOutputPercent());
        System.out.println("left: " + leftMaster.getMotorOutputPercent());

        System.out.println("y: " + y);
        System.out.println("x: " + x);
    }
    

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {}

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
