package Schedule;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

import javafx.scene.text.Text;
import org.example.AppComponents;


public class AnchorPaneNode extends AnchorPane{

    // Date associated with this pane
    LocalDate date;
    TextField dateTextField;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);

        // Add action handler for mouse clicked
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.print(date);

            }
        });
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTextfields() {

        dateTextField = new TextField();
        dateTextField.setPrefSize(100, 30);
        dateTextField.setEditable(false);
    }

    public TextField getDateTextField() {
        return dateTextField;
    }

}
