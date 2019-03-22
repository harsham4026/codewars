package com.codewars;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * https://www.codewars.com/kata/the-greatest-warrior
 */
public class TheGreatestWarrior {
    private int experience = 100;
    private int level = 1;

    private List<String> ranks = Arrays.asList("Pushover", "Novice", "Fighter", "Warrior", "Veteran",
            "Sage", "Elite", "Conqueror", "Champion", "Master", "Greatest");
    private List<String> achievements = new ArrayList<>();

    public int level() {
        return level;
    }

    public int experience() {
        return experience;
    }

    public String rank() {
        return ranks.get(getRankIndexForLevel(level));
    }

    private int getRankIndexForLevel(int level) {
        return level / 10;
    }

    public List<String> achievements() {
        return achievements;
    }

    public String training(String description, int points, int minLevel) {
        if (level >= minLevel) {
            addToExperience(points);
            achievements.add(description);
            return description;
        }
        return "Not strong enough";
    }

    private void addToExperience(int value) {
        experience = Math.min(10000, experience + value);
        level = experience / 100;
    }

    public String battle(int opponentLevel) {
        if (opponentLevel < 1 || opponentLevel > 100) {
            return "Invalid level";
        }
        if (level == opponentLevel) {
            addToExperience(10);
            return "A good fight";
        } else if (opponentLevel == level - 1) {
            addToExperience(5);
            return "A good fight";
        } else if (opponentLevel == level - 2) {
            return "Easy fight";
        } else if (opponentLevel > level) {
            int diff = opponentLevel - level;
            if (getRankIndexForLevel(level) < getRankIndexForLevel(opponentLevel)
                    && diff >= 5) {
                return "You've been defeated";
            } else {
                addToExperience(20 * diff * diff);
                return "An intense fight";
            }
        }
        return "Easy fight";
    }
}