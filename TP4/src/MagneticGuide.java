import java.awt.Point;

import fr.lri.swingstates.canvas.CElement;
import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CSegment;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.Canvas;

public class MagneticGuide extends CExtensionalTag {
	private CSegment segment;
	public static final int DIR_VERT = 0;
	public static final int DIR_HOR = 1;
	private int direction;
	private Point position;

	public MagneticGuide(Canvas canvas, int x, int y, int direction) {
		super(canvas);
		position = new Point(x, y);
		this.direction = direction;
		display();
	}

	public CSegment getSegment() {
		return segment;
	}

	public void setSegment(CSegment segment) {
		this.segment = segment;
	}

	public void attachShape(CShape shape) {
		shape.addTag(this);
	}

	public void detachShape(CShape shape) {
		shape.removeTag(this);
	}

	public boolean isAttachable(CShape shape) {
		switch (direction) {
		case DIR_HOR:
			return (int) shape.getCenterY() == (int) segment.getCenterY();
		case DIR_VERT:
			return (int) shape.getCenterX() == (int) segment.getCenterX();
		default:
			return false;
		}
	}

	public int getDirection() {
		return direction;
	}

	public void display() {
		switch (direction) {
		case DIR_HOR:
			segment = canvas.newSegment(-canvas.getWidth() - 999,
					position.getY(), canvas.getWidth() + 9999,
					position.getY());
			segment.addTag(this);
			break;
		case DIR_VERT:
			segment = canvas.newSegment(position.getX(), -9999,
					position.getX(), canvas.getHeight() + 9999);
			segment.addTag(this);
			break;
		default:
			break;
		}
	}

	public void hide() {
		canvas.removeShape(segment);
		segment = canvas.newSegment(-99, -99, -99, -99);
	}
	
	@Override
	public CElement translateBy(double tx, double ty) {
		position.translate((int)tx, (int)ty);
		return super.translateBy(tx, ty);
	}
}
