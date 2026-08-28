package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class HardwareClass {
    public DcMotor FrontLeft, FrontRight, BackLeft, BackRight;

    public IMU imu;

    public void init(HardwareMap hwMap){

        //intialing motor locations
        FrontLeft = hwMap.get(DcMotorEx.class, "FrontLeft");

        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        FrontRight = hwMap.get(DcMotorEx.class, "FrontRight");

        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        BackLeft = hwMap.get(DcMotorEx.class, "BackLeft");

        BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        BackRight = hwMap.get(DcMotorEx.class,"BackRight");

        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




        //Initalinzings other hardware

        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOriention = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT
        );

        imu.initialize(new IMU.Parameters(RevOriention));

    }

    public double getHeading(){

        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

    }
}