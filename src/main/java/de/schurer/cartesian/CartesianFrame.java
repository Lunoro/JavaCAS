package de.schurer.cartesian;

import de.schurer.Main;
import de.schurer.point.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;

public class CartesianFrame extends JFrame {
    CartesianPanel cartesianPanel;

    double f (double x) {
        return x;
    }

    public CartesianFrame() {
        cartesianPanel = new CartesianPanel(this);
        add(cartesianPanel);

        for (int i = -100; i < 200; i++) {
            double n = 0.25 * i;
            cartesianPanel.drawPoint(new Point(n, f(n)));
        }
    }

    public void showUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cartesian");
        setMinimumSize(new Dimension(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT));
        setVisible(true);
    }
}
