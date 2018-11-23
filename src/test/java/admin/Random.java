package admin;

public class Random {

    public static String getRandomProductNumber() {
        return Integer.toString((int)(1 + Math.random()*7));
    }
}
