package br.edu.idp.es.stsw.bva.unit;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MAX_BATTERY;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MAX_PAYLOAD_WEIGHT;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MAX_WIND;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MIN_BATTERY;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MIN_PAYLOAD_WEIGHT;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MIN_WIND;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.NOMINAL_BATTERY;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.NOMINAL_PAYLOAD_WEIGHT;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.NOMINAL_WIND;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class BvaTestSupport {

    private BvaTestSupport() {
    }

    static String expectedDecision(int battery, int wind, int payloadWeight) {
        boolean validBattery = battery >= MIN_BATTERY && battery <= MAX_BATTERY;
        boolean validWind = wind >= MIN_WIND && wind <= MAX_WIND;
        boolean validPayload = payloadWeight >= MIN_PAYLOAD_WEIGHT && payloadWeight <= MAX_PAYLOAD_WEIGHT;

        return validBattery && validWind && validPayload ? "AUTORIZADA" : "NEGADA";
    }

    static Stream<Arguments> cartesianProduct(int[] batteryValues, int[] windValues, int[] payloadWeightValues) {
        Stream.Builder<Arguments> builder = Stream.builder();

        for (int battery : batteryValues) {
            for (int wind : windValues) {
                for (int payloadWeight : payloadWeightValues) {
                    builder.add(arguments(
                            battery,
                            wind,
                            payloadWeight,
                            expectedDecision(battery, wind, payloadWeight)));
                }
            }
        }

        return builder.build();
    }

    static int[] normalBatteryValues() {
        return new int[] {
                MIN_BATTERY,
                MIN_BATTERY + 1,
                NOMINAL_BATTERY,
                MAX_BATTERY - 1,
                MAX_BATTERY
        };
    }

    static int[] normalWindValues() {
        return new int[] {
                MIN_WIND,
                MIN_WIND + 1,
                NOMINAL_WIND,
                MAX_WIND - 1,
                MAX_WIND
        };
    }

    static int[] normalPayloadWeightValues() {
        return new int[] {
                MIN_PAYLOAD_WEIGHT,
                MIN_PAYLOAD_WEIGHT + 1,
                NOMINAL_PAYLOAD_WEIGHT,
                MAX_PAYLOAD_WEIGHT - 1,
                MAX_PAYLOAD_WEIGHT
        };
    }

    static int[] robustBatteryValues() {
        return new int[] {
                MIN_BATTERY - 1,
                MIN_BATTERY,
                MIN_BATTERY + 1,
                NOMINAL_BATTERY,
                MAX_BATTERY - 1,
                MAX_BATTERY,
                MAX_BATTERY + 1
        };
    }

    static int[] robustWindValues() {
        return new int[] {
                MIN_WIND - 1,
                MIN_WIND,
                MIN_WIND + 1,
                NOMINAL_WIND,
                MAX_WIND - 1,
                MAX_WIND,
                MAX_WIND + 1
        };
    }

    static int[] robustPayloadWeightValues() {
        return new int[] {
                MIN_PAYLOAD_WEIGHT - 1,
                MIN_PAYLOAD_WEIGHT,
                MIN_PAYLOAD_WEIGHT + 1,
                NOMINAL_PAYLOAD_WEIGHT,
                MAX_PAYLOAD_WEIGHT - 1,
                MAX_PAYLOAD_WEIGHT,
                MAX_PAYLOAD_WEIGHT + 1
        };
    }
}
