package com.kinztech.os.game.node.entity.player;

import com.kinztech.os.network.codec.game.encode.SkillEncoder;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public class Skills {

    enum Skill {

        ATTACK(0);

        int skillID;
        Skill(int skillID) { this.skillID = skillID; }

        public int getID() { return skillID; }

    }

    private Player player;

    /**
     * The player's levels
     */
    private int[] levels = new int[22];

    /**
     * The player's experience.
     */
    private int[] experience = new int[22];

    /**
     * Skills
     * @param player
     */
    public Skills(Player player) {
        this.player = player;
        this.setDefault();
    }

    public void updateSkills() {
        for(int i = 0; i < levels.length; i++) {
            player.write(new SkillEncoder(i, levels[i], experience[i]));
        }
    }

    /**
     * Set the default skill levels and experience.
     */
    public void setDefault() {
        for(int i = 0; i < levels.length; i++) {
            if (i == 3)
                levels[i] = 99;
            else
                levels[i] = 1;
        }
        for(int i = 0; i < experience.length; i++)
            experience[i] = 0;
    }

    /**
     * Calculate total level.
     * @return
     */
    public int getTotalLevel() {
        int total = 0;
        for(int i : levels)
            total += i;
        return total;
    }

}
