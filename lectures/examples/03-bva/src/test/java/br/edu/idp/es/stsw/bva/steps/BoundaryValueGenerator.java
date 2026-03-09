package br.edu.idp.es.stsw.bva.steps;

import java.util.ArrayList;
import java.util.List;

final class BoundaryValueGenerator {

    private BoundaryValueGenerator() {
    }

    static List<Integer> points(int min, int max, int nominal, BoundaryVariation variation) {
        List<Integer> values = new ArrayList<>();

        if (variation == BoundaryVariation.ROBUSTO || variation == BoundaryVariation.ROBUST_WORST_CASE) {
            values.add(min - 1);
        }

        values.add(min);
        values.add(min + 1);
        values.add(nominal);
        values.add(max - 1);
        values.add(max);

        if (variation == BoundaryVariation.ROBUSTO || variation == BoundaryVariation.ROBUST_WORST_CASE) {
            values.add(max + 1);
        }

        return values;
    }

    static int expectedSize(BoundaryVariation variation, boolean combinatorial) {
        if (!combinatorial) {
            return switch (variation) {
                case CLASSICO -> 5;
                case ROBUSTO -> 7;
                case WORST_CASE, ROBUST_WORST_CASE ->
                        throw new IllegalArgumentException("Use combinatorial=true para worst-case.");
            };
        }

        return switch (variation) {
            case WORST_CASE -> 25;
            case ROBUST_WORST_CASE -> 49;
            case CLASSICO, ROBUSTO -> throw new IllegalArgumentException("Use variações worst-case em modo combinatório.");
        };
    }
}
