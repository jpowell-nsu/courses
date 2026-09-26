package driver;

import java.util.ArrayList;

/**
 * Holds the data for one crew member at Starfall Outpost. This class is
 * shared by both example days this week: on the JSON day it gets converted
 * to and from a JSONObject, and on the byte file day it gets written to and
 * read from a binary save file. Nothing in this class changes between the
 * two days; only how a CrewMember gets in and out of a file changes.
 *
 * @author  Dr. Powell
 * @version 1.0
 */
public class CrewMember {
    private String name;
    private String role;
    private int health;        // 0 to 100
    private double morale;     // 0.0 to 100.0
    private boolean onDuty;
    private ArrayList<String> skills;

    /**
     * Creates a crew member with an empty skill list.
     *
     * @param name   the crew member's name
     * @param role   the crew member's job at the outpost
     * @param health current health, 0 to 100
     * @param morale current morale, 0.0 to 100.0
     * @param onDuty whether the crew member is currently on duty
     */
    public CrewMember(String name, String role, int health, double morale, boolean onDuty) {
        this.name = name;
        this.role = role;
        this.health = health;
        this.morale = morale;
        this.onDuty = onDuty;
        this.skills = new ArrayList<String>();
    }

    public String getName() { return name; }
    public String getRole() { return role; }
    public int getHealth() { return health; }
    public double getMorale() { return morale; }
    public boolean isOnDuty() { return onDuty; }
    public ArrayList<String> getSkills() { return skills; }

    public void setHealth(int health) { this.health = health; }
    public void setMorale(double morale) { this.morale = morale; }
    public void setOnDuty(boolean onDuty) { this.onDuty = onDuty; }

    /**
     * Adds one skill to this crew member's skill list.
     *
     * @param skill the skill to add, for example "Engineering"
     */
    public void addSkill(String skill) {
        skills.add(skill);
    }

    @Override
    public String toString() {
        return name + " (" + role + ") - health " + health + ", morale "
                + morale + ", " + (onDuty ? "on duty" : "off duty") + ", skills " + skills;
    }
}
