package de.schurer.cartesian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CartesianFrame extends JFrame {
    CartesianPanel cartesianPanel;

    public CartesianFrame() {
        cartesianPanel = new CartesianPanel(this);
        add(cartesianPanel);
    }

    public void showUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cartesian");
        setMinimumSize(new Dimension(700, 700));
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized");
            }
        });
    }


}
