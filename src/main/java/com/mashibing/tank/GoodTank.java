package com.mashibing.tank;

import java.lang.reflect.InvocationTargetException;

public class GoodTank extends Tank {
   public static final Group group=Group.GOOD;
    private final String strategy="goodFS";
//    private final FireStrategy fireStrategy;

//    public GoodTank(FireStrategy fireStrategy) {
//
//        this.fireStrategy = fireStrategy;
//    }


    public GoodTank(int x, int y, Dir dir, TankFrame tf) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
       super(x,  y, dir, group, tf);
//       String goodFSName=(String)PropertyMgr.get("goodFS");
//       fireStrategy=(FireStrategy) Class.forName(goodFSName).getMethod("getInstance").invoke(this);
       fireStrategy=getFireStrategy(strategy);
   }
}
