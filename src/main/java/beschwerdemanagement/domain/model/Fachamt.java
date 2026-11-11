package beschwerdemanagement.domain.model;

import beschwerdemanagement.domain.valueObjects.Kategorie;

public record Fachamt(String name, Kategorie kategorie) {
}
