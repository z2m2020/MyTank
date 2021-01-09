package com.mashibing.tank;

import java.lang.reflect.InvocationTargetException;

public class GoodTankFactory extends AbstractFactory {
    @Override
    Tank createTank(int x, int y, Dir dir, TankFrame tf) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return new GoodTank(x,  y, dir, tf);
    }

//    @Override
//    Bullet createBullet() {
//        return null;
//    }
}
