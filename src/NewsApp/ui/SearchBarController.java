package NewsApp.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Controller for the search bar.
 * The search bar appear only when requested.
 */
public class SearchBarController {
    NewsReaderController newsReaderController;

    @FXML
    private AnchorPane searchSection;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXDatePicker fromDatePicker;
    @FXML
    private JFXDatePicker toDatePicker;
    @FXML
    private JFXButton searchButton;

    @FXML
    private void initialize() {
        this.setVisibility(false);
    }

    @FXML
    private void handleSearch() {
        if (searchField.getText().isEmpty()) {
            // TODO: Add warning label
        }

        String question = searchField.getText();

        // Convert from Local Date to Date
        Date fromDate = null;
        LocalDate fromLocalDate = fromDatePicker.getValue();
        if (fromLocalDate != null) {
            fromDate = Date.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        Date toDate = null;
        LocalDate toLocalDate = toDatePicker.getValue();
        if (toLocalDate != null) {
            toDate = Date.from(toLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        newsReaderController.searchNews(question, fromDate, toDate);
    }

    @FXML
    private void clearSearchResults() {
        newsReaderController.clearResults();
    }

    public void injectMasterController(NewsReaderController newsReaderController) {
        this.newsReaderController = newsReaderController;
    }

    public void setVisibility(boolean visibility) {
        searchSection.setVisible(visibility);
        searchSection.setManaged(visibility);
    }
}
