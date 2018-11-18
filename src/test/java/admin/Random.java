package admin;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Random {

    public static String getRandomCount() {
        return Integer.toString((int)(1 + Math.random()*100));
    }

    public static String getRandomPrice() {
        return Double.toString(new BigDecimal(Math.random()*100+0.1)
                .setScale(2, RoundingMode.UP).doubleValue());
    }

}
