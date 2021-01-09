package com.mashibing.tank;

import java.awt.*;

public class Explore {
    private static final int SPEED = 10;
    //    private static int WIDTH = 15, HEIGHT = 15;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();


    private int x, y;
    private TankFrame tf = null;
    private boolean living = true;

    private int step=0;

    public boolean isLiving() {
        return living;
    }

    public Explore(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
            if (living==false) return;
            g.drawImage(ResourceMgr.explodes[step++],x,y,null);
            if(step>=ResourceMgr.explodes.length){
                step=0;
                living=false;
            }
    }

}
