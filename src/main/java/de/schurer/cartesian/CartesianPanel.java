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
    private int xStartingPoint = 50;
    private int xDestinationPoint = 600;
    private int xHeight = 600;

    // y-axis
    private int yStartingPoint = 50;
    private int yDestinationPoint = 600;
    private int yWidth = 50;

    private final int AXIS_NUMERATION_DISTANCE = 5;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        xDestinationPoint = (int) frame.getSize().getWidth() - 100;
        yDestinationPoint = (int) frame.getSize().getHeight() - 100;
        xHeight = (int) frame.getSize().getHeight() - 100;

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int numberAmountXAxis = (int) (frame.getSize().getWidth() / 50);
        int numberAmountYAxis = (int) (frame.getSize().getHeight() / 50);
        ;
        int lengthXAxis = (xDestinationPoint - xStartingPoint) / numberAmountXAxis;
        int lengthYAxis = (yDestinationPoint - yStartingPoint) / numberAmountYAxis;

        //draws the x-axis based on the start and destination which are updated by the resize event and numerates it
        g2.drawLine(xStartingPoint, xHeight, xDestinationPoint, xHeight);

        for (int i = 1; i < numberAmountXAxis; i++) {
            g2.drawLine(xStartingPoint + (i * lengthXAxis), xHeight - AXIS_NUMERATION_DISTANCE, xStartingPoint + (i * lengthXAxis),
                    xHeight + AXIS_NUMERATION_DISTANCE);
            g2.drawString(Integer.toString(i), xStartingPoint + (i * lengthXAxis), (xHeight - AXIS_NUMERATION_DISTANCE) + 20);
        }

        //draws the y-axis based on the start and destination which are updated by the resize event and numerates it
        g2.drawLine(yWidth, yStartingPoint, yWidth, yDestinationPoint);

        for (int i = 1; i < numberAmountYAxis; i++) {
            //draws y-axis from top to bottom to improve tracing of points
            g2.drawLine(yWidth - AXIS_NUMERATION_DISTANCE, yDestinationPoint - (i * lengthYAxis), yWidth + AXIS_NUMERATION_DISTANCE, yDestinationPoint - (i * lengthYAxis));
            g2.drawString(Integer.toString(numberAmountYAxis - i), (yWidth - AXIS_NUMERATION_DISTANCE) - 10, yStartingPoint + (i * lengthYAxis));
        }

        updatePoints(numberAmountXAxis, numberAmountYAxis, g2);
    }

    private void updatePoints(int numberAmountXAxis, int numberAmountYAxis, Graphics2D g2) {
        for (int i = 0; i < numberAmountXAxis; i++) {
            if (points.size() > i) {
                Point point = points.get(i);
                if (point.y < numberAmountYAxis) {
                    drawPointOnPanel(point, g2, numberAmountXAxis, numberAmountYAxis);
                }
            }
        }
    }

    private void drawPointOnPanel(Point point, Graphics g, int xScale, int yScale) {
        final int pointDiameter = 5;
        final int x = xStartingPoint + point.x * ((xDestinationPoint - xStartingPoint) / xScale);
        final int y = xHeight - point.y * ((yDestinationPoint - yStartingPoint) / yScale);
        g.fillOval(x, y, pointDiameter, pointDiameter);
    }

    //don't know where to store it
    private void resizeListener() {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paintComponent(frame.getGraphics());
            }
        });
    }
}
