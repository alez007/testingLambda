package lrucache.model;

public class Env {
    public static String get(String env) {
        return System.getenv(env);
    }
}
