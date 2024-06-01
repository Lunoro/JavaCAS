package de.schurer.cartesian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class CartesianPanel extends JPanel {

    private final JFrame frame;

    public CartesianPanel(JFrame frame) {
        this.frame = frame;
    }

    private List<Point> points = new ArrayList<>();

    public void drawPoint(Point point) {
        points.add(point);
        repaint();
    }

    // x-axis
    public static final int X_FIRST_CORD = 50;
    public static final int X_SECOND_CORD = 600;
    public static final int X_CORD_Y_CORD = 600;

    // y-axis
    public static final int Y_FIRST_CORD = 50;
    public static final int Y_SECOND_CORD = 600;
    public static final int Y_CORD_X_CORD = 50;

    // size of start coordinate lenght
    public static final int ORIGIN_COORDINATE_LENGHT = 6;

    // distance of coordinate strings from axis
    public static final int AXIS_STRING_DISTANCE = 1;

    public static final int FIRST_LENGHT = 10;
    public static final int SECOND_LENGHT = 5;

    private void drawPointOnPanel(Point point, Graphics g) {
        final int pointDiameter = 5;
        final int x = X_FIRST_CORD + point.x * ((X_SECOND_CORD - X_FIRST_CORD) / 10);
        final int y = X_CORD_Y_CORD - point.y * ((X_SECOND_CORD - X_FIRST_CORD) / 10);
        g.fillOval(x, y, pointDiameter, pointDiameter);

    }

    //Draws the Cartesian Plane
    //TODO: refactor this mess
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //x-axis
        g2.drawLine(X_FIRST_CORD, X_CORD_Y_CORD, X_SECOND_CORD, X_CORD_Y_CORD);
        //y-axis
        g2.drawLine(Y_CORD_X_CORD, Y_FIRST_CORD, Y_CORD_X_CORD, Y_SECOND_CORD);

        int xCordNumbers = 10;
        int yCordNumbers = 10;
        int xLength = (X_SECOND_CORD - X_FIRST_CORD) / xCordNumbers;
        int yLength = (Y_SECOND_CORD - Y_FIRST_CORD) / yCordNumbers;

        for (int i = 1; i < xCordNumbers; i++) {
            g2.drawLine(X_FIRST_CORD + (i * xLength), X_CORD_Y_CORD - SECOND_LENGHT, X_FIRST_CORD + (i * xLength),
                    X_CORD_Y_CORD + SECOND_LENGHT);
            g2.drawString(Integer.toString(i), X_FIRST_CORD + (i * xLength), (X_CORD_Y_CORD - SECOND_LENGHT) + 20);
        }

        for (int i = 1; i < yCordNumbers; i++) {
            g2.drawLine(Y_CORD_X_CORD - SECOND_LENGHT, Y_FIRST_CORD + (i * xLength), Y_CORD_X_CORD + SECOND_LENGHT, Y_FIRST_CORD + (i * xLength));
            g2.drawString(Integer.toString(10 - i), (Y_CORD_X_CORD - SECOND_LENGHT) - 10, Y_FIRST_CORD + (i * xLength));
        }

        points.forEach(point -> drawPointOnPanel(point, g2));

    }
}
