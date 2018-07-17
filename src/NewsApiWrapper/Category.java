/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

public enum Category {
    BUSINESS ("business"),
    ENTERTAINMENT ("entertainment"),
    GENERAL ("general"),
    HEALTH ("health"),
    SCIENCE ("science"),
    SPORTS ("sports"),
    TECHNOLOGY ("technology");

    private final String name;

    Category(String value) {
        this.name = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
