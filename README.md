# HighScore Kata

## Description

This Kata is a Java adaptation of [a kata from the TDD Fellow](http://www.tddfellow.com/blog/2016/09/30/highscore-kata/?q=3) written in javascript.
The game has only one high score and when current game’s score exceeds that number, it gets updated. Example acceptance test would read like this:

```
Given high score is 174
When player scores 191
Then high score is 191
```

Current implementation stores high score in a file.

## Tasks

- Write tests for this class.
- Fix the bug: when a game is launched (without any previous highscore), it throws an NPE.
- Extract storing mechanisms, so that class can be re-used with different storage mechanisms (for example local database, or external REST API).
