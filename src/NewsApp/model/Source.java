package NewsApp.model;


/**
 * A news source object
 * TODO: Add source functionallity
 */
public class Source {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;

    /**
     * Convert a NewsApiSource to a Source object
     * @param newsApiSource
     */
    public Source(NewsApiWrapper.Source newsApiSource) {
        this.id = newsApiSource.getId();
        this.name = newsApiSource.getName();
        this.description = newsApiSource.getDescription();
        this.url = newsApiSource.getUrl();
        this.category = newsApiSource.getUrl();
        this.language = newsApiSource.getLanguage();
        this.country = newsApiSource.getCountry();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
