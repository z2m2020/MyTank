package com.mashibing.tank;

import java.lang.reflect.InvocationTargetException;

public class BadTank extends Tank {
    public static  final Group group=Group.BAD;
    private final String strategy="badFS";
//    public FireStrategy fireStrategy=null;
    public BadTank(int x, int y, Dir dir, TankFrame tf) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super(x,  y, dir, group, tf);
//        String badFSName=(String)PropertyMgr.get("badFS");
//        fireStrategy= (FireStrategy) Class.forName(badFSName).getMethod("getInstance").invoke(this);
        fireStrategy=getFireStrategy(strategy);
    }

}
