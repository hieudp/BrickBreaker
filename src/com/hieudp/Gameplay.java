package com.hieudp;

import org.w3c.dom.css.Rect;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;

    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballPosX = 120;
    private int ballPosY = 350;
    private double ballXdir = -1.5;
    private int ballYdir = -3;

    private MapGenerator map;

    public Gameplay(LayoutManager layout) {
        super(layout);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        map = new MapGenerator(3,7);
        this.timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(1,1, 692, 592);
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691, 0, 3, 592);
        g.setColor(Color.BLUE);
        g.fillRect(playerX, 550, 100, 8);
        g.setColor(Color.GREEN);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        map.draw((Graphics2D) g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);
        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Wou Won, Score was: " + score, 150, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        else if(ballPosY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score was: " + score, 150, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        g.dispose();
    }

    private void moveRight(){
        play = true;
        playerX += 40;
    }

    private void moveLeft(){
        play = true;
        playerX -= 40;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play){
            if(new Rectangle(ballPosX, ballPosY, 20, 30).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdir = -ballYdir;
            }

            for(int i = 0; i < map.getMap().length; i++){
                for(int j = 0; j < map.getMap()[0].length; j++){
                    if(map.getMap()[i][j] > 0){
                        int brickX = j*map.getBrickWidth() + 80;
                        int brickY = i*map.getBrickHeight() + 50;
                        int brickWidth = map.getBrickWidth();
                        int brickHeight = map.getBrickHeight();

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i ,j);
                            totalBricks--;
                            score += 5;

                            if(ballPosX + 19 <= brickRect.getX() || ballPosX + 1 >= brickRect.getX() + brickRect.getWidth()){
                                ballXdir = -ballXdir;
                            }else {
                                ballYdir = -ballYdir;
                            }
                        }
                    }
                }
            }

            ballPosX += ballXdir;
            ballPosY += ballYdir;
            if(ballPosX < 0 || ballPosX > 670){
                ballXdir = -ballXdir;
            }
            else if (ballPosY < 0){
                ballYdir = -ballYdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }
            else {
                moveRight();
            }
        } if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <= 10){
                playerX = 10 ;
            }
            else {
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1.5;
                ballYdir = -3;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3,7);

                repaint();
            }
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
