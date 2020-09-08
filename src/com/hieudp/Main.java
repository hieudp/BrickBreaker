package com.hieudp;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // create a new frame to store text field and button
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay(new BorderLayout());
        obj.setTitle("Brick Breaker");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setBounds(10, 10, 700, 600);
        obj.add(gameplay);
    }
}
