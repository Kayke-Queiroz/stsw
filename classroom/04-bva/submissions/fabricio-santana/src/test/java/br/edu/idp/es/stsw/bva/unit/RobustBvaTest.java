package br.edu.idp.es.stsw.bva.unit;

import java.util.stream.Stream;

import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MAX_BATTERY;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MAX_PAYLOAD_WEIGHT;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MAX_WIND;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MIN_BATTERY;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MIN_PAYLOAD_WEIGHT;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.MIN_WIND;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.NOMINAL_BATTERY;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.NOMINAL_PAYLOAD_WEIGHT;
import static br.edu.idp.es.stsw.bva.DroneMissionPolicy.NOMINAL_WIND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.edu.idp.es.stsw.bva.DroneMissionPolicy;

class RobustBvaTest {

    private final DroneMissionPolicy policy = new DroneMissionPolicy();

    @ParameterizedTest(name = "[{index}] bateria={0}, vento={1}, pesoCarga={2} -> {3}")
    @MethodSource("robustBvaCases")
    @DisplayName("BVA robusto deve validar limites inclusivos e rejeitar valores logo fora das fronteiras")
    void shouldEvaluateRobustBoundaryValues(
            int battery,
            int wind,
            int payloadWeight,
            String expected) {
        assertEquals(expected, policy.evaluate(battery, wind, payloadWeight));
    }

    static Stream<Arguments> robustBvaCases() {
        return Stream.of(
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(MIN_BATTERY - 1, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT, "NEGADA"),
                arguments(MIN_BATTERY, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(MIN_BATTERY + 1, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(MAX_BATTERY - 1, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(MAX_BATTERY, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(MAX_BATTERY + 1, NOMINAL_WIND, NOMINAL_PAYLOAD_WEIGHT, "NEGADA"),
                arguments(NOMINAL_BATTERY, MIN_WIND - 1, NOMINAL_PAYLOAD_WEIGHT, "NEGADA"),
                arguments(NOMINAL_BATTERY, MIN_WIND, NOMINAL_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(NOMINAL_BATTERY, MIN_WIND + 1, NOMINAL_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(NOMINAL_BATTERY, MAX_WIND - 1, NOMINAL_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(NOMINAL_BATTERY, MAX_WIND, NOMINAL_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(NOMINAL_BATTERY, MAX_WIND + 1, NOMINAL_PAYLOAD_WEIGHT, "NEGADA"),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MIN_PAYLOAD_WEIGHT - 1, "NEGADA"),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MIN_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MIN_PAYLOAD_WEIGHT + 1, "AUTORIZADA"),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MAX_PAYLOAD_WEIGHT - 1, "AUTORIZADA"),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MAX_PAYLOAD_WEIGHT, "AUTORIZADA"),
                arguments(NOMINAL_BATTERY, NOMINAL_WIND, MAX_PAYLOAD_WEIGHT + 1, "NEGADA"));
    }
}
