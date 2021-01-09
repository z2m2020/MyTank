package com.mashibing.tank;

import com.mashibing.tank.fire.FireStrategy;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Tank {
    private static final int SPEED = 5;
    public static int WIDTH=ResourceMgr.goodtankD.getWidth();
    public static int HEIGHT=ResourceMgr.goodtankD.getHeight();

    private Random random=new Random();

    private int x, y;
    private Dir dir = Dir.DOWN;
    private boolean moving =true;
    private TankFrame tf=null;
    private boolean living=true;
    private Group group=Group.BAD;
    Rectangle rect=new Rectangle();
    protected FireStrategy fireStrategy=null;
//        if (this.Group == )FourDirectioFireStrategy.getInstance();
//    private FireStrategy fireStrategy= new FourDirectioFireStrategyE();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x=this.x;
        rect.y=this.y;
        rect.width=WIDTH;
        rect.height=HEIGHT;

//        if (group ==Group.GOOD){
//            String goodFSName=(String)PropertyMgr.get("goodFS");
//                fireStrategy=(FireStrategy) Class.forName(goodFSName).getMethod("getInstance").invoke(this);

//            fireStrategy = FourDirectioFireStrategy.getInstance();
//        }else {
//            String badFSName=(String)PropertyMgr.get("badFS");
//            fireStrategy= (FireStrategy) Class.forName(badFSName).getMethod("getInstance").invoke(this);
//        }

    }

    public TankFrame getTf() {
        return tf;
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



    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public Dir getDir() {
        return dir;
    }
    public void setDir(Dir dir){
        this.dir=dir;

    }



    public Tank(int x, int y, Dir dir,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
    }

    public Tank() {
    }

    public void paint(Graphics g) {
        if(!living) tf.tanks.remove(this);

        switch (dir){
            case LEFT:
                g.drawImage(this.group==Group.BAD?ResourceMgr.badtankL:ResourceMgr.goodtankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group==Group.BAD?ResourceMgr.badtankR:ResourceMgr.goodtankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group==Group.BAD?ResourceMgr.badtankU:ResourceMgr.goodtankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group==Group.BAD?ResourceMgr.badtankD:ResourceMgr.goodtankD,x,y,null);
                break;

        }

        move();
//        //边界检测================================== v1
//        if(this.y>tf.getHeight()-Tank.HEIGHT/2){
//            this.setDir(Dir.UP);
//        }
//        if(this.y<Tank.HEIGHT/2){
//            this.setDir(Dir.DOWN);
//        }
//        if(this.x<Tank.WIDTH/2){
//            this.setDir(Dir.RIGHT);
//        }
//        if(this.x>tf.getWidth()-Tank.WIDTH/2){
//            this.setDir(Dir.LEFT);
//        }
        //边界检测================================== v2
        if(this.x<2) x=2;
        if(this.y<28) y=28;
        if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH-2) x=TankFrame.GAME_WIDTH-Tank.WIDTH-2;
        
        if(this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT-2) y =TankFrame.GAME_HEIGHT-Tank.HEIGHT-2;
        
        if(this.group==Group.BAD &&random.nextInt(100)>95) this.fire();
        if(this.group==Group.BAD && random.nextInt(100)>95)
            randomDir();
    }

    private void randomDir() {
        this.dir=Dir.values()[random.nextInt(4)];
    }

    private void move() {
        if(!moving) return;
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
    }
//怎样把这颗子弹传到TankFrame中
    public void fire() {
        //装子弹
//        tf.bullets.add(new Bullet(this.x,this.y,this.dir, tf));
//       tf.b=new Bullet(this.x,this.y,this.dir);
//        int bX=this.x +Tank.WIDTH/2-Bullet.WIDTH/2;
//        int bY=this.y +Tank.HEIGHT/2-Bullet.HEIGHT/2;
//
//        tf.bullets.add(new Bullet(bX,bY,this.dir,this.group,this.tf));
        fireStrategy.fire(this);
//        fireStrategy.fire(this);

    }
    public void fireFour() {
        //装子弹
//        tf.bullets.add(new Bullet(this.x,this.y,this.dir, tf));
//       tf.b=new Bullet(this.x,this.y,this.dir);
        int bX=this.x +Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY=this.y +Tank.HEIGHT/2-Bullet.HEIGHT/2;

        tf.bullets.add(new Bullet(bX,bY,Dir.LEFT,this.group,this.tf));
        tf.bullets.add(new Bullet(bX,bY,Dir.RIGHT,this.group,this.tf));
        tf.bullets.add(new Bullet(bX,bY,Dir.UP,this.group,this.tf));
        tf.bullets.add(new Bullet(bX,bY,Dir.DOWN,this.group,this.tf));

    }
//    public void fireTwo() {
//        //装子弹
////        tf.bullets.add(new Bullet(this.x,this.y,this.dir, tf));
////       tf.b=new Bullet(this.x,this.y,this.dir);
////        int bX=this.x +Tank.WIDTH/2-Bullet.WIDTH/2;
////        int bY=this.y +Tank.HEIGHT/2-Bullet.HEIGHT/2;
////        if(this.dir==Dir.DOWN||this.dir==Dir.UP) {
////            tf.bullets.add(new Bullet(bX - 5, bY, this.dir, this.group, this.tf));
////            tf.bullets.add(new Bullet(bX + 5, bY, this.dir, this.group, this.tf));
////        }else{
////            tf.bullets.add(new Bullet(bX , bY-5, this.dir, this.group, this.tf));
////            tf.bullets.add(new Bullet(bX , bY+5, this.dir, this.group, this.tf));
////        }
//
////        tf.bullets.add(new Bullet(bX,bY,Dir.UP,this.group,this.tf));
////        tf.bullets.add(new Bullet(bX,bY,Dir.DOWN,this.group,this.tf));
//
//    }

    public void die(){
        this.living=false;
    }
    public Explore beExplore(){
        Explore e=new Explore(this.x,this.y,this.tf);
        this.tf.explores.add(e);
        return e;
    }
    public FireStrategy getFireStrategy(String key) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String goodFSName=(String)PropertyMgr.get(key);
        return fireStrategy=(FireStrategy) Class.forName(goodFSName).getMethod("getInstance").invoke(this);
    }
}