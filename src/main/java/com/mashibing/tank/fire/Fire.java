package com.mashibing.tank.fire;

public class Fire {
//    private Fire(){};

    public Fire() {
        this.getInstance();

    }

    private static class FireHolder{
        private final static Fire INSTANCE=new Fire();
    }
    public  Fire getInstance(){
        return Fire.FireHolder.INSTANCE;
    }
}
