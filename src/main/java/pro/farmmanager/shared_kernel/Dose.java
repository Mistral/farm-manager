package pro.farmmanager.shared_kernel;

public class Dose {

    public enum Unit {
        MILILITRES,
        LITERES,
        GRAMS,
        KILOGRAMS,
        TONS
    }

    public final static Dose ZERO = of(0d, Unit.LITERES);

    private Double value;

    private Unit unit;

    public static Dose of(Double value, Unit unit) {
        return new Dose(value, unit);
    }

    public static Dose ofMililitres(Double value) {
        return new Dose(value, Unit.MILILITRES);
    }

    public static Dose ofLiteres(Double value) {
        return new Dose(value, Unit.LITERES);
    }

    public static Dose ofGrams(Double value) {
        return new Dose(value, Unit.GRAMS);
    }

    public static Dose ofKilograms(Double value) {
        return new Dose(value, Unit.KILOGRAMS);
    }

    public static Dose ofTons(Double value) {
        return new Dose(value, Unit.TONS);
    }

    private Dose(Double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    private Dose() {

    }

}
