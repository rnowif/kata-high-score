package com.rnowif.kata.feature;

import com.rnowif.kata.HighScore;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HighScoreFeatureTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void should_update_highscore_to_file_and_print_it_when_new_highscore() throws IOException {
        Path scoreFile = temporaryFolder.newFile().toPath();
        Files.write(scoreFile, "14".getBytes(StandardCharsets.UTF_8));
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        HighScore highScore = new HighScore(scoreFile);
        highScore.updateHighScore(16);

        verify(out).println("HIGHSCORE: 16");
        assertThat(Integer.valueOf(new String(Files.readAllBytes(scoreFile))), is(16));
    }

    @Test
    public void should_not_update_highscore_to_file_but_should_print_it_when_score_lower_than_highscore() throws IOException {
        Path scoreFile = temporaryFolder.newFile().toPath();
        Files.write(scoreFile, "14".getBytes(StandardCharsets.UTF_8));
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        HighScore highScore = new HighScore(scoreFile);
        highScore.updateHighScore(12);

        verify(out).println("HIGHSCORE: 14");
        assertThat(Integer.valueOf(new String(Files.readAllBytes(scoreFile))), is(14));
    }

    @Test
    @Ignore("This test is failing with NumberFormatException")
    public void should_update_highscore_to_file_and_print_it_when_high_score_file_is_empty() throws IOException {
        Path scoreFile = temporaryFolder.newFile().toPath();
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        HighScore highScore = new HighScore(scoreFile);
        highScore.updateHighScore(12);

        verify(out).println("HIGHSCORE: 12");
        assertThat(Integer.valueOf(new String(Files.readAllBytes(scoreFile))), is(12));
    }

    @Test
    @Ignore("This test is failing with NPE")
    public void should_update_highscore_to_file_and_print_it_when_high_score_file_not_exists() throws IOException {
        Path scoreFile = temporaryFolder.newFile().toPath();
        Files.deleteIfExists(scoreFile);

        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        HighScore highScore = new HighScore(scoreFile);
        highScore.updateHighScore(12);

        verify(out).println("HIGHSCORE: 12");
        assertThat(Integer.valueOf(new String(Files.readAllBytes(scoreFile))), is(12));
    }
}
