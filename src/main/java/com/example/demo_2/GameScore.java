package com.example.demo_2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameScore {
    private int scoreID;
    private int userID;
    private String username;
    private int score;
    private String scoredTime;

}
