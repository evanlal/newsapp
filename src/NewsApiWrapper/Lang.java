/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

public enum Lang {
    DE ("de"),
    EN ("en"),
    FR ("fr"),
    IT ("it"),
    RU ("ru");

    private final String code;

    private Lang(String value) {
        this.code = value;
    }

    @Override
    public String toString() {
        return code;
    }
}
