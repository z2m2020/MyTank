package com.mashibing.tank.fire;//package com.mashibing.tank.fire;

import com.mashibing.tank.*;

public class FourDirectioFireStrategy implements FireStrategy {
    private FourDirectioFireStrategy() {
    }

    ;

    private static class FourDirectioFireStrategyHolder {
        private final static FourDirectioFireStrategy INSTANCE = new FourDirectioFireStrategy();
    }

    public static FourDirectioFireStrategy getInstance() {
        return FourDirectioFireStrategy.FourDirectioFireStrategyHolder.INSTANCE;
    }

//    private FourDirectioFireStrategy(){};


    @Override
    public void fire(Tank t) {
        System.out.println(this.hashCode());

        int bX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        for (Dir dir : Dir.values()) {
            new Bullet(bX, bY, dir, t.getGroup(), t.getTf());


        }


    }
}
