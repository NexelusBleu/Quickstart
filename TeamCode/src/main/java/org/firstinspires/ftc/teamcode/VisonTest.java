package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp
public class VisonTest extends OpMode {

    AprilTagProcessor tagProcessor;
    VisionPortal visionPortal;

    @Override
    public void init() {
        // Build AprilTag processor (draw outlines & axes so you can see tags)
        tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .build();

        // Build VisionPortal and keep the default DS camera monitor ON
        visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "fWebCam"))
                .setCameraResolution(new Size(640, 480))
                //.enableLiveView(true) // (optional, it's on by default)
                .build();
    }

    @Override
    public void loop() {
        if (tagProcessor != null && tagProcessor.getDetections().size() > 0) {
            AprilTagDetection tag = tagProcessor.getDetections().get(0);
            telemetry.addData("distance", tag.ftcPose.bearing);
        } else {
            telemetry.addLine("No tags detected");
        }
        telemetry.update();
    }
}
