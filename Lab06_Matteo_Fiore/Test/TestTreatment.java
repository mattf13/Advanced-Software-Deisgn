import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTreatment {

    @Test
    public void manTreatment() {
        TreatmentBuilder b = new TreatmentBuilder();
        Treatment t = b.treatmentForMan("Fabio").create();

        assertEquals("Mr. Fabio", t.announce());
    }

    @Test
    public void womanTreatment() {
        TreatmentBuilder b = new TreatmentBuilder();
        Treatment t = b.treatmentForWoman("Miriam").create();

        assertEquals("Ms. Miriam", t.announce());
    }

    @Test
    public void manDoctorTreatment() {
        TreatmentBuilder b = new TreatmentBuilder();
        Treatment t = b.treatmentForMan("Matteo").thatIsADoctor().create();

        assertEquals("Dr. Mr. Matteo", t.announce());
    }

    @Test
    public void womanCaptainTreatment() {
        TreatmentBuilder b = new TreatmentBuilder();
        Treatment t = b.treatmentForWoman("Miriam").thatIsACaptain().create();

        assertEquals("Cap. Ms. Miriam", t.announce());
    }

    @Test
    public void manCaptainDoctorTreatment() {
        TreatmentBuilder b = new TreatmentBuilder();
        Treatment t = b.treatmentForMan("Linus").thatIsADoctor().thatIsACaptain().create();

        assertEquals("Cap. Dr. Mr. Linus", t.announce());
    }

    @Test
    public void womanCaptainFromAPlaceTreatment() {
        TreatmentBuilder b = new TreatmentBuilder();
        Treatment t = b.treatmentForWoman("Miriam").thatIsACaptain().from("Brno").create();

        assertEquals("Cap. Ms. Miriam of Brno", t.announce());
    }

    @Test
    public void aLotOfStuff() {
        TreatmentBuilder b = new TreatmentBuilder();
        Treatment t = b.treatmentForWoman("Miriam").from("Brno").thatIsACaptain().from("Bozen").create();

        assertEquals("Cap. Ms. Miriam of Brno of Bozen", t.announce());
    }

}
