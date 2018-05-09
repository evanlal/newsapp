package NewsApp.ui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

/**
 * Controller for the Main Menu
 */
public class MainMenuController {
    NewsReaderController newsReaderController;

    @FXML
    private JFXButton breakingNewsButton;
    @FXML
    private JFXButton searchNewsButton;

    public void injectMasterController(NewsReaderController newsReaderController) {
        this.newsReaderController = newsReaderController;
    }

    @FXML
    private void breakingNewsButtonClicked() {
        newsReaderController.setSearchVisibility(false);
        newsReaderController.fetchBreakingNews();
    }

    @FXML
    private void searchNewsButtonClicked() {
        newsReaderController.setSearchVisibility(true);
    }
}
