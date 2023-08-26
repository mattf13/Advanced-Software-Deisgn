package translator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Translator {
    private Map<String, String> translations = new HashMap<>();

    public static void main(String[] args) {
        Translator t = new Translator();
        t.addTranslation("casa", "house");
        t.addTranslation("dolce", "sweet");
    }


    public boolean isEmpty() {
        return translations.isEmpty();
    }

    public int numberOfTranslations() {
        return translations.size();
    }

    private String firstTranslation(String word){
        return translate(word).split(", ")[0];
    }
    public String translate(String word) {
        lowerCase();
        if (word.contains(" ")) {
            String sentence = "";
            String[] words = word.split(" ");
            for (String w : words) {
                sentence += this.firstTranslation(w) + " ";
            }
            return sentence.trim();
        }
        if (!translations.containsKey(word)) {
            return word;
        }
        return this.translations.get(word);
    }

    public void addTranslation(String word, String translation) {
        if (this.translations.containsKey(word)) {
            translation = this.translations.get(word) + ", " + translation;
        }
        this.translations.put(word, translation);
    }

    private void lowerCase() {
        for (Map.Entry<String, String> entry : translations.entrySet()){
            translations.put(entry.getKey().toLowerCase(), entry.getValue());
        }
    }

    public void loadPropertyFile(String fileName) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(fileName));
        for (Object word : p.keySet()){
            addTranslation(word.toString(), p.getProperty(word.toString()));
        }
    }
}
