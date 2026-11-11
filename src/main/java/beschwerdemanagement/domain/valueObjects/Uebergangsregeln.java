package beschwerdemanagement.domain.valueObjects;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * Deklarative Tabelle erlaubter Zustandsuebergaenge fuer einen Enum-Typ.
 */
public final class Uebergangsregeln<S extends Enum<S>> {

    private final Class<S> typ;
    private final Map<S, Set<S>> erlaubt;

    private Uebergangsregeln(Class<S> typ) {
        this.typ = typ;
        this.erlaubt = new EnumMap<>(typ);
    }

    public static <S extends Enum<S>> Uebergangsregeln<S> fuer(Class<S> typ) {
        return new Uebergangsregeln<>(typ);
    }

    @SafeVarargs
    public final Uebergangsregeln<S> erlaube(S von, S... nach) {
        Set<S> ziele = erlaubt.computeIfAbsent(von, k -> EnumSet.noneOf(typ));
        ziele.addAll(Arrays.asList(nach));
        return this;
    }

    public boolean istErlaubt(S von, S nach) {
        Set<S> ziele = erlaubt.get(von);
        return ziele != null && ziele.contains(nach);
    }
}
