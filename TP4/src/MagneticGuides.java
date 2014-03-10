import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CRectangle;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Drag;
import fr.lri.swingstates.sm.transitions.Press;
import fr.lri.swingstates.sm.transitions.Release;

/**
 * @author Nicolas Roussel (roussel@lri.fr)
 * 
 */
public class MagneticGuides extends JFrame {

	private Canvas canvas;
	private CExtensionalTag oTag;
	private List<MagneticGuide> listGuides = new ArrayList<>();
	private boolean guidesHidden = false;

	public MagneticGuides(String title, int width, int height) {
		super(title);
		canvas = new Canvas(width, height);
		canvas.setAntialiased(true);
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (guidesHidden) {
						for (MagneticGuide g : listGuides) {
							g.display();
						}
						guidesHidden = false;
					} else {
						for (MagneticGuide g : listGuides) {
							g.hide();
						}
						guidesHidden = true;
					}
				}
			}
		});
		getContentPane().add(canvas);

		oTag = new CExtensionalTag() {
		};

		CStateMachine sm = new CStateMachine() {

			private Point2D p;
			private CShape draggedShape;

			@SuppressWarnings("unused")
			public State start = new State() {
				Transition createHorozontalGuide = new Press(BUTTON1) {
					@Override
					public void action() {
						MagneticGuide guide = new MagneticGuide(canvas,
								getMouseEvent().getX(), getMouseEvent().getY(),
								MagneticGuide.DIR_HOR);
						listGuides.add(guide);
					}
				};
				Transition createVerticalGuide = new Press(BUTTON3) {
					@Override
					public void action() {
						MagneticGuide guide = new MagneticGuide(canvas,
								getMouseEvent().getX(), getMouseEvent().getY(),
								MagneticGuide.DIR_VERT);
						listGuides.add(guide);
					}
				};

				Transition pressOnGuide = new PressOnShape(BUTTON1, ">> oDrag") {
					@Override
					public void action() {
						p = getPoint();
						draggedShape = getShape();
					}
				};
				Transition removeGuide = new PressOnShape(BUTTON2) {
					@Override
					public void action() {
						for (MagneticGuide g : listGuides) {
							if (g.getSegment() == getShape()){
								listGuides.remove(g);
								getShape().remove();
								break;
							}
						}
					}
				};

				Transition pressOnObject = new PressOnTag(oTag, BUTTON1,
						">> oDrag") {
					public void action() {
						p = getPoint();
						draggedShape = getShape();
					}
				};
			};

			@SuppressWarnings("unused")
			public State oDrag = new State() {

				Transition dragLine = new Drag(BUTTON1) {
					public void action() {
						Point2D q = getPoint();
						for (MagneticGuide g : listGuides) {
							if (draggedShape.hasTag(g))
								switch (g.getDirection()) {
								case MagneticGuide.DIR_HOR:
									g.translateBy(0, q.getY() - p.getY());
									break;
								case MagneticGuide.DIR_VERT:
									g.translateBy(q.getX() - p.getX(), 0);
									break;
								default:
									break;
								}

						}
						p = q;
					}
				};

				Transition dragOnShape = new DragOnTag(oTag, BUTTON1) {
					public void action() {
						Point2D q = getPoint();
						draggedShape.translateBy(q.getX() - p.getX(), q.getY()
								- p.getY());
						p = q;
						// test si le carre est dans un guide
						for (MagneticGuide g : listGuides) {
							if (g.isAttachable(getShape())) {
								g.getSegment().setStroke(new BasicStroke(2));
								g.attachShape(getShape());
							} else {
								g.getSegment().setStroke(new BasicStroke(1));
								g.detachShape(getShape());
							}
						}
					}
				};

				Transition release = new Release(BUTTON1, ">> start") {
					@Override
					public void action() {
						for (MagneticGuide g : listGuides) {
							g.getSegment().setStroke(new BasicStroke(1));
						}
					}
				};
			};

		};
		sm.attachTo(canvas);

		pack();
		setVisible(true);
		canvas.requestFocusInWindow();
	}

	public void populate() {
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		double s = (Math.random() / 2.0 + 0.5) * 30.0;
		double x = s + Math.random() * (width - 2 * s);
		double y = s + Math.random() * (height - 2 * s);

		int red = (int) ((0.8 + Math.random() * 0.2) * 255);
		int green = (int) ((0.8 + Math.random() * 0.2) * 255);
		int blue = (int) ((0.8 + Math.random() * 0.2) * 255);

		CRectangle r = canvas.newRectangle(x, y, s, s);
		r.setFillPaint(new Color(red, green, blue));
		r.addTag(oTag);
	}

	public List<MagneticGuide> getGuides() {
		return listGuides;
	}

	public static void main(String[] args) {
		MagneticGuides guides = new MagneticGuides("Magnetic guides", 600, 600);
		for (int i = 0; i < 20; ++i)
			guides.populate();
		guides.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
