package br.edu.idp.stsw.classroom.bva;

import static br.edu.idp.stsw.classroom.bva.BvaTestSupport.cartesianProduct;
import static br.edu.idp.stsw.classroom.bva.BvaTestSupport.robustBatteryValues;
import static br.edu.idp.stsw.classroom.bva.BvaTestSupport.robustPayloadWeightValues;
import static br.edu.idp.stsw.classroom.bva.BvaTestSupport.robustWindValues;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RobustWorstCaseBvaTest {

    private final DroneMissionPolicy policy = new DroneMissionPolicy();

    @ParameterizedTest(name = "[{index}] bateria={0}, vento={1}, pesoCarga={2} -> {3}")
    @MethodSource("robustWorstCaseBvaCases")
    @DisplayName("Robust worst-case BVA deve avaliar as 343 combinações válidas e inválidas de fronteira")
    void shouldEvaluateEveryRobustBoundaryCombination(
            int battery,
            int wind,
            int payloadWeight,
            String expected) {
        assertEquals(expected, policy.evaluate(battery, wind, payloadWeight));
    }

    static Stream<Arguments> robustWorstCaseBvaCases() {
        return cartesianProduct(robustBatteryValues(), robustWindValues(), robustPayloadWeightValues());
    }
}
