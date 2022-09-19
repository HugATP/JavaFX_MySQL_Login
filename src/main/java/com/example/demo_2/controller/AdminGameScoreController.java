package com.example.demo_2.controller;

import com.example.demo_2.GameScore;
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

public class AdminGameScoreController extends CommonController {
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
    private TextField nameSearchTextField;


    @FXML
    private TableView<GameScore> gameScoreTableView;
    @FXML
    private TableColumn<GameScore, Integer> scoreIDColumn;
    @FXML
    private TableColumn<GameScore, Integer> userIDColumn;
    @FXML
    private TableColumn<GameScore, Integer> scoreColumn;

    @FXML
    private TableColumn<GameScore, String> usernameColumn;
    @FXML
    private TableColumn<GameScore, String> scoredTimeColumn;
    private ObservableList<GameScore> gameScoreList;


    public void backButtonOnAction() throws IOException {
        super.backButtonOnAction("login.fxml");
    }

    public void searchButtonOnAction() throws SQLException {
        search();
    }
    public void UACButtonOnAction() throws IOException {
        Stage stage = (Stage) UACButton.getScene().getWindow();
        changeScene(stage, "admin_user-control.fxml");
    }
    public void GSCButtonOnAction() throws IOException {
        Stage stage = (Stage) GSCButton.getScene().getWindow();
        changeScene(stage, "admin_score-control.fxml");
    }

    public void resetButtonOnAction() {
        usernameSearchTextField.setText("");
        nameSearchTextField.setText("");
        reset();
    }
    public void reset(){
        int size = gameScoreList.size();
        for (int i = 0; i < size; i++) {
            gameScoreList.remove(0);
        }
    }
    public void search() throws SQLException {
        reset();

        String queryScore = "SELECT gamescore.*, useraccount.username FROM useraccount " +
                "INNER JOIN gamescore " +
                "ON useraccount.userID = gamescore.userID " +
                "WHERE username LIKE '%"
                + usernameSearchTextField.getText()
                + "%' AND (lastName LIKE '%"
                + nameSearchTextField.getText() + "%' OR firstName LIKE '%"
                + nameSearchTextField.getText() + "%') LIMIT 20;";

        PreparedStatement statement = connection.prepareStatement(queryScore);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            /**
             * constructor userAccount
             * get value from column in Database.
             */
            GameScore userAccount = new GameScore(
                    Integer.valueOf(resultSet.getInt("scoreID")),
                    Integer.valueOf(resultSet.getInt("userID")),
                    resultSet.getString("username"),
                    Integer.valueOf(resultSet.getInt("score")),
                    resultSet.getString("scoredTime")
            );
            gameScoreList.add(userAccount);
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
        gameScoreList = FXCollections.observableArrayList();

        /**
         * column in Table
         * get value from column in gameScoreList, from each Score.
         */
        scoreIDColumn.setCellValueFactory(new PropertyValueFactory<GameScore,Integer>("scoreID"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<GameScore,Integer>("userID"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<GameScore,Integer>("score"));
        scoredTimeColumn.setCellValueFactory(new PropertyValueFactory<GameScore,String>("scoredTime"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<GameScore,String>("username"));
        gameScoreTableView.setItems(gameScoreList);
    }
}
