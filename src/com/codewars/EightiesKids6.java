package com.codewars;
import java.util.Map;

/**
 * https://www.codewars.com/kata/80-s-kids-number-6-rock-em-sock-em-robots
 */
public class EightiesKids6 {
    public static String fight(Robot robot1, Robot robot2, Map<String, Integer> tactics) {

        int robot1Health = robot1.getHealth();
        int robot2Health = robot2.getHealth();
        boolean robot1Chance = robot1.getSpeed() >= robot2.getSpeed();
        int robot1TacticsIndex = 0;
        int robot2TacticsIndex = 0;

        String tactic;
        while (robot1Health > 0 && robot2Health > 0
                && (robot1TacticsIndex < robot1.getTactics().length     //Atleast one has tactics left
                || robot2TacticsIndex < robot2.getTactics().length)) {

            if (robot1Chance && robot1TacticsIndex < robot1.getTactics().length) {
                tactic = robot1.getTactics()[robot1TacticsIndex++];
                robot2Health -= tactics.get(tactic);
            } else if (robot2TacticsIndex < robot2.getTactics().length) {
                tactic = robot2.getTactics()[robot2TacticsIndex++];
                robot1Health -= tactics.get(tactic);
            }
            robot1Chance = !robot1Chance;
        }
        if (robot1Health > robot2Health) {
            return robot1.getName() + " has won the fight.";
        } else if (robot1Health < robot2Health) {
            return robot2.getName() + " has won the fight.";
        } else {
            return "The fight was a draw.";
        }
    }
    //Creating Robot class for compilation to succeed
    private static class Robot {
        private String name;
        private int health;
        private int speed;
        private String[] tactics;
        public int getHealth() {
            return health;
        }

        public int getSpeed() {
            return speed;
        }

        public String[] getTactics() {
            return tactics;
        }

        public String getName() {
            return name;
        }
    }
}
