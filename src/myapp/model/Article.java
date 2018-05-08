package myapp.model;

import javafx.scene.image.Image;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Image thumbnail;
    private Date publishedAt;


    public Article(NewsApiWrapper.Article newsApiArticle) {
        this.source = new Source(newsApiArticle.getSource());
        this.author = newsApiArticle.getAuthor();
        this.title = newsApiArticle.getTitle();
        this.description = newsApiArticle.getDescription();
        this.url = newsApiArticle.getUrl();
        this.urlToImage = newsApiArticle.getUrlToImage();

        try {
            DateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            this.publishedAt = iso8601.parse(newsApiArticle.getPublishedAt());
        } catch (ParseException e) {
            this.publishedAt = null;
        }

        if (this.urlToImage != null) {
            try {
                this.thumbnail = new Image(urlToImage, 150, 150, true, true);
            }catch (IllegalArgumentException e) {
                System.out.println(urlToImage);
                this.thumbnail = null;
            }
        } else {
            this.thumbnail = null;
        }
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

    public Date getPublishedAt() {
        return publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public Image getThumbnail() {
        return thumbnail;
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
