package com.mashibing.tank;


import com.mashibing.tank.fire.FireStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {

    //
//    ResourceMgr resourceMgr=new ResourceMgr();
    AbstractFactory tankFactory=new GoodTankFactory();
//    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
//        Tank badTank =new Tank(300,300,Dir.RIGHT,this);
    Tank myTank=tankFactory.createTank(200, 400, Dir.DOWN,this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explore> explores = new ArrayList<>();
    List<FireStrategy> fs=new ArrayList<>();

    //    Explore e=new Explore(100,100,this);
    Bullet b = new Bullet(300, 300, Dir.DOWN, this);
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public List<FireStrategy> getFs() {
        return fs;
    }

    public void setFs(List<FireStrategy> fs) {
        this.fs = fs;
    }

    public TankFrame() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

        setSize(GAME_WIDTH, GAME_HEIGHT);//设置窗口大小
        setResizable(false);
        setTitle("TANK WAR");
        setVisible(true);
//加个小叉把window关闭
        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {//监听器
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_WIDTH);
        }

        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);


    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("Bullet volume: " + bullets.size(), 10, 60);
        g.drawString("Tank volume: " + tanks.size(), 10, 80);
        g.drawString("Explore volume: " + explores.size(), 10, 100);

        //===========================fs==============================
        g.drawString("Fs volume: " + fs.size(), 10, 120);

        g.setColor(c);
        myTank.paint(g);
//        badTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
//
//            for (Bullet b : bullets) {
//                b.paint(g);
//            }



        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        for (int i = 0; i < explores.size(); i++) {
            if (explores.get(i).isLiving()) {
                explores.get(i).paint(g);
            } else explores.remove(i);
        }
//        e.paint(g);

        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ) {
            Bullet b = it.next();
            if (!b.isLive()) it.remove();
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;


        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:

                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    ;
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    ;
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) myTank.setMoving(false);
            myTank.setMoving(true);
            if (bL) myTank.setDir(Dir.LEFT);
            if (bR) myTank.setDir(Dir.RIGHT);
            if (bU) myTank.setDir(Dir.UP);
            if (bD) myTank.setDir(Dir.DOWN);

        }

    }
}

