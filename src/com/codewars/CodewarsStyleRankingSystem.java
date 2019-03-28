package com.codewars;

/**
 * https://www.codewars.com/kata/codewars-style-ranking-system/
 */
public class CodewarsStyleRankingSystem {

    // TODO: create the User class/object
// it must support rank, progress, and the incProgress(int rank) method

    class User {
        public int rank = -8;
        public int progress = 0;

        public int rank() {
            return rank;
        }

        public int progress() {
            return progress;
        }

        public void incProgress(int activityRank) {
            if (activityRank > 8 || activityRank < -8 || activityRank == 0) {
                throw new IllegalArgumentException();
            }
            if (rank == 8) {
                return;
            }
            if (activityRank == rank) {
                progress += 3;
            } else if ((rank != 1 && activityRank == rank - 1) || (rank == 1 && activityRank == -1)) {
                progress += 1;
            } else if (activityRank > rank) {
                int diffInRank;
                diffInRank = activityRank - rank;

                if ((activityRank > 0 && rank < 0)) {
                    diffInRank--; //no 0 rank
                }
                progress += (10 * diffInRank * diffInRank);
            }

            //Updating rank if applies
            if (progress > 100 && rank != 8) {
                int quot = progress / 100;
                progress = progress % 100;

                for (int i = 0; i < quot; i++) {
                    this.rank = rank + 1;
                    if (rank == 0) {
                        rank = rank + 1;
                    }
                }
                if (rank == 8) {
                    progress = 0;
                }
            }
        }
    }
}
