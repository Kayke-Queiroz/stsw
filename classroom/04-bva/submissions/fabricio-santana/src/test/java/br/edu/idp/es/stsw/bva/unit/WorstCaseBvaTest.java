package br.edu.idp.es.stsw.bva.unit;

import java.util.stream.Stream;

import static br.edu.idp.es.stsw.bva.unit.BvaTestSupport.cartesianProduct;
import static br.edu.idp.es.stsw.bva.unit.BvaTestSupport.normalBatteryValues;
import static br.edu.idp.es.stsw.bva.unit.BvaTestSupport.normalPayloadWeightValues;
import static br.edu.idp.es.stsw.bva.unit.BvaTestSupport.normalWindValues;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.edu.idp.es.stsw.bva.DroneMissionPolicy;

class WorstCaseBvaTest {

    private final DroneMissionPolicy policy = new DroneMissionPolicy();

    @ParameterizedTest(name = "[{index}] bateria={0}, vento={1}, pesoCarga={2}")
    @MethodSource("worstCaseBvaCases")
    @DisplayName("Worst-case BVA deve autorizar todas as 125 combinações válidas de fronteira")
    void shouldAuthorizeEveryValidBoundaryCombination(
            int battery,
            int wind,
            int payloadWeight,
            String expected) {
        assertEquals(expected, policy.evaluate(battery, wind, payloadWeight));
    }

    static Stream<Arguments> worstCaseBvaCases() {
        return cartesianProduct(normalBatteryValues(), normalWindValues(), normalPayloadWeightValues());
    }
}
