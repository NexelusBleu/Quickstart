package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;

public class FlywheelPID {
    @Configurable
    public static class FlywheelsTune{
        public static double kp = 0.0;
        public static double ki = 0.0;
        public static double kd = 0.0;
    }

    double intergral;
    double rawDerivative;
    double error;
    double lastError;
    double preCIntergral;
    double PIDOutput;
    double kf;

    public double MPIDUpdate(double targetRPM, double currentRPM, double dt) {
        error = targetRPM-currentRPM;

        kf = (double) 12 /6000;

        intergral += error * dt;

        preCIntergral = intergral;

        intergral = Math.max(-1, Math.min(1, intergral));

        if ((error * preCIntergral > 0) && (Math.abs(preCIntergral - intergral) < 1e-6)) {
            intergral = 0;
        }

        rawDerivative = (error - lastError) / dt;

        PIDOutput = (kf * targetRPM) + (error * FlywheelPID.FlywheelsTune.kp) + (intergral * FlywheelPID.FlywheelsTune.ki) + (rawDerivative * FlywheelPID.FlywheelsTune.kd);

        lastError = error;

        return PIDOutput;
    }
}

