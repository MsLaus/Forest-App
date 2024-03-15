package com.mslaus.forestapp.enums;

public enum Achievements {

    BEGINNER("Focus for 5 hours.", 100),
    MIDDLE("Focus for 10 hours.", 150),
    ADVANCED("Focus for 25 hours.", 300),
    EXPERT("Focus for 40 hours.", 500),
    /*FIRST_DAY("Focus for more than 2 hours in a day.", 200),
    PLANTER("Focus for 3 days in a row.", 300),
    ADVANCED_PLANTER("Focus for 7 days in a row.", 500),
    EXPERT_PLANTER("Focus for 14 days in a row.", 700),
    LEGENDARY_PLANTER("Focus for 30 days in a row.", 1000);*/
    ;


    final String description;
    final int reward;

    Achievements(String description, int reward) {
        this.description = description;
        this.reward = reward;
    }

    public String getDescription() {
        return description;
    }

    public int getReward() {
        return reward;
    }
}
