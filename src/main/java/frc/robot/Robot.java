// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
    private Joystick joystick;
    private TalonSRX motor;

    @Override
    public void robotInit() {
        joystick = new Joystick(0);
        motor = new TalonSRX(1);

        motor.set(ControlMode.PercentOutput, 0);
        motor.setNeutralMode(NeutralMode.Brake);

    }

    @Override
    public void robotPeriodic() {
        if(joystick.getRawButton(1)) {
            motor.set(ControlMode.PercentOutput, 0.3);
        } else if (joystick.getRawButton(2)) {
            motor.set(ControlMode.PercentOutput, -0.3);
        } else {
            motor.set(ControlMode.PercentOutput, joystick.getRawAxis(3) * 0.5);
        }

        

        
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
