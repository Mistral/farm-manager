package pro.farmmanager.shared_kernel;

public class Dose {

    public enum Unit {
        MILILITRES,
        LITERES,
        GRAMS,
        KILOGRAMS,
        TONS
    }

    private Double value;

    private Unit unit;

}
