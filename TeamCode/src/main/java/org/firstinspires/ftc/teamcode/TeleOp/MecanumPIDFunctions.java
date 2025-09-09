package org.firstinspires.ftc.teamcode.TeleOp;

import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.utilities.MathFunction;

public class MecanumPIDFunctions {
    @Configurable
    public static class PIDTunables {
        public static double kp = 0.9;
        public static double ki = 0.5;
        public static double kd = 0.1;
    }

    double intergral;
    double derivative;
    double rawDerivative;
    double error;
    double lastError;
    double preCIntergral;
    double alpha = .9;
    double PIDOutput;

    public double MPIDUpdate(double dAngle, double cAngle, double dt) {

        error = MathFunction.angleWrap(MathFunction.angleWrap(dAngle)-MathFunction.angleWrap(cAngle) );

        intergral += error * dt;

        preCIntergral = intergral;

        intergral = Math.max(-1, Math.min(1, intergral));

        if ((error * preCIntergral > 0) && (Math.abs(preCIntergral - intergral) < 1e-6)) {
            intergral = 0;
        }

        rawDerivative = (error - lastError) / dt;

        derivative = alpha * derivative + (1 - alpha) * rawDerivative;

        PIDOutput = (error * PIDTunables.kp) + (intergral * PIDTunables.ki) + (derivative * PIDTunables.kd);

        lastError = error;

        return PIDOutput;
    }
}
