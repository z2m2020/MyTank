package com.mashibing.tank.fire;//package com.mashibing.tank.fire;

import com.mashibing.tank.*;

public class TwoBulletsFireStrategy implements FireStrategy {
    private TwoBulletsFireStrategy(){};
    private static class TwoBulletsFireStrategyHolder{
        private final static TwoBulletsFireStrategy INSTANCE=new TwoBulletsFireStrategy();
    }
    public static TwoBulletsFireStrategy getInstance(){
        return TwoBulletsFireStrategy.TwoBulletsFireStrategyHolder.INSTANCE;
    }

//    private FourDirectioFireStrategy(){};


    @Override
    public void fire(Tank t) {
        System.out.println(this.hashCode());

        int bX=t.getX() +Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY=t.getY() +Tank.HEIGHT/2-Bullet.HEIGHT/2;
        if(t.getDir()==Dir.DOWN||t.getDir()==Dir.UP) {
            new Bullet(bX - 5, bY, t.getDir(), t.getGroup(), t.getTf());
            new Bullet(bX + 5, bY, t.getDir(), t.getGroup(), t.getTf());
        }else{
            new Bullet(bX , bY+5, t.getDir(), t.getGroup(), t.getTf());
            new Bullet(bX, bY-5, t.getDir(), t.getGroup(), t.getTf());

        }



    }
}
