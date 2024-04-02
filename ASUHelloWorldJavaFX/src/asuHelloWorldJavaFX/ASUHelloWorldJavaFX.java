package asuHelloWorldJavaFX;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
// 
//public class ASUHelloWorldJavaFX extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//    public void start(Stage primaryStage) {
//    	System.out.println("ASU Hello World!");
//    	System.out.println("It started!");
//        primaryStage.setTitle("ASU Hello World Spring 2024");
//        Button btn = new Button();
//        btn.setText("Display: 'ASU says: Hello World!'");
//        btn.setOnAction(new EventHandler<>() {
//            public void handle(ActionEvent event) {
//                System.out.println("ASU: Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
//    }
//}
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ASUHelloWorldJavaFX extends Application {
//
//    // Prices for each item
//    private static final Map<String, Double> itemPrices = new HashMap<>();
//    static {
//        itemPrices.put("Egg Sandwich", 7.99);
//        itemPrices.put("Bagel", 2.50);
//        itemPrices.put("Potato Salad", 4.49);
//        itemPrices.put("Chicken Sandwich", 9.99);
//        itemPrices.put("Coffee", 1.99);
//        itemPrices.put("Green Tea", 0.99);
//        itemPrices.put("Black Tea", 1.25);
//        itemPrices.put("Orange Juice", 2.25);
//    }
//
//    private double totalCost = 0.0;
//    private StringBuilder orderedItems = new StringBuilder();
//
//    @Override
//    public void start(Stage primaryStage) {
//        // Create labels for the categories
//        Label headingLabel = new Label("Joe's Deli");
//        headingLabel.setStyle("-fx-font-size: 24px;"); // Set font size
//
//        Label eatLabel = new Label("Eat:");
//        Label drinkLabel = new Label("Drink:");
//
//        // Create checkboxes for each item
//        CheckBox eggSandwichCheckBox = new CheckBox("Egg Sandwich");
//        CheckBox chickenSandwichCheckBox = new CheckBox("Chicken Sandwich");
//        CheckBox bagelCheckBox = new CheckBox("Bagel");
//        CheckBox potatoSaladCheckBox = new CheckBox("Potato Salad");
//
//        // Labels for Drink options
//        ToggleGroup drinkGroup = new ToggleGroup();
//        RadioButton coffeeRadioButton = new RadioButton("Coffee");
//        RadioButton greenTeaRadioButton = new RadioButton("Green Tea");
//        RadioButton blackTeaRadioButton = new RadioButton("Black Tea");
//        RadioButton orangeJuiceRadioButton = new RadioButton("Orange Juice");
//        coffeeRadioButton.setToggleGroup(drinkGroup);
//        greenTeaRadioButton.setToggleGroup(drinkGroup);
//        blackTeaRadioButton.setToggleGroup(drinkGroup);
//        orangeJuiceRadioButton.setToggleGroup(drinkGroup);
//
//        // Create a button to order, cancel, and confirm
//        Button orderButton = new Button("Order");
//        Button cancelButton = new Button("Cancel");
//        Button confirmButton = new Button("Confirm");
//
//        // Create a Label for bill area
//        Label billLabel = new Label("Bill");
//        billLabel.setAlignment(Pos.CENTER); // Center the label text
//
//        // Create a TextArea for the bill
//        TextArea billTextArea = new TextArea();
//        billTextArea.setEditable(false);
//        billTextArea.setPrefRowCount(10);
//
//        // Create a VBox for the bill area
//        VBox billVBox = new VBox(5, billLabel, billTextArea);
//        billVBox.setAlignment(Pos.CENTER); // Center align the VBox content
//
//        // Create a VBox for checkboxes
//        VBox eatVBox = new VBox(5, eatLabel, eggSandwichCheckBox, chickenSandwichCheckBox,
//                                bagelCheckBox, potatoSaladCheckBox);
//        VBox drinkVBox = new VBox(5, drinkLabel, coffeeRadioButton, greenTeaRadioButton,
//                                  blackTeaRadioButton, orangeJuiceRadioButton);
//
//        // Create an HBox to hold checkboxes
//        HBox selectionHBox = new HBox(20, eatVBox, drinkVBox);
//        selectionHBox.setPadding(new Insets(20));
//        selectionHBox.setStyle("-fx-background-color: #f0f0f0;");
//
//        // Create a VBox to hold selection
//        VBox selectionVBox = new VBox(headingLabel, selectionHBox);
//        selectionVBox.setAlignment(Pos.CENTER_LEFT);
//
//        // Create a VBox to hold bill and buttons
//        VBox rightVBox = new VBox(10, billVBox, new HBox(10, orderButton, cancelButton, confirmButton));
//        rightVBox.setAlignment(Pos.CENTER_RIGHT);
//
//        // Create an HBox to hold both selection and right VBox
//        HBox rootHBox = new HBox(selectionVBox, rightVBox);
//        rootHBox.setPadding(new Insets(20));
//        rootHBox.setAlignment(Pos.CENTER);
//
//        // Create a VBox to hold the entire content
//        VBox rootVBox = new VBox(headingLabel, rootHBox);
//        rootVBox.setAlignment(Pos.CENTER); // Center align the VBox content
//
//        // Create the scene
//        Scene scene = new Scene(rootVBox, 800, 400);
//
//        // Set the scene and show the stage
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Joe's Deli Application");
//        primaryStage.show();
//
//        // Event handler for the order button
//        orderButton.setOnAction(event -> {
//            orderedItems.setLength(0);
//            double totalItemsCost = 0.0;
//            if (eggSandwichCheckBox.isSelected()) {
//                double price = itemPrices.get("Egg Sandwich");
//                totalItemsCost += price;
//                orderedItems.append("Egg Sandwich - $").append(price).append("\n");
//            }
//            if (chickenSandwichCheckBox.isSelected()) {
//                double price = itemPrices.get("Chicken Sandwich");
//                totalItemsCost += price;
//                orderedItems.append("Chicken Sandwich - $").append(price).append("\n");
//            }
//            if (bagelCheckBox.isSelected()) {
//                double price = itemPrices.get("Bagel");
//                totalItemsCost += price;
//                orderedItems.append("Bagel - $").append(price).append("\n");
//            }
//            if (potatoSaladCheckBox.isSelected()) {
//                double price = itemPrices.get("Potato Salad");
//                totalItemsCost += price;
//                orderedItems.append("Potato Salad - $").append(price).append("\n");
//            }
//            if (coffeeRadioButton.isSelected()) {
//                double price = itemPrices.get("Coffee");
//                totalItemsCost += price;
//                orderedItems.append("Coffee - $").append(price).append("\n");
//            }
//            if (greenTeaRadioButton.isSelected()) {
//                double price = itemPrices.get("Green Tea");
//                totalItemsCost += price;
//                orderedItems.append("Green Tea - $").append(price).append("\n");
//            }
//            if (blackTeaRadioButton.isSelected()) {
//                double price = itemPrices.get("Black Tea");
//                totalItemsCost += price;
//                orderedItems.append("Black Tea - $").append(price).append("\n");
//            }
//            if (orangeJuiceRadioButton.isSelected()) {
//                double price = itemPrices.get("Orange Juice");
//                totalItemsCost += price;
//                orderedItems.append("Orange Juice - $").append(price).append("\n");
//            }
//            double tax = totalItemsCost * 0.07;
//            double totalBill = totalItemsCost + tax;
//            orderedItems.append("\nTax (7%): $").append(String.format("%.2f", tax)).append("\n");
//            orderedItems.append("Total: $").append(String.format("%.2f", totalBill));
//            totalCost = totalBill;
//            billTextArea.setText(orderedItems.toString());
//        });
//
//        // Event handler for the cancel button
//        cancelButton.setOnAction(event -> {
//            eggSandwichCheckBox.setSelected(false);
//            chickenSandwichCheckBox.setSelected(false);
//            bagelCheckBox.setSelected(false);
//            potatoSaladCheckBox.setSelected(false);
//            coffeeRadioButton.setSelected(false);
//            greenTeaRadioButton.setSelected(false);
//            blackTeaRadioButton.setSelected(false);
//            orangeJuiceRadioButton.setSelected(false);
//            billTextArea.clear();
//        });
//
//        // Event handler for the confirm button
//        confirmButton.setOnAction(event -> {
//            billTextArea.setText("Total: $" + String.format("%.2f", totalCost));
//        });
//
//        // Event handler for food checkboxes
//        eggSandwichCheckBox.setOnAction(event -> updateTotalCost(eggSandwichCheckBox.isSelected(), "Egg Sandwich"));
//        chickenSandwichCheckBox.setOnAction(event -> updateTotalCost(chickenSandwichCheckBox.isSelected(), "Chicken Sandwich"));
//        bagelCheckBox.setOnAction(event -> updateTotalCost(bagelCheckBox.isSelected(), "Bagel"));
//        potatoSaladCheckBox.setOnAction(event -> updateTotalCost(potatoSaladCheckBox.isSelected(), "Potato Salad"));
//
//        // Event handler for drink radio buttons
//        coffeeRadioButton.setOnAction(event -> updateTotalCost(coffeeRadioButton.isSelected(), "Coffee"));
//        greenTeaRadioButton.setOnAction(event -> updateTotalCost(greenTeaRadioButton.isSelected(), "Green Tea"));
//        blackTeaRadioButton.setOnAction(event -> updateTotalCost(blackTeaRadioButton.isSelected(), "Black Tea"));
//        orangeJuiceRadioButton.setOnAction(event -> updateTotalCost(orangeJuiceRadioButton.isSelected(), "Orange Juice"));
//    }
//
//    // Method to update total cost based on selected items
//    private void updateTotalCost(boolean isSelected, String itemName) {
//        if (isSelected) {
//            totalCost += itemPrices.get(itemName);
//        } else {
//            totalCost -= itemPrices.get(itemName);
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}



