/*
 * Click
 * Click 
 */
package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import controleur.*;
import controleur.Action.*;
import java.awt.Shape;
import java.util.Stack;


/**
 *
 * @author MILIA Juvaldo Wisman
 */
public class DrawSpace extends JPanel {

    private static Stack<Action> undoStack;
    private static Stack<Action> redoStack;
    private static ArrayList<Point> freehandPoints;
    public ArrayList<Point> freehandCopy;
    private static List<Shape> formes;
    private Shape newShape;
    private Shape CurrentShape;
    private Rectangle1 Currentrect;
    private Circle Currentcircle;
    private Triangle Currenttri;
    private Ligne Currentligne;
    private Shape selectedShape;
    private  int BrushSize;
    private Graphics2D g2d;
    public int LastX, LastY ,r,x,y;
    
    public <T extends Integer> DrawSpace(T BrushSize){
        //Constructeur
        this.BrushSize = BrushSize;
        
        //initialiser les listener
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                canvasMouseClicked(e);
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                canvasMousePressed(e);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent ev) {
                canvasMouseRealeased(ev);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                canvasMouseDragged(evt);
            }
        });
        
        //initialiser la pile des action
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        
        //initialiser le tracer
        freehandPoints = new ArrayList<Point>();
        
        
        //initialiser la liste des formes
        formes = new ArrayList<>();
        
    }
    
    
    public void ClearCurrentShape(){
        this.CurrentShape = null;
    }
    
    public void setBrushSize(int value){
        this.BrushSize = value;
    }
    
    public void clear(){
        undoStack.clear();
        redoStack.clear();
        formes.clear();
        freehandPoints.clear();
        MaFenetre.ColUsed.setBackground(Color.BLACK);
        repaint();
    }

    //test
    
    //test
    public void addShape(Shape shape) {
        formes.add(shape);
        Action action = new ShapeAction(shape);
        undoStack.push(action);
        //redoStack.clear();
        repaint();
    }

    public void removeShape(Shape shape) {
        formes.remove(shape);
        Action action = new ShapeAction(shape);
        redoStack.push(action);
        undoStack.pop();
        repaint();
    }
    
    public void addFreehandPoints(ArrayList<Point> points) {
        freehandPoints.addAll(points);
        Action action = new FreehandAction(points);
        undoStack.push(action);
        //redoStack.clear();
        repaint();
    }

    public void removeFreehandPoints(ArrayList<Point> points) {
        if (!freehandCopy.isEmpty()) {
            freehandCopy.removeAll(points);
            Action action = new FreehandAction(points);
            redoStack.push(action);
            undoStack.pop();
        }
        repaint();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            action.undo();
            redoStack.push(action);
            repaint();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();
            action.redo();
            undoStack.push(action);
            repaint();
        }
    }

