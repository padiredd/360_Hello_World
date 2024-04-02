package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;

public class HW1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            VBox mainLayout = new VBox(10); // Main layout with spacing between children
            mainLayout.setPadding(new Insets(10, 10, 10, 10)); // Padding around the VBox
            mainLayout.setAlignment(Pos.CENTER); // Set alignment to center
            
            // Welcome label
            Label welcomeLabel = new Label("Welcome to Heart Health Imaging and Recording System");
            mainLayout.getChildren().add(welcomeLabel);

            // Buttons to navigate to different views
            Button patientIntakeBtn = new Button("Patient Intake");
            Button ctScanTechViewBtn = new Button("CT Scan Tech View");
            Button patientViewBtn = new Button("Patient View");

            // Add buttons to the main layout
            mainLayout.getChildren().addAll(patientIntakeBtn, ctScanTechViewBtn, patientViewBtn);

            // Set the main scene
            Scene scene = new Scene(mainLayout, 400, 400);
            primaryStage.setTitle("Heart Health System");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Event handlers for buttons
            patientIntakeBtn.setOnAction(e -> displayPatientIntakeView(primaryStage));
            ctScanTechViewBtn.setOnAction(e -> displayCtScanTechView(primaryStage));
            patientViewBtn.setOnAction(e -> displayPatientView(primaryStage));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayPatientIntakeView(Stage primaryStage) {
        GridPane intakeFormLayout = new GridPane();
        intakeFormLayout.setVgap(10);
        intakeFormLayout.setHgap(10);
        intakeFormLayout.setPadding(new Insets(10, 10, 10, 10));

        // Create labels and text fields for patient information
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberField = new TextField();

        Label healthHistoryLabel = new Label("Health History:");
        TextField healthHistoryField = new TextField();

        Label insuranceIdLabel = new Label("Insurance ID:");
        TextField insuranceIdField = new TextField();

        Button saveButton = new Button("Save");

        // Add labels and text fields to the grid
        intakeFormLayout.addRow(0, firstNameLabel, firstNameField);
        intakeFormLayout.addRow(1, lastNameLabel, lastNameField);
        intakeFormLayout.addRow(2, emailLabel, emailField);
        intakeFormLayout.addRow(3, phoneNumberLabel, phoneNumberField);
        intakeFormLayout.addRow(4, healthHistoryLabel, healthHistoryField);
        intakeFormLayout.addRow(5, insuranceIdLabel, insuranceIdField);

        // Add save button
        intakeFormLayout.add(saveButton, 1, 6);

        // Handle the save button click
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Validate input fields are not empty
                if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty()
                    || phoneNumberField.getText().isEmpty() || healthHistoryField.getText().isEmpty() || insuranceIdField.getText().isEmpty()) {
                    showAlert("Error", "All fields must be filled out.");
                    return;
                }

                // Generate a unique patient ID
                String patientId = generatePatientId();
                if (patientId == null) {
                    showAlert("Error", "Failed to generate a unique patient ID.");
                    return;
                }

                // Define the file name with the patient ID
                String fileName = "PatientInfo_" + patientId + ".txt";

                // Attempt to save the information to the file
                try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                    writer.println("First Name: " + firstNameField.getText());
                    writer.println("Last Name: " + lastNameField.getText());
                    writer.println("Email: " + emailField.getText());
                    writer.println("Phone Number: " + phoneNumberField.getText());
                    writer.println("Health History: " + healthHistoryField.getText());
                    writer.println("Insurance ID: " + insuranceIdField.getText());
                    showAlert("Success", "Patient information saved with ID: " + patientId);
                    // Go back to the main UI
                    start(primaryStage);
                } catch (IOException e) {
                    showAlert("Error", "Failed to save patient information.");
                }
            }
        });

        // Set the new scene
        Scene intakeScene = new Scene(intakeFormLayout, 400, 400);
        primaryStage.setScene(intakeScene);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private String generatePatientId() {
        String idFilePath = "lastPatientId.txt"; // File where the last patient ID is stored
        File idFile = new File(idFilePath);
        int lastId = 0;

        // Try to read the last ID from the file
        if (idFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(idFile))) {
                String lastIdStr = reader.readLine();
                if (lastIdStr != null && !lastIdStr.isEmpty()) {
                    lastId = Integer.parseInt(lastIdStr);
                }
            } catch (IOException | NumberFormatException e) {
                showAlert("Error", "Failed to read the last patient ID. Please check the file and try again.");
                return null; // Returning null or you could return a default value or handle it differently
            }
        }

        // Increment the ID for the new patient
        int newId = lastId + 1;

        // Try to save the new last ID back to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(idFile))) {
            writer.println(newId);
        } catch (IOException e) {
            showAlert("Error", "Failed to save the new patient ID. Please check the file permissions and try again.");
            return null; // Returning null or you could return a default value or handle it differently
        }

        // Return the new ID as a string
        return String.format("%05d", newId); // Formats the ID as a 5-digit number
    }
    

    private void displayCtScanTechView(Stage primaryStage) {
        GridPane techFormLayout = new GridPane();
        techFormLayout.setVgap(10);
        techFormLayout.setHgap(10);
        techFormLayout.setPadding(new Insets(10, 10, 10, 10));

        // Create input fields for CT scan data
        Label patientIdLabel = new Label("Patient ID:");
        TextField patientIdField = new TextField();
        
        Label totalAgatstonLabel = new Label("The total Agatston CAC score:");
        TextField totalAgatstonField = new TextField();
        
        Label lmLabel = new Label("LM:");
        TextField lmField = new TextField();
        
        Label ladLabel = new Label("LAD:");
        TextField ladField = new TextField();
        
        Label lcxLabel = new Label("LCX:");
        TextField lcxField = new TextField();
        
        Label rcaLabel = new Label("RCA:");
        TextField rcaField = new TextField();
        
        Label pdaLabel = new Label("PDA:");
        TextField pdaField = new TextField();
        
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            // Validate the fields and save data
            String patientId = patientIdField.getText();
            if (!patientId.isEmpty() && !totalAgatstonField.getText().isEmpty() &&
                !lmField.getText().isEmpty() && !ladField.getText().isEmpty() &&
                !lcxField.getText().isEmpty() && !rcaField.getText().isEmpty() &&
                !pdaField.getText().isEmpty()) {
                
                // Save the CT scan data to a file named with the patient ID
                saveCtScanData(patientId, totalAgatstonField.getText(), lmField.getText(),
                    ladField.getText(), lcxField.getText(), rcaField.getText(), pdaField.getText());
                
                // Go back to the main UI
                start(primaryStage);
            } else {
                showAlert("Error", "All fields must be filled out.");
            }
        });

        // Add components to the grid
        techFormLayout.addRow(0, patientIdLabel, patientIdField);
        techFormLayout.addRow(1, totalAgatstonLabel, totalAgatstonField);
        techFormLayout.addRow(2, lmLabel, lmField);
        techFormLayout.addRow(3, ladLabel, ladField);
        techFormLayout.addRow(4, lcxLabel, lcxField);
        techFormLayout.addRow(5, rcaLabel, rcaField);
        techFormLayout.addRow(6, pdaLabel, pdaField);
        techFormLayout.add(saveButton, 1, 7);

        // Set the new scene
        primaryStage.setScene(new Scene(techFormLayout, 600, 400));
        primaryStage.setTitle("CT Scan Technician View");
        primaryStage.show();
    }
    
    private void saveCtScanData(String patientId, String totalAgatstonScore, String lmScore, String ladScore, String lcxScore, String rcaScore, String pdaScore) {
        String fileName = patientId + "_CTScanResults.txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Patient ID: " + patientId);
            writer.println("Total Agatston CAC Score: " + totalAgatstonScore);
            writer.println("LM: " + lmScore);
            writer.println("LAD: " + ladScore);
            writer.println("LCX: " + lcxScore);
            writer.println("RCA: " + rcaScore);
            writer.println("PDA: " + pdaScore);
        } catch (IOException e) {
            showAlert("Error", "Failed to save CT scan data.");
        }
    }

    private void displayPatientView(Stage primaryStage) {
        GridPane patientViewLayout = new GridPane();
        patientViewLayout.setVgap(10);
        patientViewLayout.setHgap(10);
        patientViewLayout.setPadding(new Insets(10, 10, 10, 10));

        // Create input field for patient ID and a button to trigger the result display
        Label patientIdLabel = new Label("Patient ID:");
        TextField patientIdField = new TextField();
        Button viewResultsButton = new Button("View Results");

        // Create labels and text fields for displaying CT scan results
        TextField totalAgatstonField = new TextField();
        totalAgatstonField.setEditable(false);
        TextField lmField = new TextField();
        lmField.setEditable(false);
        TextField ladField = new TextField();
        ladField.setEditable(false);
        TextField lcxField = new TextField();
        lcxField.setEditable(false);
        TextField rcaField = new TextField();
        rcaField.setEditable(false);
        TextField pdaField = new TextField();
        pdaField.setEditable(false);

        // Add components to the grid
        patientViewLayout.addRow(0, patientIdLabel, patientIdField, viewResultsButton);
        patientViewLayout.addRow(1, new Label("The total Agatston CAC score:"), totalAgatstonField);
        patientViewLayout.addRow(2, new Label("LM:"), lmField);
        patientViewLayout.addRow(3, new Label("LAD:"), ladField);
        patientViewLayout.addRow(4, new Label("LCX:"), lcxField);
        patientViewLayout.addRow(5, new Label("RCA:"), rcaField);
        patientViewLayout.addRow(6, new Label("PDA:"), pdaField);

        // Event handler for the view results button
        viewResultsButton.setOnAction(event -> {
            String patientId = patientIdField.getText();
            if (!patientId.isEmpty()) {
                loadCtScanResults(patientId, totalAgatstonField, lmField, ladField, lcxField, rcaField, pdaField);
            } else {
                showAlert("Error", "Please enter a valid Patient ID.");
            }
        });

        // Set the new scene
        Scene patientScene = new Scene(patientViewLayout, 600, 400);
        primaryStage.setScene(patientScene);
        primaryStage.setTitle("Patient View");
        primaryStage.show();
    }
    
    private void loadCtScanResults(String patientId, TextField totalAgatstonField, TextField lmField, TextField ladField, TextField lcxField, TextField rcaField, TextField pdaField) {
        String fileName = patientId + "_CTScanResults.txt";
        File file = new File(fileName);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        switch (parts[0]) {
                            case "Total Agatston CAC Score":
                                totalAgatstonField.setText(parts[1]);
                                break;
                            case "LM":
                                lmField.setText(parts[1]);
                                break;
                            case "LAD":
                                ladField.setText(parts[1]);
                                break;
                            case "LCX":
                                lcxField.setText(parts[1]);
                                break;
                            case "RCA":
                                rcaField.setText(parts[1]);
                                break;
                            case "PDA":
                                pdaField.setText(parts[1]);
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                showAlert("Error", "An error occurred while reading the CT scan results.");
            }
        } else {
            showAlert("Information", "No CT scan results available for the entered Patient ID.");
        }
    }
   
    public static void main(String[] args) {
        launch(args);
    }
}
