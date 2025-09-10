package org.firstinspires.ftc.teamcode.TeleOp;

import com.bylazar.graph.GraphManager;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.utilities.MathFunction;


@TeleOp
public class MecanumDriveCodeFieldCentric extends OpMode {

    double dAngle;
    double dt;

    ElapsedTime timer;
    MecanumPIDFunctions headingPID;
    TeleOpHardWare robot;
    TelemetryManager panelsTelemetry;
    GraphManager panelsGraph;

    IMU imu;

    @Override
    public void init() {
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));

        panelsTelemetry.debug();
        panelsTelemetry.update();

        imu.resetYaw();

        timer.reset();

        robot.init(hardwareMap);

        imu.initialize(parameters);

    }

    @Override
    public void loop() {
        dt = timer.seconds();
        timer.reset();

        //setting up gamepad inputs
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;  
        double tR = -gamepad1.right_stick_x * Math.toRadians(90);
        tR = MathFunction.angleWrap(tR);

        //this will allows the drive to reset the imu angle
        if(gamepad1.options) {
            imu.resetYaw();
        }
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        botHeading = MathFunction.angleWrap(botHeading);

        dAngle += tR * dt;
        dAngle = MathFunction.angleWrap(dAngle);

        double PIDOutput = headingPID.MPIDUpdate(dAngle, botHeading, dt);

        double rotX = x * Math.cos(- botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX *= 1.1; //This accounts for how strafing feels different

        //doing the math for each wheel
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(PIDOutput), 1);
        double frontLeftPower = (rotY + rotX - PIDOutput) / denominator;
        double backLeftPower = (rotY - rotX - PIDOutput) / denominator;
        double frontRightPower = (rotY - rotX + PIDOutput) / denominator;
        double backRightPower = (rotY + rotX + PIDOutput) / denominator;

        //setting the motor powers
        robot.frontLeft.setPower(frontLeftPower);
        robot.backLeft.setPower(backLeftPower);
        robot.frontRight.setPower(frontRightPower);
        robot.backRight.setPower(backRightPower);

        panelsTelemetry.debug();
        panelsGraph.addData("PID Output", PIDOutput);
        panelsGraph.addData("Current Angle", botHeading);
        panelsGraph.addData("Wanted Angle", dAngle);
        panelsGraph.update();
    }
}
