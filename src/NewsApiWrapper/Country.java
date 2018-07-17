/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

public enum Country {
    GB("gb"),
    GR("gr"),
    US("us");

    private final String code;

    Country(String value) {
        code = value;
    }

    @Override
    public String toString() {
        return code;
    }
}
