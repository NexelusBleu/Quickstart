package org.firstinspires.ftc.teamcode;

import static android.media.CamcorderProfile.get;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpHardWare;

import java.lang.reflect.Array;
import java.util.List;

@TeleOp
public class colorTest extends OpMode {
    double purpleBalls;
    double greenBalls;
    TeleOpHardWare robot = new TeleOpHardWare();
    ElapsedTime delay = new ElapsedTime();
    int lastBall = 0;
    int movement = 0;
    int spotInPattern = 0;
    int[] pattern = {1,2,2};

    public void init(){
        robot.init(hardwareMap);
    }
    public void loop(){
        if(robot.colorSensor.blue() > 80){
            telemetry.addData("statues", "Ball Found");
            if(robot.colorSensor.green() > robot.colorSensor.blue()){
                telemetry.addData("color", "Green");
                greenBalls += 1;
                delay.reset();
            }else{
                telemetry.addData("Color", "Purple");
                purpleBalls += 1;
                delay.reset();
            }

        }
        if(greenBalls > purpleBalls){
            lastBall = 1;
        }else if(purpleBalls > greenBalls){
            lastBall = 2;
        }

        if(pattern[0] == lastBall){
            movement = 1;
        }


        telemetry.addData("Chosen Ball", lastBall);
        if(movement== 1){
            if(delay.seconds() < 3) {
                robot.topFrontWheel.setPower(1);
                robot.topBackWheel.setPower(-1);
                robot.bottomFrontWheel.setPower(-1);
                robot.bottomBackWheel.setPower(1);
            }else{
             lastBall = 0;
             greenBalls = 0;
             purpleBalls = 0;
            }
        }
        if(movement == 2){
            if(delay.seconds() < 3) {
                robot.topFrontWheel.setPower(-1);
                robot.topBackWheel.setPower(1);
                robot.bottomFrontWheel.setPower(-1);
                robot.bottomBackWheel.setPower(1);
            }else {
                lastBall = 0;
                greenBalls = 0;
                purpleBalls = 0;
            }
        }
        if(movement == 3){
            if(delay.seconds() < 3){
                robot.topFrontWheel.setPower(1);
                robot.topBackWheel.setPower(1);
                robot.bottomFrontWheel.setPower(1);
                robot.bottomBackWheel.setPower(1);
            }else{
                lastBall = 0;
                greenBalls = 0;
                purpleBalls = 0;
            }
        }


        if(lastBall == 0){
        robot.topFrontWheel.setPower(1);
        robot.topBackWheel.setPower(1);
        robot.bottomFrontWheel.setPower(-1);
        robot.bottomBackWheel.setPower(1);

        }
        telemetry.addData("statues", "No Ball Found");
        telemetry.addData("Delay Timer", delay.seconds());
    }
}
