public class CompositeTitle implements Title {
    private Title title1;
    private Title title2;

    public CompositeTitle(Title title1, Title title2) {
        this.title1 = title1;
        this.title2 = title2;
    }

    @Override
    public String get() {
        return title1.get() + title2.get();
    }
}