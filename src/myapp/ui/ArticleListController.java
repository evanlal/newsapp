package myapp.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import myapp.model.Article;

/**
 * Controller for the main list view
 */
public class ArticleListController {
    private NewsReaderController newsReaderController;

    @FXML
    private AnchorPane listWrapper;
    @FXML
    private JFXListView<Article> articlesListView;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private JFXButton loadMoreButton;

    @FXML
    private void initialize() {
        articlesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        progressBar.setVisible(false);
        loadMoreButton.setVisible(false);

        // Custom list view cell factory
        articlesListView.setCellFactory(articlesListView -> {

            // Important - Create the cells only once!
            ArticleCell articleCell = new ArticleCell();
            JFXListCell<Article> cell = new JFXListCell<Article>() {

                // Only ypdate the content of the cells,
                // don't create them inside updateItem
                @Override
                protected void updateItem(Article article, boolean empty) {
                    super.updateItem(article, empty);
                    this.setGraphic(null);
                    if(empty) {
                        setGraphic(null);
                    } else {
                        articleCell.setContent(article);
                        setText(null);
                        setGraphic(articleCell.getGridPane());
                    }
                }
            };
            return cell;
        });
    }

    @FXML
    private void listClickHandler() {
        newsReaderController.updateFullView(
                articlesListView.getSelectionModel().getSelectedItem()
        );
    }

    @FXML
    private void loadMoreClickHandler() {
        newsReaderController.loadMoreSearchResults();
    }

    public void injectMasterController(NewsReaderController newsReaderController) {
        this.newsReaderController = newsReaderController;
    }

    public JFXListView<Article> getArticlesListView() {
        return articlesListView;
    }

    public JFXProgressBar getProgressBar() {
        return progressBar;
    }

    public JFXButton getLoadMoreButton() {
        return loadMoreButton;
    }
}
