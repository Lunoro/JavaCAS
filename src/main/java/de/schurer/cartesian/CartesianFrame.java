package de.schurer.cartesian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CartesianFrame extends JFrame {
    CartesianPanel cartesianPanel;

    double f (double x) {
        return x +1;
    }

    public CartesianFrame() {
        cartesianPanel = new CartesianPanel(this);
        add(cartesianPanel);

        for (int i = 0; i < 19; i++) {
            cartesianPanel.drawPoint(new Point(i, (int) f(i)));
        }
    }

    public void showUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cartesian");
        setMinimumSize(new Dimension(700, 700));
        setVisible(true);
    }


}
