public class MisterTreatment extends TreatmentBase {

    public MisterTreatment(String name) {
        super(name);
    }

    @Override
    public String genderPrefix() {
        return "Mr.";
    }
}
