package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TeleOpHardWare {

    public DcMotor frontLeft, frontRight, backLeft, backRight;

    public void init(HardwareMap hwMap){

        frontLeft = hwMap.get(DcMotorEx.class, "frontLeft");//init the motor
        frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);//allow motors to run faster
        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);//reversing direction
        //repeated for the rest of wheels
        frontRight = hwMap.get(DcMotorEx.class, "frontRight");
        frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        backLeft = hwMap.get(DcMotorEx.class, "backLeft");
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        backRight = hwMap.get(DcMotorEx.class, "backRight");
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}

