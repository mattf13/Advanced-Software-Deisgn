package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import translator.Translator;

import java.io.IOException;

import static org.junit.Assert.*;

public class TranslatorTest {
    Translator t;

    @BeforeEach
    void createTranslator(){
        t = new Translator();
    }

    @Test
    public void emptyTranslation() {
        assertEquals(t.numberOfTranslations(), 0);
        assertTrue(t.isEmpty());
    }

    @Test
    public void emptyStringTranslation() {
        assertEquals(t.translate(""), "");
    }

    @Test
    public void unknownWord() {
        assertEquals("unknown", t.translate("unknown"));
    }

    @Test
    public void oneTranslation() {
        t.addTranslation("ciao", "hi");
        assertEquals("hi", t.translate("ciao"));
        assertEquals(1,t.numberOfTranslations());
        assertFalse(t.isEmpty());

    }
    @Test
    public void twoTranslation() {
        t.addTranslation("ciao", "hi");
        t.addTranslation("casa", "house");
        assertEquals("hi", t.translate("ciao"));
        assertEquals("house", t.translate("casa"));
        assertEquals(2,t.numberOfTranslations());
        assertFalse(t.isEmpty());

    }
    @Test
    public void twoTranslationSameWord(){
        t.addTranslation("ciao", "hi");
        t.addTranslation("ciao", "bye");
        assertEquals("hi, bye", t.translate("ciao"));
        assertEquals(1,t.numberOfTranslations());
        assertFalse(t.isEmpty());

    }
    @Test
    public void translateSentence(){
        t.addTranslation("buon", "good");
        t.addTranslation("giorno", "day");
        assertEquals("good day", t.translate("buon giorno"));

    }
    @Test
    public void twoTranslationsSameWordsTranslatesSentence(){
        t.addTranslation("buon", "good");
        t.addTranslation("buon", "nice");
        t.addTranslation("giorno", "day");
        assertEquals("good day", t.translate("buon giorno"));

    }
    @Test
    public void loadPropertyFile() throws IOException {
        t.loadPropertyFile("properties.txt");
        assertEquals("good day", t.translate("buon giorno"));
    }

    @Test
    public void lowercase(){
        t.addTranslation("TelEfonO", "phone");
        assertEquals("phone", t.translate("telefono"));

    }

}