public void canvasMouseClicked(MouseEvent e) {
    int mouseX = e.getX();
    int mouseY = e.getY();
    
    if(MaFenetre.tool == "Remplir"){
        for (Action action : undoStack) {
            if (action instanceof ShapeAction) {
                Shape shape = ((ShapeAction) action).getShape();
                if(shape instanceof Circle){
                        System.out.println("cerle");
                    }else if(shape instanceof Rectangle1){
                        System.out.println("Rectangle");
                    }else if(shape instanceof Triangle){
                        System.out.println("Triangle");
                    }else if(shape instanceof Ligne){
                        System.out.println("Ligne");
                    }
                if (shape.contains(mouseX, mouseY)) {
                    // Le clic se trouve à l'intérieur de la forme, vous pouvez la remplir ici
                    if (shape instanceof Circle){
                    Circle circle = (Circle) shape;
                    circle.setFill(true);
                    }else if (shape instanceof Rectangle1){
                    Rectangle1 rect = (Rectangle1) shape;
                    rect.setFill(true);
                    }else if (shape instanceof Triangle){
                    Triangle tri = (Triangle) shape;
                    tri.setFill(true);
                    }
                    addShape(shape);
                    // Redessinez le composant pour mettre à jour l'affichage
                    repaint();
                    break; // Sortir de la boucle une fois que vous avez trouvé la forme correspondante
                }
            }
        }
    }
}    

    public void canvasMousePressed(MouseEvent e) {
        //LastPoint = e.getPoint();
        LastX = e.getX();
        LastY = e.getY();

        if(MaFenetre.tool == "Rectangle"){
            //test rect 
            int X =LastX;
            int Y = LastY;
            int width = 0;
            int height = 0;

            this.CurrentShape = new Rectangle1(X, Y, width, height, MaFenetre.LaCouleur,this.BrushSize);
            
            Currentrect = (Rectangle1) this.CurrentShape;
            
        }else if(MaFenetre.tool == "Cercle"){
            int raduis = 3;
            this.CurrentShape = new Circle(LastX, LastY,raduis, MaFenetre.LaCouleur,this.BrushSize);
            Currentcircle = (Circle) this.CurrentShape;
        } else if(MaFenetre.tool == "Ligne"){
            
            this.CurrentShape = new Ligne(LastX, LastY,LastX,LastY, MaFenetre.LaCouleur,this.BrushSize);
            Currentligne = (Ligne) this.CurrentShape;
        }else if(MaFenetre.tool == "Triangle"){
            int[] xPoints = {LastX, LastX, LastX};
            int[] yPoints = {LastY, LastY, LastY};
            CurrentShape = new Triangle(xPoints, yPoints, MaFenetre.LaCouleur,this.BrushSize);
            Currenttri = (Triangle) this.CurrentShape;
        }else if(MaFenetre.tool == "Select"){
            //selectioneur
            for (Action action : undoStack) {
                if (action instanceof ShapeAction) {
                    Shape shape = ((ShapeAction) action).getShape();
                    
                    if (shape.contains(LastX, LastY)) {
                        // Forme trouvée, ajoutez une nouvelle instance de ShapeAction à la undoStack
                        Action newAction = new ShapeAction(shape);
                        undoStack.push(newAction);
                        selectedShape = shape;
                        repaint();
                        break; // Sortez de la boucle dès que vous trouvez une forme
                    }
                }
            }
        
        }

        
    }

    public void canvasMouseDragged(MouseEvent evt) {
        //LastPoint = evt.getPoint();
        x =  evt.getX();
        y = evt.getY();
        
        if(MaFenetre.tool == "Pinceau"){
                freehandPoints.add(new Point(x,y,MaFenetre.LaCouleur,this.BrushSize));
                
            //LastX = x;
            //LastY = y;
            
            
        }else if(MaFenetre.tool == "Gomme"){
                freehandPoints.add(new Point(x,y,Color.WHITE,this.BrushSize));
        }
        
        //affichage en temps reel
        if(MaFenetre.tool == "Ligne"){
            if (CurrentShape instanceof Ligne){
                Currentligne = (Ligne) CurrentShape;
                Currentligne.setX2(x);
                Currentligne.setY2(y);
            }
        }else if(MaFenetre.tool == "Rectangle"){
            
            //curentshape 
            if (CurrentShape instanceof Rectangle1){
                Currentrect = (Rectangle1) CurrentShape;
                int width = x - Currentrect.getX();
                int height = y - Currentrect.getY();
                Currentrect.setWidth(width);
                Currentrect.setHeight(height);
            }
        } else if(MaFenetre.tool == "Cercle"){
            if (CurrentShape instanceof Circle) {
                Currentcircle = (Circle) CurrentShape; // Conversion vers le type Circle
                
                int raduis = (int) Math.sqrt(Math.pow(x-Currentcircle.getX(), 2) + Math.pow(y-Currentcircle.getY(), 2));
                Currentcircle.setRadius(raduis);
                
            }
            
        }else if(MaFenetre.tool == "Triangle"){
            if (CurrentShape instanceof Triangle) {
                Currenttri = (Triangle) CurrentShape;
                int [] Xp = Currenttri.getXPoints();
                int [] Yp = Currenttri.getYPoints();
                //int x2 = Xp[0] + Xp[1] / 2;
                int x2 = (LastX + x) / 2;
                //this.CurrentShape.setter("X1",x);
                //this.CurrentShape.setter("Y1",y);
                //this.CurrentShape.setter("X2",x2);
                //this.CurrentShape.setter("Y2",y);
                int [] Xpt ={LastX,x,x2};
                Currenttri.setXPoints(Xpt);
                int [] Ypt ={LastY,LastY,y};
                Currenttri.setXPoints(Ypt);
            }
        }else if(MaFenetre.tool == "Select"){
            //selectionneur
            if (selectedShape != null) {
                for (Action action : undoStack) {
                    if (action instanceof ShapeAction) {
                        Shape shape = ((ShapeAction) action).getShape();
                        if (shape == selectedShape) {
                            // Mettez à jour la position de la forme dans l'action
                            ((ShapeAction) action).updateShapePosition(x, y);
                            if(shape instanceof Circle){
                                Circle circle = (Circle) shape;
                                circle.setX(evt.getX());
                                circle.setY(evt.getY());
                            }else if(shape instanceof Rectangle1){
                                Rectangle1 rectangle = (Rectangle1) shape;
                                rectangle.setX(evt.getX());
                                rectangle.setY(evt.getY());
                            }else if(shape instanceof Triangle){
                                
                                System.out.println("Triangle");
                            }else if(shape instanceof Ligne){
                                System.out.println("Ligne");
                            }
                            
                            repaint();
                            break; // Sortez de la boucle dès que vous trouvez la forme correspondante
                        }
                    }
                }      
            }
        }

        repaint();
        //System.out.println("( "+LastX+" , "+LastY+" )");
    }

   public void canvasMouseRealeased(MouseEvent e) {
        //LastPoint = e.getPoint();
        x = e.getX();
        y = e.getY();

        

        if(MaFenetre.tool == "Pinceau"){
            freehandCopy = new ArrayList<>(freehandPoints);
            addFreehandPoints(freehandCopy);
            freehandPoints = new ArrayList<Point>();
            
        }else if(MaFenetre.tool == "Ligne"){
            if(LastX != 0 && LastY != 0){
                this.newShape = new Ligne(LastX,LastY,x,y,MaFenetre.LaCouleur,this.BrushSize);

                addShape(newShape);
                
            }
            
        }else if(MaFenetre.tool == "Cercle"){
            
            r = (int) Math.sqrt(Math.pow(x- LastX, 2) + Math.pow(y -LastY, 2));
            this.newShape = new Circle(LastX, LastY, r, MaFenetre.LaCouleur,this.BrushSize);
            addShape(newShape);
            
            
            
        }else if(MaFenetre.tool == "Rectangle"){
        
            int X = Math.min(LastX, x);
            int Y = Math.min(LastY, y);
            int width = Math.abs(x - LastX);
            int height = Math.abs(y - LastY);
            
            this.newShape = new Rectangle1(X, Y, width, height, MaFenetre.LaCouleur,this.BrushSize);
            addShape(newShape);
            
        }else if(MaFenetre.tool == "Triangle"){
            int x1 = LastX;
            int y1 = LastY;
            int x2 = y;
            int y2 = y;

            // Calculer les coordonnées du troisième sommet
            int x3 = ( LastX + x)/2;

            
            int [] xPoints = {x1,x2,x3};
            int [] yPoints = {y1, y1, y2};
            
            this.newShape = new Triangle(xPoints, yPoints, MaFenetre.LaCouleur,this.BrushSize);
            addShape(newShape);
        }else if(MaFenetre.tool == "Select"){
            //selectionneur
            addShape(selectedShape);
            selectedShape = null;
        }
        
        
        ClearCurrentShape();
        
        repaint();
        
        
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < undoStack.size(); i++) {
            Action action = undoStack.get(i);
            if (action instanceof ShapeAction) {
                Shape shape = ((ShapeAction) action).getShape();
                if (shape instanceof Circle) {
                    Circle circle = (Circle) shape;
                    circle.draw(g2d);
                } else if (shape instanceof Rectangle1) {
                    Rectangle1 rect = (Rectangle1) shape;
                    rect.draw(g2d);
                } else if (shape instanceof Triangle) {
                    Triangle tri = (Triangle) shape;
                    tri.draw(g2d);
                }else if(shape instanceof Ligne) {
                    Ligne ligne = (Ligne ) shape;
                    ligne.draw(g2d);
                }
            }
            if (action instanceof FreehandAction) {
                ArrayList<Point> freehandCopy = ((FreehandAction) action).getFreehandPoints();
                for (int j = 1; j < freehandCopy.size(); j++) {
                    Point p1 = freehandCopy.get(j - 1);
                    Point p2 = freehandCopy.get(j);
                    g2d.setColor(p1.getColor());
                    g2d.setStroke(new BasicStroke(p1.getStroke()));
                    g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }

        if (CurrentShape != null) {
            if (CurrentShape instanceof Circle) {
                Circle circle = (Circle) CurrentShape;
                circle.draw(g2d);
            } else if (CurrentShape instanceof Rectangle1) {
                Rectangle1 rect = (Rectangle1) CurrentShape;
                rect.draw(g2d);
            } else if (CurrentShape instanceof Triangle) {
                Triangle tri = (Triangle) CurrentShape;
                tri.draw(g2d);
            } else if (CurrentShape instanceof Ligne) {
                Ligne line = (Ligne) CurrentShape;
                line.draw(g2d);
            }
        }

        if (!freehandPoints.isEmpty()) {
            if (MaFenetre.tool == "Gomme") {
                g2d.setColor(Color.WHITE);
            } else {
                g2d.setColor(MaFenetre.LaCouleur);
            }
            for (int i = 1; i < freehandPoints.size(); i++) {
                Point p1 = freehandPoints.get(i - 1);
                Point p2 = freehandPoints.get(i);
                g2d.setColor(p1.getColor());
                g2d.setStroke(new BasicStroke(p1.getStroke()));
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    
}
