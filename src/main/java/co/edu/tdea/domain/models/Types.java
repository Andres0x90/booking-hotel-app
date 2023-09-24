package co.edu.tdea.domain.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Types {
    EXECUTIVE,
    SUITE,
    SIMPLE;

    public static boolean exist(String value) {
        for (Types types : values()) {
            if (types.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
