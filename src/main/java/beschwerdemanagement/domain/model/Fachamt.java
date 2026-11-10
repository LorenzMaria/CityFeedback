package beschwerdemanagement.domain.model;

import beschwerdemanagement.domain.valueObjects.Kategorie;

public class Fachamt {

    private final String name;
    private final Kategorie kategorie;

    public Fachamt(String name, Kategorie kategorie) {
        this.name = name;
        this.kategorie = kategorie;
    }

    public String getName() {
        return name;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }
}
