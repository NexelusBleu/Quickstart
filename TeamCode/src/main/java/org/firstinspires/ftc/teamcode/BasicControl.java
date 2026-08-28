package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.VoltageSensor;


@TeleOp
public class BasicControl extends OpMode {

    //this allows for pulling from the Hardware class
    HardwareClass robot = new HardwareClass();

    //This allows for the reading of the battery voltage
    private VoltageSensor battery;

    //these can be edited to change the feel of the robot
    double rampSpeed = .02;
    double deadZoneR = .15;
    double turnStrength = 0.5;
    double strafeStrength = 1;
    double flPower, frPower, blPower, brPower;

    //Not variables to change feel
    double vNom = 12.0;

    @Override
    public void init() {

        //initializes all the motor and position on brain
        robot.init(hardwareMap);
        battery = hardwareMap.voltageSensor.iterator().next();

    }

    @Override
    public void loop() {

        //finds the angle of the robot
        double robotAngle = robot.getHeading();
        telemetry.addData("degree", robotAngle);
        telemetry.update();

        double vNow = battery.getVoltage();

        //initializing game pads and adding dead-zones to make less is less sensitive
        double pad1LY = -utiliCode.deadZone(gamepad1.left_stick_y,deadZoneR);
        double pad1LX = utiliCode.deadZone(gamepad1.left_stick_x, deadZoneR)*strafeStrength;
        double turn = -utiliCode.deadZone(gamepad1.right_stick_x, deadZoneR)*turnStrength;

        //This calculate all the powers for the mecanum wheels
        double flTarget = pad1LY + pad1LX - turn;
        double frTarget = pad1LY - pad1LX + turn;
        double blTarget = pad1LY - pad1LX - turn;
        double brTarget = pad1LY + pad1LX + turn;

        //normalizes all inputs
        double max = utiliCode.normalizeWheelBasee(flTarget,frTarget,blTarget,brTarget);

        flTarget /= max;
        frTarget /= max;
        blTarget /= max;
        brTarget /= max;

        // Ramps up the power to make the controllers more natural and smooth
        flPower = utiliCode.rampPower(flPower, flTarget, rampSpeed);
        frPower = utiliCode.rampPower(frPower, frTarget, rampSpeed);
        blPower = utiliCode.rampPower(blPower, blTarget,  rampSpeed);
        brPower = utiliCode.rampPower(brPower, brTarget, rampSpeed);

        double vScale = vNom/vNow;

        flPower *= vScale;
        frPower *= vScale;
        blPower *= vScale;
        brPower *= vScale;

        double vMax = utiliCode.normalizeWheelBasee(flPower,frPower,blPower,brPower);

        flPower /= vMax;
        frPower /= vMax;
        blPower /= vMax;
        brPower /= vMax;



        //Set all of the motor
        robot.FrontLeft.setPower(flPower);
        robot.FrontRight.setPower(frPower);
        robot.BackRight.setPower(brPower);
        robot.BackLeft.setPower(blPower);
    }
}
