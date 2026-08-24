package br.edu.idp.es.stsw.bva;

public class DroneMissionPolicy {

    public static final int MIN_BATTERY = 30;
    public static final int NOMINAL_BATTERY = 70;
    public static final int MAX_BATTERY = 100;

    public static final int MIN_WIND = 0;
    public static final int NOMINAL_WIND = 20;
    public static final int MAX_WIND = 40;

    public static final int MIN_PAYLOAD_WEIGHT = 1;
    public static final int NOMINAL_PAYLOAD_WEIGHT = 4;
    public static final int MAX_PAYLOAD_WEIGHT = 8;

    public static void main(String[] args){

        DroneMissionPolicy droneMission = new DroneMissionPolicy();
        System.out.println(droneMission.evaluate(70, 20, 4));
    }
    
    public String evaluate(int battery, int wind, int payloadWeight) {
        boolean batteryIsSafe = isInside(battery, MIN_BATTERY, MAX_BATTERY);
        boolean windIsSafe = isInside(wind, MIN_WIND, MAX_WIND);
        boolean payloadWeightIsSafe = isInside(payloadWeight, MIN_PAYLOAD_WEIGHT, MAX_PAYLOAD_WEIGHT);

        if (batteryIsSafe && windIsSafe && payloadWeightIsSafe) {
            return "AUTORIZADA";
        }

        return "NEGADA";
    }

    private boolean isInside(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
