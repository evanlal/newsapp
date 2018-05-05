package myapp;

/**
 * An article object
 */
public class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;


    public Article(NewsApiWrapper.Article newsApiArticle) {
        this.source = new Source(newsApiArticle.getSource());
        this.author = newsApiArticle.getAuthor();
        this.title = newsApiArticle.getTitle();
        this.description = newsApiArticle.getDescription();
        this.url = newsApiArticle.getDescription();
        this.urlToImage = newsApiArticle.getUrlToImage();
        this.publishedAt = newsApiArticle.getPublishedAt();
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    @Override
    public String toString() {
        return "Article{" +
                "source=" + source.getName() +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}
