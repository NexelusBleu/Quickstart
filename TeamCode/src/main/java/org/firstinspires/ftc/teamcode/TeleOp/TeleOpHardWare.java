package org.firstinspires.ftc.teamcode.TeleOp;

import android.hardware.Sensor;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TeleOpHardWare {

    public DcMotor frontLeft, frontRight, backLeft, backRight, linearSlider, topFlywheel, bottomFlywheel;
    public CRServo topFrontWheel,topBackWheel, bottomBackWheel, bottomFrontWheel;
    public ColorSensor colorSensor;

    public void init(HardwareMap hwMap){

        frontLeft = hwMap.get(DcMotorEx.class, "frontLeft");
        frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        frontRight = hwMap.get(DcMotorEx.class, "frontRight");
        frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        backLeft = hwMap.get(DcMotorEx.class, "backLeft");
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        backRight = hwMap.get(DcMotorEx.class, "backRight");
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        topFlywheel = hwMap.get(DcMotorEx.class, "topFlywheel");
        topFlywheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        topFlywheel.setDirection(DcMotorSimple.Direction.FORWARD);

        bottomFlywheel = hwMap.get(DcMotorEx.class, "bottomFlywheel");
        bottomFlywheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bottomFlywheel.setDirection(DcMotorSimple.Direction.REVERSE);

        // your CR servo
        topFrontWheel = hwMap.get(CRServo.class, "topFrontWheel");
        topBackWheel = hwMap.get(CRServo.class, "topBackWheel");
        bottomFrontWheel = hwMap.get(CRServo.class, "bottomFrontWheel");
        bottomBackWheel = hwMap.get(CRServo.class, "bottomBackWheel");

        //sensors
        colorSensor = hwMap.get(ColorSensor.class, "colorSensor");
    }
}
