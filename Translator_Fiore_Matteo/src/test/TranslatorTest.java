package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TranslatorTest {
    private static Translator t;

    @Before
    public void getNewTranslator() {
        t = new Translator();
    }

    @Test
    public void emptyTranslation() {
        Assert.assertEquals(t.numberOfTranslations(),0);
        Assert.assertTrue(t.isEmpty());
    }
    @Test
    public void emptyStringTranslation() {
        Assert.assertEquals(t.translate(" ")," ");
    }

    @Test
    public void unknownWord() {
        String s = "unknown";
        Assert.assertEquals(s,t.translate(s));
    }

    @Test
    public void addTranslation() {
        t.addTranslation("ciao","hi");
        Assert.assertFalse(t.isEmpty());
        Assert.assertEquals(t.numberOfTranslations(), 1);
        Assert.assertEquals(t.translate("ciao"), "hi");
    }

    @Test
    public void add2Translation() {
        t.addTranslation("ciao","hi");
        t.addTranslation("arrivederci","goodbye");
        Assert.assertFalse(t.isEmpty());
        Assert.assertEquals(t.numberOfTranslations(), 2);
        Assert.assertEquals(t.translate("ciao"), "hi");
        Assert.assertEquals(t.translate("arrivederci"), "goodbye");
    }

    @Test
    public void getMultipleTranslations() {
        t.addTranslation("arrivederci","bye");
        t.addTranslation("arrivederci","goodbye");
        Assert.assertFalse(t.isEmpty());
        Assert.assertEquals(t.numberOfTranslations(), 1);
        var tr = t.getPossibleTranslations("arrivederci");
        Assert.assertTrue(tr.contains("bye") && tr.contains("goodbye"));
    }

    @Test
    public void translateSentence() {
        t.addTranslation("buon","good");
        t.addTranslation("giorno","day");
        Assert.assertFalse(t.isEmpty());
        Assert.assertEquals(t.numberOfTranslations(), 2);
        Assert.assertEquals(t.translate("buon giorno"), "good day");
    }

    @Test
    public void loadFromFile() {
        t.loadFromPropertyFile("translation.properties");
        Assert.assertFalse(t.isEmpty());
        Assert.assertEquals(t.numberOfTranslations(), 2);
        Assert.assertEquals(t.translate("mela"), "apple");
        Assert.assertEquals(t.translate("arancia"), "orange");
    }

    @Test
    public void caseIndependent() {
        t.addTranslation("Etwas", "SomeThing");
        Assert.assertEquals(t.translate("etwas"), "something");
    }
    @Test
    public void keepPunctuation() {
        t.addTranslation("thing", "cosa");
        t.addTranslation("thing2", "cosa2");
        t.addTranslation("thing3", "cosa3");
        t.addTranslation("thing4", "cosa4");
        Assert.assertEquals( "[]cosa, cosa2; cosa3 cosa4()",t.translate("[]thing, thing2; thing3 thing4()"));
    }

    @Test
    public void uppercaseFirstWord() {
        t.addTranslation("casa", "home");
        t.addTranslation("dolce", "sweet");
        Assert.assertEquals( "Home sweet home",t.translate("Casa dolce casa"));
    }


    @Test
    public void dontUppercaseMiddle() {
        t.addTranslation("casa", "home");
        t.addTranslation("dolce", "sweet");
        Assert.assertEquals( "Home Dolce home",t.translate("Casa Dolce casa"));
    }

    @Test
    public void multiLineTranslating() {
        t.addTranslation("casa", "home");
        t.addTranslation("dolce", "sweet");
        Assert.assertEquals( "Home Dolce home\n home",t.translate("Casa Dolce casa\n casa"));
    }

}