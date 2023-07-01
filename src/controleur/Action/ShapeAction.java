
package controleur.Action;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import vue.MaFenetre;

/**
 *
 * @author MILIA Juvaldo Wisman
 */
public class ShapeAction extends Action {
    private Shape shape;

    public ShapeAction(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void perform() {
        MaFenetre.canvas.addShape(this.shape);
    }

    @Override
    public void undo() {
        MaFenetre.canvas.removeShape(this.shape);
    }

    @Override
    public void redo() {
        MaFenetre.canvas.addShape(this.shape);
    }
    
    public Shape getShape() {
        return this.shape;
    }

    public void updateShapePosition(int x, int y) {
        AffineTransform transform = new AffineTransform();
        transform.translate(x, y);
        shape = transform.createTransformedShape(shape);
        MaFenetre.canvas.repaint();
    }
}

