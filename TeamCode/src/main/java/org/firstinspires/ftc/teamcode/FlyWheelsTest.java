package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.TeleOp.TeleOpHardWare;

@TeleOp
public class FlyWheelsTest extends OpMode {

    int targetTopRPM;
    int targetBottomRPM;
    double currentBottomRPM;
    double currentTopRPM;

    boolean lastUpInput;
    boolean currentUpInput;
    boolean lastDownInput;
    boolean currentDownInput;
    boolean lastYInput;
    boolean currentYInput;
    boolean lastAInput;
    boolean currentAInput;

    double deltaTime;

    int topDeltaTicks;
    int bottomDeltaTicks;
    int topCurrentTicks;
    int bottomCurrentTicks;
    int bottomLastTicks;
    int topLastTicks;

    double topWheelTicksPerSec;
    double bottomWheelTicksPerSec;

    // NEW: rotation counters
    double topRotations;
    double bottomRotations;

    double topWheelTicksPerRev = 8192;
    double bottomWheelTicksPerRev = 8192;

    TeleOpHardWare robot = new TeleOpHardWare();
    ElapsedTime timer = new ElapsedTime();
    FlywheelPID flywheelPID = new FlywheelPID();

    public void init() {
        timer.reset();
        robot.init(hardwareMap);
    }

    public void loop() {
        deltaTime = timer.seconds();
        timer.reset();

        currentUpInput = gamepad1.dpad_up;
        currentDownInput = gamepad1.dpad_down;
        currentYInput = gamepad1.y;
        currentAInput = gamepad1.a;

        topCurrentTicks = robot.topFlywheel.getCurrentPosition();
        bottomCurrentTicks = robot.bottomFlywheel.getCurrentPosition();

        topDeltaTicks = topCurrentTicks - topLastTicks;
        bottomDeltaTicks = bottomCurrentTicks - bottomLastTicks;

        topWheelTicksPerSec = topDeltaTicks / deltaTime;
        bottomWheelTicksPerSec = bottomDeltaTicks / deltaTime;

        currentTopRPM = (topWheelTicksPerSec / topWheelTicksPerRev) * 60;
        currentBottomRPM = (bottomWheelTicksPerSec / bottomWheelTicksPerRev) * 60;

        // --- ROTATION COUNTER LOGIC ---
        topRotations = (double) topCurrentTicks / topWheelTicksPerRev;
        bottomRotations = (double) bottomCurrentTicks / bottomWheelTicksPerRev;

        // --- INPUT LOGIC ---
        if (currentUpInput && !lastUpInput && gamepad1.right_bumper) {
            targetTopRPM += 10;
        } else if (currentUpInput && !lastUpInput) {
            targetTopRPM += 100;
        }

        if (currentDownInput && !lastDownInput && gamepad1.right_bumper) {
            targetTopRPM -= 10;
        } else if (currentDownInput && !lastDownInput) {
            targetTopRPM -= 100;
        }

        if (currentYInput && !lastYInput && gamepad1.right_bumper) {
            targetBottomRPM += 10;
        } else if (currentYInput && !lastYInput) {
            targetBottomRPM += 100;
        }

        if (currentAInput && !lastAInput && gamepad1.right_bumper) {
            targetBottomRPM -= 10;
        } else if (currentAInput && !lastAInput) {
            targetBottomRPM -= 100;
        }

        double topPidOutput = flywheelPID.MPIDUpdate(targetTopRPM, currentTopRPM, deltaTime);
        double bottomPidOutput = flywheelPID.MPIDUpdate(targetBottomRPM, currentBottomRPM, deltaTime);

        robot.topFlywheel.setPower(topPidOutput);
        robot.bottomFlywheel.setPower(bottomPidOutput);

        bottomLastTicks = bottomCurrentTicks;
        topLastTicks = topCurrentTicks;
        lastAInput = currentAInput;
        lastYInput = currentYInput;
        lastDownInput = currentDownInput;
        lastUpInput = currentUpInput;

        telemetry.addData("Top Current Position", topCurrentTicks);
        telemetry.addData("Top RPM", currentTopRPM);
        telemetry.addData("Top Ticks Per Sec", topWheelTicksPerSec);
        telemetry.addData("Top Target RPM", targetTopRPM);
        telemetry.addData("Top Rotations", "%.2f", topRotations);

        telemetry.addData("Bottom Current Position", bottomCurrentTicks);
        telemetry.addData("Bottom RPM", currentBottomRPM);
        telemetry.addData("Bottom Tick Per Sec", bottomWheelTicksPerSec);
        telemetry.addData("Bottom Target RPM", targetBottomRPM);
        telemetry.addData("Bottom Rotations", "%.2f", bottomRotations);
    }
}
