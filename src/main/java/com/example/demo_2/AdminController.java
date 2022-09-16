package com.example.demo_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController extends CommonController implements Initializable {
    @FXML
    private Button searchButton;
    @FXML
    private Button resetButton;
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
    private TableColumn<UserAccount, String> firstNameColumn;
    @FXML
    private TableColumn<UserAccount, String> lastNameColumn;
    private ObservableList<UserAccount> userAccountList;


    public void backButtonOnAction(ActionEvent e) throws IOException {
        super.backButtonOnAction(e, "login.fxml");
    }

    public void searchButtonOnAction(ActionEvent e) throws SQLException {
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
        String queryUser = "SELECT * FROM useraccounts WHERE Username LIKE '%"
                        + usernameSearchTextField.getText()
                        + "%' AND (LastName LIKE '%"
                        + nameSearchTextField.getText() + "%' OR FirstName LIKE '%"
                        + nameSearchTextField.getText() + "%') LIMIT 15;";
        PreparedStatement statement = connection.prepareStatement(queryUser);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            UserAccount userAccount = new UserAccount(
                    Integer.valueOf(resultSet.getInt("UserID")),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Firstname"),
                    resultSet.getString("Lastname")
            );
            userAccountList.add(userAccount);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userAccountList = FXCollections.observableArrayList();

        userIDColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,Integer>("userID"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("password"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("lastName"));

        userAccountTableView.setItems(userAccountList);
    }
}
