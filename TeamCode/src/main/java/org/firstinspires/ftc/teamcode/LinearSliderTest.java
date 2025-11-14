package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleOp.TeleOpHardWare;

@TeleOp
public class LinearSliderTest extends OpMode {
    boolean ACS;
    boolean ALS;
    boolean ATS;
    double dt;
    double cLocation;
    double dLocation = 0;
    LinearSliderPID sliderPID = new LinearSliderPID();
    TeleOpHardWare robot = new TeleOpHardWare();
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void init() {
        timer.reset();

        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        dt = timer.seconds();
        timer.reset();

        ACS = gamepad1.a;

        if(ACS & !ALS){
            ATS = !ATS;
        }
        if(ATS){
            dLocation = 4350;
        }else{
            dLocation = 0;
        }
        cLocation = robot.linearSlider.getCurrentPosition();

        double PIDOutput = sliderPID.MPIDUpdate(dLocation, cLocation, dt);
        telemetry.addData("pre clamp pid", PIDOutput);

        if(PIDOutput > 1)
            PIDOutput = 1;
        else if (PIDOutput < -1 ) {
            PIDOutput = -1;
        }

        ALS = ACS;

        robot.linearSlider.setPower(PIDOutput);

        telemetry.addData("motor position", robot.linearSlider.getCurrentPosition());
        telemetry.addData("target", dLocation);
        telemetry.addData("after clamp", PIDOutput);
        telemetry.update();

    }
}
