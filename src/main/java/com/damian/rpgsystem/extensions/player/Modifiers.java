package com.damian.rpgsystem.extensions.player;

public enum Modifiers {
    DEFAULT(new StatModifier());
    private StatModifier modifier;

    Modifiers(StatModifier modifier) {
        this.modifier = modifier;
    }

    public StatModifier getModifier() {
        return modifier;
    }
}
