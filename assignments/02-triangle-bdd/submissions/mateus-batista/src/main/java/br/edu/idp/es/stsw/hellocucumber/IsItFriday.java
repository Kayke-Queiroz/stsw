package br.edu.idp.es.stsw.hellocucumber;

public class IsItFriday {
    public static String isItFriday(String today) {
        return "Friday".equals(today) ? "TGIF" : "Nope";
    }
}
