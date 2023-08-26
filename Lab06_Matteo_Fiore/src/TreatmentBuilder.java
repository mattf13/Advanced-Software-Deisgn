public class TreatmentBuilder {

    private Treatment treatment;

    public InternalBuilder treatmentForMan(String name) {
        treatment = new MisterTreatment(name);
        return new InternalBuilder();
    }

    public InternalBuilder treatmentForWoman(String name) {
        treatment = new MissTreatment(name);
        return new InternalBuilder();
    }

    class InternalBuilder {
        public InternalBuilder thatIsADoctor() {
            treatment.setTitle(new CompositeTitle(new Doctor(), treatment.getTitle()));
            return this;
        }

        public InternalBuilder thatIsACaptain() {
            treatment.setTitle(new CompositeTitle(new Captain(), treatment.getTitle()));
            return this;
        }

        public Treatment create() {
            return treatment;
        }

        public InternalBuilder from(String city) {
            treatment = new TreatmentPlaceProxy(treatment, city);
            return this;
        }
    }
}
