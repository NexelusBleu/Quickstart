package org.firstinspires.ftc.teamcode;

public class utiliCode {

    private utiliCode() {

        throw new UnsupportedOperationException("Utility class");

    }
    public static double rampPower(double current, double target, double step) {
        if (current < target){
            current += step;

            if (current > target) current = target;

        } else if (current > target) {
            current -= step;

            if (current < target) current = target;
        }
        return current;
    }

    public static double deadZone(double input, double r){

        if (Math.abs(input) < r) input = 0;

        return input;
    }

    public static double normalizeWheelBasee(
            double flTarget, double blTarget, double frTarget, double brTarget){

        double max = Math.max(1.0, Math.max(Math.abs(flTarget), Math.max(Math.abs(blTarget),
                Math.max(Math.abs(frTarget), Math.abs(brTarget)))));
        return max;
    }
}
