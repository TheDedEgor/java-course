package edu.hw8;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private Map<String, String> map = new HashMap<>();

    public Dictionary() {
        map.put("личности", "Не переходи на личности там, где их нет.");
        map.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами."
        );
        map.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        map.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public String getQuote(String word) {
        return map.get(word);
    }
}
