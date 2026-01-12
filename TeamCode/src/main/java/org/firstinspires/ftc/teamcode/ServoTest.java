package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpHardWare;

@TeleOp
public class ServoTest extends OpMode {
    int lastInput;

    TeleOpHardWare robot = new TeleOpHardWare();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad2.dpad_up) {
            robot.topFrontWheel.setPower(-1);
            robot.topBackWheel.setPower(1);
            robot.bottomFrontWheel.setPower(-1);
            robot.bottomBackWheel.setPower(1);
        }else if(gamepad2.dpad_right) {
            robot.topFrontWheel.setPower(1);
            robot.topBackWheel.setPower(-1);
            robot.bottomFrontWheel.setPower(1);
            robot.bottomBackWheel.setPower(1);
        }else if(gamepad2.dpad_down){
            robot.topFrontWheel.setPower(1);
            robot.topBackWheel.setPower(-1);
            robot.bottomFrontWheel.setPower(1);
            robot.bottomBackWheel.setPower(-1);
        }else if(gamepad2.dpad_left){
            robot.topFrontWheel.setPower(1);
            robot.topBackWheel.setPower(1);
            robot.bottomFrontWheel.setPower(-1);
            robot.bottomBackWheel.setPower(1);
        }else if(gamepad2.shareWasPressed()){
            robot.topFrontWheel.setPower(-1);
            robot.topBackWheel.setPower(-1);
            robot.bottomFrontWheel.setPower(-1);
            robot.bottomBackWheel.setPower(-1);
        } else if (gamepad2.optionsWasPressed()) {
            robot.topFrontWheel.setPower(1);
            robot.topBackWheel.setPower(-1);
            robot.bottomFrontWheel.setPower(-1);
            robot.bottomBackWheel.setPower(1);
        }

    }
}