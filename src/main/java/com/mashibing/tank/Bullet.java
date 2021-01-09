package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
//    private static int WIDTH = 15, HEIGHT = 15;
    public static int WIDTH=ResourceMgr.bulletD.getWidth();
    public static int HEIGHT=ResourceMgr.bulletD.getHeight();


    private int x, y;
    private Dir dir;
    private TankFrame tf=null;
    private boolean living=true;

    private Group group=Group.BAD;

    Rectangle rect=new Rectangle();


    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x=this.x;
        rect.y=this.y;
        rect.width=WIDTH;
        rect.height=HEIGHT;

        tf.bullets.add(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLive() {
        return live;
    }

    private boolean live = true;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
//        new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void paint(Graphics g) {
//        if(!live){
////            tf.bullets.iterator().
//            tf.bullets.remove(this);
//        }
        if(!living){
            tf.bullets.remove(this);
        }


        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;

        }

        move();

    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        rect.x=this.x;
        rect.y=this.y;
        //子弹越界判断
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }

    public void collideWith(Tank tank) {
        if(this.group==tank.getGroup()) return;

//        Rectangle rect1=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect1=this.rect;
        Rectangle rect2=tank.rect;
//        Rectangle rect2=new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if(rect1.intersects(rect2)){
            tank.die();
            Explore e = tank.beExplore();
//            tf.explores.remove(e);
            this.die();
        }
    }

    private void die() {
        this.living=false;
    }
}
