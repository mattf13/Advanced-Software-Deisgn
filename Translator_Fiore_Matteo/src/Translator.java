import java.io.FileInputStream;
import java.util.*;

public class Translator {
    private final Map<String, Set<String>> translations = new HashMap<>();

    public static void main(String[] args) {
        Translator t = new Translator();
        t.addTranslation("casa", "house");
        t.addTranslation("dolce", "sweet");
    }
    public boolean loadFromPropertyFile(String filePath) {
        Properties properties = new Properties();
        //var c = getClass().getResource("/translation.properties");
        //File file = new File("../" + filePath);
        try (var f = new FileInputStream(filePath)) {
            properties.load(f);
            for (var e : properties.entrySet()) {
                addTranslation((String) e.getKey(), (String) e.getValue());
            }
        } catch (Exception e) {
            int b = 1;
        }
        int a = 1;
        return false;
    }

    private static String capitalize(String translation) {
        return translation.substring(0, 1).toUpperCase() + translation.substring(1).toLowerCase();
    }

    public Set<String> getPossibleTranslations(String word) {
        return translations.getOrDefault(word, Set.of());
    }

    public boolean isEmpty() {
        return translations.isEmpty();
    }

    public int numberOfTranslations() {
        return translations.keySet().size();
    }

    public void addTranslation(String from, String to) {
        translations.computeIfAbsent(from.toLowerCase(Locale.ROOT), f -> new HashSet<>()).add(to.toLowerCase(Locale.ROOT));
    }

    public String translate(String sentence) {
        StringBuilder builder = new StringBuilder();
        var words = new ArrayDeque<>(List.of(sentence.split("\\W+")));
        var nonWords = new ArrayDeque<>(List.of(sentence.split("\\w+")));
        //non word start
        if (!nonWords.isEmpty()) {
            var nonWordStart = nonWords.peek();
            if (nonWordStart.equals("")) nonWords.remove();
            else if (sentence.startsWith(nonWordStart)) {
                builder.append(nonWordStart);
                nonWords.remove();
            }
        }
        if (!words.isEmpty()) {
            if (words.peek().equals("")) {
                words.remove(); //idk
            }
        }
        boolean isFirst = true;
        while (!nonWords.isEmpty() || !words.isEmpty()) {
            String word = null;
            String nonWord = null;
            if (!words.isEmpty()) {
                word = words.peek();
                words.remove();
            }
            if (!nonWords.isEmpty()) {
                nonWord = nonWords.peek();
                nonWords.remove();
            }

            if (word != null) {
                boolean isCapitalized = (Character.isUpperCase(word.charAt(0)));
                word = word.toLowerCase(Locale.ROOT);
                String translation = this.getPossibleTranslations(word).stream().findFirst().orElse(word);
                if (isCapitalized) {
                    if (isFirst) {
                        translation = capitalize(translation);
                    } else {
                        translation = capitalize(word);
                    }
                }
                builder.append(translation);
            }
            if (nonWord != null) {
                builder.append(nonWord);
            }

            isFirst = false;
        }
        return builder.toString();
    }

}