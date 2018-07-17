/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

public enum SortBy {
    RELEVANCY ("relevancy"), POPULARITY ("popularity"), PUBLISHED_AT ("publishedAt");

    private final String sortby;

    SortBy(String value) {
        this.sortby = value;
    }

    @Override
    public String toString() {
        return sortby;
    }
}
