package com.mashibing.tank.fire;//package com.mashibing.tank.fire;

import com.mashibing.tank.*;

public class FourDirectioFireStrategyE extends Fire implements FireStrategy {

    public FourDirectioFireStrategyE(){
        super();
    }

    @Override
    public void fire(Tank t) {
        System.out.println(this.hashCode());
        int bX=t.getX()+Tank.WIDTH/2- Bullet.WIDTH/2;
        int bY=t.getY()+Tank.HEIGHT/2- Bullet.HEIGHT/2;
        for (Dir dir:Dir.values()
             ) {
            if (t.getGroup()==Group.GOOD){
                new Bullet(bX,bY,dir,t.getGroup(),t.getTf());
                if(t.getGroup()== Group.GOOD) new Thread(()->new Audio("audio/tank_move.wav"));
            } else {
                new Bullet(bX,bY,t.getDir(),t.getGroup(),t.getTf());
            }
        }



    }
}
