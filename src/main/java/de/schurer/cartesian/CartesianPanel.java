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

    /**
     * STANDARD WINDOW SIZE: 700 x 700
     *
     * TODO: brainstorm a methode to calculate the point coordinates into the window coordinates
     */

    // x-axis
    private int xStartingPoint = 50;
    private int xDestinationPoint = 600;
    private int xHeight = 600;

    // y-axis
    private int yStartingPoint = 50;
    private int yDestinationPoint = 600;
    private int yWidth = 50;

    //mid coordinates
    private int midX = 350;
    private int midY = 350;

    private final int AXIS_NUMERATION_DISTANCE = 5;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        xDestinationPoint = (int) frame.getSize().getWidth() - 50;
        yDestinationPoint = (int) frame.getSize().getHeight() - 50;
        xHeight = (int) frame.getSize().getHeight() / 2;
        yWidth = (int) frame.getSize().getWidth() / 2;

        midX = frame.getWidth() / 2;
        midY = frame.getHeight() / 2;

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int numberAmountXAxis = (int) (frame.getSize().getWidth() / 50);
        int numberAmountYAxis = (int) (frame.getSize().getHeight() / 25);

        int lengthXAxis = (xDestinationPoint - xStartingPoint) / numberAmountXAxis;
        int lengthYAxis = (yDestinationPoint - yStartingPoint) / numberAmountYAxis;

        drawXAxis(g2, numberAmountXAxis, lengthXAxis);
        drawYAxis(g2, numberAmountYAxis, lengthYAxis);

        updatePoints(numberAmountXAxis, numberAmountYAxis, g2);
    }

    //TODO: refactor duplicated code
    private void drawXAxis(Graphics2D g2, int numberAmountXAxis, int lengthXAxis) {
        //draws the x-axis based on the start and destination which are updated by the resize event and numerates it
        g2.drawLine(xStartingPoint, xHeight, xDestinationPoint, xHeight);

        for (int i = 0; i < numberAmountXAxis; i++) {
            g2.drawLine(midX + (i * lengthXAxis), xHeight - AXIS_NUMERATION_DISTANCE, midX + (i * lengthXAxis),
                    xHeight + AXIS_NUMERATION_DISTANCE);
            g2.drawString(Integer.toString(i), midX + (i * lengthXAxis), (xHeight - AXIS_NUMERATION_DISTANCE) + 20);
        }

        for (int i = 0; i > -numberAmountXAxis; i--) {
            g2.drawLine(midX + (i * lengthXAxis), xHeight - AXIS_NUMERATION_DISTANCE, midX + (i * lengthXAxis),
                    xHeight + AXIS_NUMERATION_DISTANCE);
            g2.drawString(Integer.toString(i), midX + (i * lengthXAxis), (xHeight - AXIS_NUMERATION_DISTANCE) + 20);
        }
    }

    //TODO: refactor duplicated code
    private void drawYAxis(Graphics2D g2, int numberAmountYAxis, int lengthYAxis) {
        //draws the y-axis based on the start and destination which are updated by the resize event and numerates it
        g2.drawLine(yWidth, yStartingPoint, yWidth, yDestinationPoint);

        for (int i = 0; i < numberAmountYAxis; i++) {
            //draws y-axis from top to bottom to improve tracing of points
            g2.drawLine(yWidth - AXIS_NUMERATION_DISTANCE, midY - (i * lengthYAxis), yWidth + AXIS_NUMERATION_DISTANCE, midY - (i * lengthYAxis));
            g2.drawString(Integer.toString(i), (yWidth - AXIS_NUMERATION_DISTANCE) - 15, midY - (i * lengthYAxis));
        }

        for (int i = 0; i > -numberAmountYAxis; i--) {
            //draws y-axis from top to bottom to improve tracing of points
            g2.drawLine(yWidth - AXIS_NUMERATION_DISTANCE, midY - (i * lengthYAxis), yWidth + AXIS_NUMERATION_DISTANCE, midY - (i * lengthYAxis));
            g2.drawString(Integer.toString(i), (yWidth - AXIS_NUMERATION_DISTANCE) - 15, midY - (i * lengthYAxis));
        }
    }

    public void drawPoint(Point point) {
        points.add(point);
        repaint();
    }

    //TODO: refactor duplicated code
    private void drawPointOnPanel(Point point, Graphics g, int xScale, int yScale) {
        final int pointDiameter = 2;
        final int x = midX + point.x * ((xDestinationPoint - xStartingPoint) / xScale);
        final int y = xHeight - point.y * ((yDestinationPoint - yStartingPoint) / yScale);
        g.fillOval(x, y, pointDiameter, pointDiameter);
    }

    private void updatePoints(int numberAmountXAxis, int numberAmountYAxis, Graphics2D g2) {
        for (int i = 0; i < numberAmountXAxis; i++) {
            if (points.size() > i) {
                Point point = points.get(i);
                if (point.y < numberAmountYAxis) {
                    drawPointOnPanel(point, g2, numberAmountXAxis, numberAmountYAxis);
                    connectPoints(g2, numberAmountXAxis, numberAmountYAxis);
                }
            }
        }
    }

    //TODO: refactor duplicated code
    private void connectPoints(Graphics2D g2, int xScale, int yScale) {
        for (int i = 0; i < points.size(); i++) {
            if (i == 0) {
                continue;
            }

            Point lastPoint = points.get(i - 1);
            Point currentPoint = points.get(i);

            final int lastPointX = midX + lastPoint.x * ((xDestinationPoint - xStartingPoint) / xScale);
            final int lastPointY = xHeight - lastPoint.y * ((yDestinationPoint - yStartingPoint) / yScale);

            final int currentPointX = midX + currentPoint.x * ((xDestinationPoint - xStartingPoint) / xScale);
            final int currentPointY = xHeight - currentPoint.y * ((yDestinationPoint - yStartingPoint) / yScale);

            g2.drawLine(lastPointX, lastPointY, currentPointX, currentPointY);
        }
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
