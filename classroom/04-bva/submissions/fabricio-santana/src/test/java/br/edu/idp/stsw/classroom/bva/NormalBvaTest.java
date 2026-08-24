package br.edu.idp.stsw.classroom.bva;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static br.edu.idp.stsw.classroom.bva.DroneMissionPolicy.MAX_BATTERY;
import static br.edu.idp.stsw.classroom.bva.DroneMissionPolicy.MAX_PAYLOAD_WEIGHT;
import static br.edu.idp.stsw.classroom.bva.DroneMissionPolicy.MAX_WIND;
import static br.edu.idp.stsw.classroom.bva.DroneMissionPolicy.MIN_BATTERY;
import static br.edu.idp.stsw.classroom.bva.DroneMissionPolicy.MIN_PAYLOAD_WEIGHT;
import static br.edu.idp.stsw.classroom.bva.DroneMissionPolicy.MIN_WIND;
import static br.edu.idp.stsw.classroom.bva.DroneMissionPolicy.NOMINAL_BATTERY;
import static br.edu.idp.stsw.classroom.bva.DroneMissionPolicy.NOMINAL_PAYLOAD_WEIGHT;
import static br.edu.idp.stsw.classroom.bva.DroneMissionPolicy.NOMINAL_WIND;

class NormalBvaTest {

    private final DroneMissionPolicy policy = new DroneMissionPolicy();

    @ParameterizedTest(name = "[{index}] bateria={0}, vento={1}, pesoCarga={2}")
    @MethodSource("normalBvaCases")
    @DisplayName("BVA normal deve autorizar todos os valores válidos de fronteira com falha única")
    void shouldAuthorizeNormalBoundaryValues(int battery, int wind, int payloadWeight) {
        assertEquals("AUTORIZADA", policy.evaluate(battery, wind, payloadWeight));
    }

    static Stream<Arguments> normalBvaCases() {
        return Stream.of(
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT),
                arguments(MIN_BATTERY, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT),
                arguments(MIN_BATTERY + 1, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT),
                arguments(MAX_BATTERY - 1, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT),
                arguments(MAX_BATTERY, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT),
                arguments(NOMINAL_BATTERY, MIN_WIND, NOMINAL_PAYLOAD_WEIGHT),
                arguments(NOMINAL_BATTERY, MIN_WIND + 1, NOMINAL_PAYLOAD_WEIGHT),
                arguments(NOMINAL_BATTERY, MAX_WIND - 1, NOMINAL_PAYLOAD_WEIGHT),
                arguments(NOMINAL_BATTERY, MAX_WIND, NOMINAL_PAYLOAD_WEIGHT),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MIN_PAYLOAD_WEIGHT),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MIN_PAYLOAD_WEIGHT + 1),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MAX_PAYLOAD_WEIGHT - 1),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MAX_PAYLOAD_WEIGHT));
    }
}
