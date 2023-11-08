package edu.hw5.chain;

public class ChainDateParser {

    private ChainDateParser() {
    }

    public static DateParser getChain() {
        DateParser dateParser6 = new DateParser6(null);
        DateParser dateParser5 = new DateParser5(dateParser6);
        DateParser dateParser4 = new DateParser4(dateParser5);
        DateParser dateParser3 = new DateParser3(dateParser4);
        DateParser dateParser2 = new DateParser2(dateParser3);
        return new DateParser1(dateParser2);
    }
}
