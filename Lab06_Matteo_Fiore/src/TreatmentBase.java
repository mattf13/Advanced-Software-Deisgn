public abstract class TreatmentBase implements Treatment {
    private String name;
    private Title title = new Untitled();

    public TreatmentBase(String name) {
        super();
        this.name = name;
    }

    @Override
    public String announce() {
        return title.get() + genderPrefix() + " " + this.name;
    }

    @Override
    public void setTitle(Title t) {
        this.title = t;
    }

    @Override
    public Title getTitle() {
        return this.title;
    }

}
