package com.mashibing.tank;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractFactory {
   abstract Tank createTank(int x, int y, Dir dir, TankFrame tf) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
//   abstract Bullet createBullet();

}
