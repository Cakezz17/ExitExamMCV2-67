package view;

import java.util.HashMap;
import java.util.Map;

import controller.SuitController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.suit;

public class viewSuits extends Application{
    private SuitController controller = new SuitController();
    
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Enter Suit ID");
        TextField suitIdInput = new TextField();
        Button checkButton = new Button("Check Suit");
        Label resultLabel = new Label();
        Button repairButton = new Button("Repair Suit");
        repairButton.setVisible(false);
        Map<String, Integer> repairCnt = new HashMap<>();
        checkButton.setOnAction(e -> {
            String suitId = suitIdInput.getText();
            suit foundSuit = controller.findsuitById(suitId);
            
            if (foundSuit != null) {
                String suitType = foundSuit.getType();
                resultLabel.setText("Suit ID = " + suitId + "\nSuit Type = " + suitType);

                if (foundSuit.isDurabilityValid()) {
                    resultLabel.setText(resultLabel.getText() + "\nSuit is valid");
                    repairButton.setVisible(false);
                } else {
                    resultLabel.setText(resultLabel.getText() + "\nSuit durability is too low Durability = " + foundSuit.getDurability());
                    repairButton.setVisible(true);
                    repairButton.setOnAction(ev -> {
                        foundSuit.repair();
                        resultLabel.setText(resultLabel.getText() + "\nSuit repaired to = " + foundSuit.getDurability());
                        
                        repairCnt.put(suitType, repairCnt.getOrDefault(suitType, 0) + 1);
                        resultLabel.setText(resultLabel.getText() + "\n" + suitType + " repaired " + repairCnt.get(suitType) + " times");
                        repairButton.setVisible(false);
                    });
                }
            } else {
                resultLabel.setText("Suit not found");
                repairButton.setVisible(false);
            }
        });
        
        VBox layout = new VBox(10, label, suitIdInput, checkButton, resultLabel, repairButton);
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Superhero Suit Checker");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
