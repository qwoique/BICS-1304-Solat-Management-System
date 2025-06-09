package com.mycompany.grupprojekoop;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
//import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    private SolatManagementSystem system = new SolatManagementSystem();//to access SolatManagementSystem class
    private Stage primaryStage;
    private TextField nameField;
    private TextField userIdField;
    private PasswordField passwordField;
    Alert info = new Alert(Alert.AlertType.INFORMATION);
    Alert alert = new Alert(Alert.AlertType.WARNING);
    Button loginButton = new Button("Login");
    Button registerButton = new Button("Register");
    Button addButton = new Button("Add Schedule");
    Button deleteButton = new Button("Delete Schedule");
    Button exitButton = new Button("Exit");
    Label roleLabel = new Label("Role:");
    Label prayerLabel = new Label("Prayer time:");
    private ComboBox<String> prayerComboBox;
    private DatePicker datePicker;
    private ObservableList<PrayerSchedule> scheduleList = FXCollections.observableArrayList();
    ToggleGroup roleGroup = new ToggleGroup(); // make only can select 1

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.setTitle("Prayer Schedule");
        stage.setScene(loginScene());
        stage.show();
    }
    
    private Scene loginScene(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        
        Label title = new Label("SOLAT MANAGEMENT SYSTEM");
        title.setFont(Font.font("Arial", 20));
        
        Label userIdLabel = new Label("UserId:");
        userIdLabel.setFont(Font.font("Arial", 15));
        nameField = new TextField();
        nameField.setPromptText("Enter your userId");
        
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", 15));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        
        loginButton.setFont(Font.font("Arial", 15));
        loginButton.setStyle("-fx-background-color: #556B2F;");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setOnAction(e -> {
            info.setTitle("No information");
            info.setHeaderText(null);
            
            String userId = nameField.getText();
            String password = passwordField.getText();
            
            if(userId.isEmpty() || password.isEmpty()){
                info.setContentText("Plese enter all field.");
                info.showAndWait();
                return;
            }
            boolean loggedIn = system.login(userId, password);
            if (loggedIn) {
                primaryStage.setScene(mainScene()); // 
            } else {
                info.setContentText("Login fail. Please try again.");
                info.showAndWait();
            }
            
        });
        exitButton.setOnAction(e -> primaryStage.close());
        exitButton.setFont(Font.font("Arial", 15));
        exitButton.setStyle("-fx-background-color: #556B2F;");
        exitButton.setTextFill(Color.WHITE);
        registerButton.setOnAction(e -> primaryStage.setScene(RegisterScene()));
        registerButton.setFont(Font.font("Arial", 15));
        registerButton.setStyle("-fx-background-color: #556B2F;");
        registerButton.setTextFill(Color.WHITE);
        
        HBox box = new HBox(10,exitButton, loginButton, registerButton);
        
        grid.add(title, 0, 0, 2,1);
        grid.add(userIdLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(box, 1, 3);
        
        VBox layout = new VBox(grid);
        layout.setPadding(new Insets(40,40,40,40));//top, right, bottom , left
        layout.setStyle("-fx-background-color: #8FBC8F;");
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout, 800, 500);
    }
    
    private Scene RegisterScene(){
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 40, 40));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        
        Label title = new Label("REGISTER USER");
        title.setFont(Font.font("Arial", 20));
        
        Label userIdLabel = new Label("UserId:");
        userIdLabel.setFont(Font.font("Arial", 15));
        userIdField = new TextField();
        userIdField.setPromptText("Enter your userId");
        
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font("Arial", 15));
        nameField= new TextField();
        nameField.setPromptText("Enter your name");
        
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", 15));
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        
        Button signUpButton = new Button("Sign up");
        signUpButton.setFont(Font.font("Arial", 15));
        signUpButton.setStyle("-fx-background-color: #556B2F;");
        signUpButton.setTextFill(Color.WHITE);
        signUpButton.setOnAction(e -> handleRegister());
        
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 15));
        backButton.setStyle("-fx-background-color: #556B2F;");
        backButton.setTextFill(Color.WHITE);
        backButton.setOnAction(e -> primaryStage.setScene(loginScene()));
        
        grid.add(title, 0, 0, 2,1);
        grid.add(userIdLabel, 0, 1);
        grid.add(userIdField, 1, 1);
        grid.add(nameLabel, 0, 2);
        grid.add(nameField, 1, 2);
        grid.add(passwordLabel, 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(backButton, 0, 4);
        grid.add(signUpButton, 1, 4);
        
        VBox layout = new VBox(grid);
        layout.setPadding(new Insets(40,40,40,40));//top, right, bottom , left
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #8FBC8F;");
        return new Scene(layout, 800, 500);
    }
   
    private void handleRegister(){
        String userId = userIdField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();
        
        if (userId.isEmpty() || name.isEmpty() || password.isEmpty()) {
            alert.setTitle("Registration");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }
        boolean registered = system.registerStaff(userId, name, password);
        if (registered) {
            info.setTitle("Registration");
            info.setHeaderText(null);
            info.setContentText("Registration successful!");
            info.showAndWait();
            primaryStage.setScene(loginScene());
        } else {
            alert.setTitle("Registration");
            alert.setHeaderText(null);
            alert.setContentText("User ID already exists. Please choose another one.");
            alert.showAndWait();
        }
    
    }
    
    private Scene mainScene(){
        VBox layout = new VBox(20);
        Label label = new Label("SOLAT MANAGEMENT SYSTEM");
        label.setFont(Font.font("Arial", 20));
        
        TableView<PrayerSchedule> tableView = new TableView<>();
        ObservableList<PrayerSchedule> scheduleList = FXCollections.observableArrayList(PrayerSchedule.readFromFile("prayerSchedule.txt"));
        tableView.setItems(scheduleList);

        // Columns
        TableColumn<PrayerSchedule, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDate()));

        TableColumn<PrayerSchedule, String> prayerCol = new TableColumn<>("Prayer");
        prayerCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPrayerTime()));

        TableColumn<PrayerSchedule, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getRole()));

        TableColumn<PrayerSchedule, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));

        tableView.getColumns().addAll(dateCol, prayerCol, roleCol, nameCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //automatically resize column
        
        //load from file to table
        ArrayList<PrayerSchedule> dataFromFile = PrayerSchedule.readFromFile("prayerSchedule.txt");
        
        addButton.setFont(Font.font("Arial", 15));
        addButton.setStyle("-fx-background-color: #556B2F;");
        addButton.setTextFill(Color.WHITE);
        addButton.setOnAction(e -> primaryStage.setScene(addScene()));
        
        Button updateButton = new Button("Update");
        updateButton.setFont(Font.font("Arial", 15));
        updateButton.setStyle("-fx-background-color: #556B2F;");
        updateButton.setTextFill(Color.WHITE);
        updateButton.setOnAction(e -> primaryStage.setScene(updateScene()));
        
        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font("Arial", 15));
        logoutButton.setStyle("-fx-background-color: #556B2F;");
        logoutButton.setTextFill(Color.WHITE);
        logoutButton.setOnAction(e -> {
            system.logout();
            primaryStage.setScene(loginScene());
        });
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label,tableView, addButton, updateButton,  logoutButton);
        layout.setStyle("-fx-background-color: #8FBC8F;");
        return new Scene(layout, 800, 500);
    }
    
    private Scene addScene() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

        Label title = new Label("ADD PRAYER SCHEDULE");
        title.setFont(Font.font("Arial", 20));
        title.setTextFill(Color.DARKGREEN);
        grid.add(title, 0, 0, 2, 1);

        // Date picker and label
        Label dateLabel = new Label("Select Date:");
        dateLabel.setFont(Font.font("Arial", 15));
        datePicker = new DatePicker();
        datePicker.setShowWeekNumbers(true);

        Label selectedDateLabel = new Label("No date selected");
        selectedDateLabel.setFont(Font.font("Arial", 15));
        datePicker.setOnAction(e -> {
            LocalDate selectedDate = datePicker.getValue();
            selectedDateLabel.setText("Date: " + selectedDate);
        });

        grid.add(dateLabel, 0, 1);
        grid.add(datePicker, 1, 1);
        grid.add(selectedDateLabel, 1, 2);

        //prayer combo box
        prayerComboBox = new ComboBox<>();
        prayerComboBox.getItems().addAll("Subuh", "Zohor", "Asar", "Maghrib", "Isya", "Jumaat");
        prayerComboBox.setValue("Subuh");
        
        prayerLabel.setFont(Font.font("Arial", 15));
        grid.add(prayerLabel, 0, 3);
        grid.add(prayerComboBox, 1, 3);
        
        //role radio button
        RadioButton imamRB = new RadioButton("Imam");
        imamRB.setFont(Font.font("Arial", 15));
        RadioButton bilalRB = new RadioButton("Bilal");
        bilalRB.setFont(Font.font("Arial", 15));
        RadioButton khutbahRB = new RadioButton("Khutbah Reader");
        khutbahRB.setFont(Font.font("Arial", 15));
        
        imamRB.setToggleGroup(roleGroup);
        bilalRB.setToggleGroup(roleGroup);
        khutbahRB.setToggleGroup(roleGroup);
        imamRB.setSelected(true);
        
        HBox roleHBox = new HBox(10, imamRB, bilalRB, khutbahRB);
        roleLabel.setFont(Font.font("Arial", 15));
        grid.add(roleLabel, 0, 4);
        grid.add(roleHBox, 1, 4);
        
        //name
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font("Arial", 15));
        nameField = new TextField();
        nameField.setPromptText("Enter your name");
        grid.add(nameLabel, 0, 5);
        grid.add(nameField, 1, 5);

        //confirm & back button
        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(Font.font("Arial", 15));
        confirmButton.setStyle("-fx-background-color: #556B2F;");
        confirmButton.setTextFill(Color.WHITE);
        
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 15));
        backButton.setStyle("-fx-background-color: #556B2F;");
        backButton.setTextFill(Color.WHITE);

        confirmButton.setOnAction(e -> handleSchedule());
        
        backButton.setOnAction(e -> primaryStage.setScene(mainScene()));

        HBox buttonBox = new HBox(15, confirmButton, backButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        grid.add(buttonBox, 0, 6);
        grid.setStyle("-fx-background-color: #8FBC8F;");

        return new Scene(grid, 800, 500);
    }
    
    private void handleSchedule(){
        info.setTitle("Schedule Added!");
        info.setHeaderText(null);
        
        alert.setTitle("Incomplete Data");
        alert.setHeaderText(null);
        
        LocalDate date = datePicker.getValue();
        String prayer = prayerComboBox.getValue();
        String role = ((RadioButton) roleGroup.getSelectedToggle()).getText(); //will return selected radio button
        String name = nameField.getText();
            
        if (date != null && prayer != null && role != null && !name.isEmpty()){
            scheduleList.add(new PrayerSchedule(date, prayer, role, name));
            info.setContentText("Successfully added schedule.");
            info.showAndWait();
        }
        else{
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        }
        PrayerSchedule schedule = new PrayerSchedule(date, prayer, role, name);
        schedule.writeToFile("prayerSchedule.txt");
     
    }
    
    private Scene updateScene() {
    VBox layout = new VBox(15);
    layout.setPadding(new Insets(20));
    layout.setAlignment(Pos.CENTER);

    Label title = new Label("UPDATE PRAYER SCHEDULE");
    title.setFont(Font.font("Arial", 18));

    // DatePicker for filtering
    Label dateLabel = new Label("Search by Date:");
    dateLabel.setFont(Font.font("Arial", 15));
    DatePicker searchDatePicker = new DatePicker();

    // TableView for schedule display
    TableView<PrayerSchedule> tableView = new TableView<>();
    ObservableList<PrayerSchedule> filteredList = FXCollections.observableArrayList();
    tableView.setItems(filteredList);

    // Columns
    TableColumn<PrayerSchedule, LocalDate> dateCol = new TableColumn<>("Date");
    dateCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDate()));

    TableColumn<PrayerSchedule, String> prayerCol = new TableColumn<>("Prayer");
    prayerCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPrayerTime()));

    TableColumn<PrayerSchedule, String> roleCol = new TableColumn<>("Role");
    roleCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getRole()));
    
    TableColumn<PrayerSchedule, String> nameCol = new TableColumn<>("Name");
    nameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));

    tableView.getColumns().addAll(dateCol, prayerCol, roleCol, nameCol);
    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    //load from file to table
    ArrayList<PrayerSchedule> dataFromFile = PrayerSchedule.readFromFile("prayerSchedule.txt");

    // Search date action
    searchDatePicker.setOnAction(e -> {
        LocalDate selectedDate = searchDatePicker.getValue();
        filteredList.clear();
        for (PrayerSchedule s : dataFromFile) {
            if (s.getDate().equals(selectedDate)) {
                filteredList.add(s);
            }
        }
    });

    // Delete button
    deleteButton = new Button("Delete selected");
    deleteButton.setFont(Font.font("Arial", 15));
    deleteButton.setStyle("-fx-background-color: #556B2F;");
    deleteButton.setTextFill(Color.WHITE);
    deleteButton.setOnAction(e -> {
        PrayerSchedule selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dataFromFile.remove(selected);
            filteredList.remove(selected);
            PrayerSchedule.writeAllSchedules("prayerSchedule.txt", dataFromFile);
            info.setTitle("Deleted");
            info.setHeaderText(null);
            info.setContentText("Deleted successfully.");
            info.showAndWait();
        } else {
            alert.setTitle("Not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select to delete");
            alert.showAndWait();
        }
    });

    Button backButton = new Button("Back");
    backButton.setFont(Font.font("Arial", 15));
    backButton.setStyle("-fx-background-color: #556B2F;");
    backButton.setTextFill(Color.WHITE);
    backButton.setOnAction(e -> primaryStage.setScene(mainScene()));
    
    Button editButton = new Button("Edit Selected");
    editButton.setFont(Font.font("Arial", 15));
    editButton.setStyle("-fx-background-color: #556B2F;");
    editButton.setTextFill(Color.WHITE);
    editButton.setOnAction(e -> {
        PrayerSchedule selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
        editScene(selected, dataFromFile, filteredList, tableView);
    } else {
        alert.setTitle("Not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a schedule to edit");
            alert.showAndWait();
    }
    });

    HBox btnBox = new HBox(10, editButton ,deleteButton, backButton);
    btnBox.setAlignment(Pos.CENTER);

    layout.getChildren().addAll(title, dateLabel, searchDatePicker, tableView, btnBox);
    layout.setStyle("-fx-background-color: #8FBC8F;");
    return new Scene(layout, 800, 500);
    }
    
    private void editScene(PrayerSchedule schedule, ArrayList<PrayerSchedule> dataFromFile, ObservableList<PrayerSchedule> filteredList, TableView<PrayerSchedule> tableView){
        Stage popup = new Stage();
        popup.setTitle("Edit Prayer Schedule");
        
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #8FBC8F;");
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        DatePicker datePicker = new DatePicker(schedule.getDate());
        ComboBox<String> prayerBox = new ComboBox<>();
        prayerBox.getItems().addAll("Subuh", "Zohor", "Asar", "Maghrib", "Isya", "Jumaat");
        prayerBox.setValue(schedule.getPrayerTime());

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Imam", "Bilal", "Khutbah Reader");
        roleBox.setValue(schedule.getRole());

        TextField nameField = new TextField(schedule.getName());

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font("Arial", 15));
        saveButton.setStyle("-fx-background-color: #556B2F;");
        saveButton.setTextFill(Color.WHITE);
        saveButton.setOnAction(e -> {
            // Update object
            schedule.setDate(datePicker.getValue());
            schedule.setPrayerTime(prayerBox.getValue());
            schedule.setRole(roleBox.getValue());
            schedule.setName(nameField.getText());
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("prayerSchedule.txt"))) {
            for (PrayerSchedule s : dataFromFile) {
                writer.write(s.toFileString());
                writer.newLine();
            }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            
            // Refresh table
            tableView.refresh();
            
            popup.close();
        });
        
        layout.getChildren().addAll(
        new Label("Date:"), datePicker,
        new Label("Prayer:"), prayerBox,
        new Label("Role:"), roleBox,
        new Label("Name:"), nameField,
        saveButton
        );
        
        popup.setScene(new Scene(layout, 300,400));
        popup.showAndWait();
    }
    
    public static void main(String[] args) {
        launch();
    }

}