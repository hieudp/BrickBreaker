package com.hieudp;

import java.awt.*;

public class MapGenerator {

    private int map [][];
    private int brickWidth;
    private int brickHeight;

    public MapGenerator(int row, int col){
        this.map = new int [row][col];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = 1;
            }
        }
        brickHeight = 150/row;
        brickWidth = 540/col;
    }

    public int[][] getMap() {
        return map;
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public int getBrickHeight() {
        return brickHeight;
    }

    public void draw(Graphics2D g){
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if(map[i][j] > 0){
                    g.setColor(Color.BLACK);
                    g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.WHITE);
                    g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;

    }
}
