package controleur;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Triangle implements Shape {
    private Color color;
    private int[] xPoints;
    private int[] yPoints;
    private int brushSize;
    private boolean fill;
    
    public Triangle(int[] xPoints, int[] yPoints, Color color, int brushSize) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
        this.brushSize = brushSize;
        this.fill = false;
    }

    public int[] getXPoints() {
        return this.xPoints;
    }

    public int[] getYPoints() {
        return this.yPoints;
    }
    
    public void setFill(boolean fill) {
        this.fill = fill;
    }
    
    public void setXPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }

    public void setYPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(brushSize));
        g2d.drawPolygon(xPoints, yPoints, 3);
        
        if (fill) {
            g2d.fill(this);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Polygon(xPoints, yPoints, 3).getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return new Polygon(xPoints, yPoints, 3).getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
        return new Polygon(xPoints, yPoints, 3).contains(x, y);
    }

    @Override
    public boolean contains(Point2D p) {
        return contains(p.getX(), p.getY());
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return new Polygon(xPoints, yPoints, 3).intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return new Polygon(xPoints, yPoints, 3).contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return new Polygon(xPoints, yPoints, 3).getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return new Polygon(xPoints, yPoints, 3).getPathIterator(at, flatness);
    }
}
