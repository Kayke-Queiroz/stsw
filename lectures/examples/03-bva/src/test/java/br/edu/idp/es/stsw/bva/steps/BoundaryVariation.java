package br.edu.idp.es.stsw.bva.steps;

enum BoundaryVariation {
    CLASSICO,
    ROBUSTO,
    WORST_CASE,
    ROBUST_WORST_CASE;

    static BoundaryVariation from(String raw) {
        String normalized = raw.trim().toLowerCase();
        return switch (normalized) {
            case "classico", "clássico" -> CLASSICO;
            case "robusto" -> ROBUSTO;
            case "worst-case", "worst case" -> WORST_CASE;
            case "robust worst-case", "robust worst case" -> ROBUST_WORST_CASE;
            default -> throw new IllegalArgumentException("Variação BVA não suportada: " + raw);
        };
    }
}
