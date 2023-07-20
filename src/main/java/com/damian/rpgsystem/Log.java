package com.damian.rpgsystem;

public class Log {
    public static void info(String content) {
        Main.getInstance().getLogger().info(content);
    }

    public static void info(String prefix, String content) {
        Main.getInstance().getLogger().info("[" + prefix + "] " + content);
    }

    public static void throwError(String className, String methodName, Throwable thrown) {
        Main.getInstance().getLogger().throwing(className, methodName, thrown);
    }
}
