public class MissTreatment extends TreatmentBase {
    public MissTreatment(String name) {
        super(name);
    }

    @Override
    public String genderPrefix() {
        return "Ms.";
    }
}
