package controleur;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Ligne implements Shape {
    private Color color;
    private int brushSize;
    private Line2D line;
    private int x1,x2,y1,y2;

    public Ligne(int x1, int y1, int x2, int y2, Color color, int brushSize) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.brushSize = brushSize;
        
    }
    
    public void setX2(int x2){
        this.x2 = x2;
    }
    
    public void setY2(int y2){
        this.y2 = y2;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(brushSize));
        g2d.drawLine(x1, y1, x2, y2);
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return line.getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
        return line.contains(x, y);
    }

    @Override
    public boolean contains(Point2D p) {
        return line.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return line.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return line.intersects(r);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return line.contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return line.contains(r);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return line.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return line.getPathIterator(at, flatness);
    }
}
