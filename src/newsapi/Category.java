package newsapi;

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
