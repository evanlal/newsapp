package newsapi;

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
