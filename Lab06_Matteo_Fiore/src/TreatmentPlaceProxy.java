public class TreatmentPlaceProxy implements Treatment {
    private Treatment encapsulatedTreatment;
    private String place;

    public TreatmentPlaceProxy(Treatment encapsulatedTreatment, String place) {
        this.encapsulatedTreatment = encapsulatedTreatment;
        this.place = place;
    }

    @Override
    public String announce() {
        return encapsulatedTreatment.announce() + " of " + place;
    }

    @Override
    public void setTitle(Title t) {
        encapsulatedTreatment.setTitle(t);
    }

    @Override
    public Title getTitle() {
        return encapsulatedTreatment.getTitle();
    }

    @Override
    public String genderPrefix() {
        return encapsulatedTreatment.genderPrefix();
    }
}
