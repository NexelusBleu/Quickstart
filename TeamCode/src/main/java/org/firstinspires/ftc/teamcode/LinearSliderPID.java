package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.utilities.MathFunction;

public class LinearSliderPID {
    @Configurable
    public static class PIDSliderTunables {
        public static double kp = 0.015;
        public static double ki = 0.0002;
        public static double kd = 0.0005;

    }

    double intergral;
    double derivative;
    double rawDerivative;
    double error;
    double lastError;
    double preCIntergral;
    double alpha = 0;
    double PIDOutput;

    public double MPIDUpdate(double dLocation, double cLaction, double dt) {

        error = dLocation-cLaction;

        intergral += error * dt;

        preCIntergral = intergral;

        intergral = Math.max(-1, Math.min(1, intergral));

        if ((error * preCIntergral > 0) && (Math.abs(preCIntergral - intergral) < 1e-6)) {
            intergral = 0;
        }

        rawDerivative = (error - lastError) / dt;

        derivative = alpha * derivative + (1 - alpha) * rawDerivative;

        PIDOutput = (error * PIDSliderTunables.kp) + (intergral * PIDSliderTunables.ki) + (derivative * PIDSliderTunables.kd);

        lastError = error;

        return PIDOutput;
    }
}
