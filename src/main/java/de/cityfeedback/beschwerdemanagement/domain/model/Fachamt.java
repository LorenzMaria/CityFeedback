package de.cityfeedback.beschwerdemanagement.domain.model;

import de.cityfeedback.beschwerdemanagement.domain.valueObjects.Kategorie;

public record Fachamt(String name, Kategorie kategorie) {
}
