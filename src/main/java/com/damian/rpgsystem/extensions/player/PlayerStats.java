package com.damian.rpgsystem.extensions.player;

public class PlayerStats {
    private double hp;
    private double armor;
    private double dodgeChance;
    private double damage;
    private double attackSpeed;
    private double speed;
    private StatModifier modifiers;
    public PlayerStats(double hp, double armor, double dodgeChance, double damage, double attackSpeed, double speed, StatModifier modifiers) {
        this.hp = hp;
        this.armor = armor;
        this.dodgeChance = dodgeChance;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.speed = speed;
        this.modifiers = modifiers;
    }

    public double getHp() {
        return hp;
    }

    public double getArmor() {
        return armor;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public double getDamage() {
        return damage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public double getSpeed() {
        return speed;
    }

    public StatModifier getModifiers() {
        return modifiers;
    }
}
