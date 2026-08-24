package br.edu.idp.es.stsw.bva.unit;

import java.util.stream.Stream;

import static br.edu.idp.es.stsw.bva.unit.BvaTestSupport.cartesianProduct;
import static br.edu.idp.es.stsw.bva.unit.BvaTestSupport.robustBatteryValues;
import static br.edu.idp.es.stsw.bva.unit.BvaTestSupport.robustPayloadWeightValues;
import static br.edu.idp.es.stsw.bva.unit.BvaTestSupport.robustWindValues;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.edu.idp.es.stsw.bva.DroneMissionPolicy;

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
