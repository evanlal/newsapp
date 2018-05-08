package myapp.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
    private void searchNewsButtonClicked() {
        newsReaderController.setSearchBarVisibility(true);
    }
}
