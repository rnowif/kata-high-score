package com.rnowif.kata;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HighScore {

    private final Path scoreFile;
    private Integer highScore;

    public HighScore(Path scoreFile) {
        this.scoreFile = scoreFile;
        load();
    }

    private void load() {
        if (Files.exists(scoreFile)) {
            try {
                highScore = Integer.valueOf(new String(Files.readAllBytes(scoreFile), StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException("Error while reading score file", e);
            }
        }
    }

    public void updateHighScore(int score) {
        if (score > highScore) {
            highScore = score;
            save();
        }

        System.out.println("HIGHSCORE: " + highScore);
    }

    private void save() {
        try {
            Files.write(scoreFile, highScore.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Error while writing score to file", e);
        }
    }
}
