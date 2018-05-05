package newsapi;

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
