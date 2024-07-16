// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    private double kP;
    private double kI;
    private double kD;
    private double setPoint;

    private TalonSRX motor;

    @Override
    public void robotInit() {
        motor = new TalonSRX(3);

        SmartDashboard.putNumber("Kp", 1);
        SmartDashboard.putNumber("Ki", 0);
        SmartDashboard.putNumber("Kd", 0);
        SmartDashboard.putNumber("setPoint", 0);

        motor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1);
        
    }

    @Override
    public void robotPeriodic() {
        kP = SmartDashboard.getNumber("Kp", 0);
        kD = SmartDashboard.getNumber("Ki", 0);
        kI = SmartDashboard.getNumber("Kd", 0);
        setPoint = SmartDashboard.getNumber("setPoint", 0);

        motor.config_kP(0, kP);
        motor.config_kI(0, kI);
        motor.config_kD(0, kD);

        System.out.println("Kp: " + kP);
        System.out.println("Kd " + kI);
        System.out.println(kD);
        System.out.println(setPoint);

        
    }

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {
        motor.set(ControlMode.Position, 100);
        System.out.println("postion: " + motor.getSelectedSensorPosition());
        
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
