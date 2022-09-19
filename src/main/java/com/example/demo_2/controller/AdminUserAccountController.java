package com.example.demo_2.controller;

import com.example.demo_2.UserAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminUserAccountController extends CommonController {
    @FXML
    private Button searchButton;
    @FXML
    private Button resetButton;

    @FXML
    private Button UACButton;
    @FXML
    private Button GSCButton;
    @FXML
    private TextField usernameSearchTextField;
    @FXML
    // debug
    private TextField nameSearchTextField;


    @FXML
    private TableView<UserAccount> userAccountTableView;
    @FXML
    private TableColumn<UserAccount, Integer> userIDColumn;
    @FXML
    private TableColumn<UserAccount, String> usernameColumn;
    @FXML
    private TableColumn<UserAccount, String> passwordColumn;
    @FXML
    private TableColumn<UserAccount, String> createdDateColumn;
    @FXML
    private TableColumn<UserAccount, String> firstNameColumn;
    @FXML
    private TableColumn<UserAccount, String> lastNameColumn;
    @FXML
    private TableColumn<UserAccount, String> DOBColumn;
    private ObservableList<UserAccount> userAccountList;


    public void backButtonOnAction() throws IOException {
        super.backButtonOnAction("login.fxml");
    }
    public void UACButtonOnAction() throws IOException {
        Stage stage = (Stage) UACButton.getScene().getWindow();
        changeScene(stage, "admin_user-control.fxml");
    }
    public void GSCButtonOnAction() throws IOException {
        Stage stage = (Stage) GSCButton.getScene().getWindow();
        changeScene(stage, "admin_score-control.fxml");
    }

    public void searchButtonOnAction() throws SQLException {
        search();
    }
    public void resetButtonOnAction() {
        usernameSearchTextField.setText("");
        nameSearchTextField.setText("");
        reset();
    }
    public void reset(){
        int size = userAccountList.size();
        for (int i = 0; i < size; i++) {
            userAccountList.remove(0);
        }
    }
    public void search() throws SQLException {
        reset();

        String queryUser = "SELECT * FROM useraccount WHERE username LIKE '%"
                        + usernameSearchTextField.getText()
                        + "%' AND (lastName LIKE '%"
                        + nameSearchTextField.getText() + "%' OR firstName LIKE '%"
                        + nameSearchTextField.getText() + "%') LIMIT 20;";

        PreparedStatement statement = connection.prepareStatement(queryUser);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            /**
             * constructor userAccount
             * get value from column in Database.
             */
            UserAccount userAccount = new UserAccount(
                    Integer.valueOf(resultSet.getInt("userID")),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("createdDate"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("dob")
            );
            userAccountList.add(userAccount);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();

        /**
         * KeyPressed.
         */
        nameSearchTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    search();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        usernameSearchTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    search();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    

    private void initTable(){
        userAccountList = FXCollections.observableArrayList();

        /**
         * column in Table
         * get value from column in userAccountList, from each UserAccount.
         */
        userIDColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,Integer>("userID"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("password"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("lastName"));
        createdDateColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("createdDate"));
        DOBColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("dob"));

        userAccountTableView.setItems(userAccountList);
    }
}
