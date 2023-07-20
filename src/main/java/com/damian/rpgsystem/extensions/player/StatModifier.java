package com.damian.rpgsystem.extensions.player;

public class StatModifier {
    private double hpModifier = 1;
    private double armorModifier = 1;
    private double damageModifier = 1;
    private double attackSpeedModifier = 1;
    private double speedModifier = 1;

    public double getHpModifier() {
        return hpModifier;
    }

    public void setHpModifier(double hpModifier) {
        this.hpModifier = hpModifier;
    }

    public double getArmorModifier() {
        return armorModifier;
    }

    public void setArmorModifier(double armorModifier) {
        this.armorModifier = armorModifier;
    }

    public double getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(double damageModifier) {
        this.damageModifier = damageModifier;
    }

    public double getAttackSpeedModifier() {
        return attackSpeedModifier;
    }

    public void setAttackSpeedModifier(double attackSpeedModifier) {
        this.attackSpeedModifier = attackSpeedModifier;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public void setSpeedModifier(double speedModifier) {
        this.speedModifier = speedModifier;
    }
}

