package com.mashibing.tank;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
       TankFrame tf=new TankFrame();

       int initTankVolume=Integer.parseInt((String)PropertyMgr.get("tankvolume"));


        for (int i = 0; i < initTankVolume; i++) {
            tf.tanks.add(new Tank(50+ i*80,200,Dir.DOWN,Group.BAD,tf));
        }
//        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
