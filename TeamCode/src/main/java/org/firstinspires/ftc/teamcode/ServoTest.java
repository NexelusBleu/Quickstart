package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpHardWare;

@TeleOp
public class ServoTest extends OpMode {

    TeleOpHardWare robot = new TeleOpHardWare();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.topFrontWheel.setPower(1);   // <-- correct call
    }
}
