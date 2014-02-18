package e201.skeleton;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JFrame;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CRectangle;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.debug.StateMachineEvent;
import fr.lri.swingstates.debug.StateMachineEventListener;
import fr.lri.swingstates.debug.StateMachineVisualization;
import fr.lri.swingstates.sm.BasicInputStateMachine;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Release;
import fr.lri.swingstates.sm.transitions.TimeOut;

/**
 * @author FRANCOIS Remy
 * 
 */
public class SimpleButton {

	private CText label;
	private CRectangle rectangle;
	private CStateMachine stateMachine;
	private int time;
	private int bouton;

	/**
	 * 
	 * @param canvas
	 * @param text
	 * @param time
	 * @param bouton
	 */
	SimpleButton(Canvas canvas, String text, int time, int bouton) {
		this.bouton = bouton;
		label = new CText(new Point(0, 0), text, new Font("verdana",
				Font.PLAIN, 12));
		rectangle = canvas.newRectangle(-5, -5, label.getWidth() + 10,
				label.getHeight() + 10);
		rectangle.setFillPaint(Color.WHITE);
		label = canvas.newText(0, 0, text, new Font("verdana", Font.PLAIN, 12));
		rectangle.addChild(label);
		this.time = time;

			
		stateMachine = new CStateMachine() {
			public State idle = new State() {

				Transition enterOnShape = new EnterOnShape(">> over") {
					public void action() {
						rectangle.setStroke(new BasicStroke(5));
					}
				};

			};
			public State over = new State() {
				Transition pressOnShape = new PressOnShape(
						SimpleButton.this.bouton, ">> pressed") {
					public void action() {
						rectangle.setStroke(new BasicStroke(1));
						rectangle.setFillPaint(Color.YELLOW);
						armTimer(SimpleButton.this.time, false);
						armTimer("double", 10, false);
					}
				};

				Transition leaveOnShape = new LeaveOnShape(">> idle") {
					public void action() {
						rectangle.setStroke(new BasicStroke(1));
						rectangle.setFillPaint(Color.WHITE);
					}
				};

			};
			public State pressed = new State() {
				Transition releaseOnShape = new ReleaseOnShape(
						SimpleButton.this.bouton, ">>over") {
					public void action() {
						doClick();
						rectangle.setFillPaint(Color.WHITE);
						rectangle.setStroke(new BasicStroke(5));
					}
				};
				Transition releaseOnBg = new Release(SimpleButton.this.bouton,
						">>idle") {
					public void action() {
						rectangle.setFillPaint(Color.WHITE);
					}
				};
				TimeOut demiclic = new TimeOut() {
					@Override
					public void action() {
						System.out.println("Demi Clic");
					}
				};
			};

		};

		stateMachine.attachTo(canvas);
		stateMachine.addStateMachineListener(new StateMachineEventListener() {
			public void smStateChanged(StateMachineEvent e) {
				System.out.println("State changed from "
						+ e.getPreviousState().getName() + " to "
						+ e.getCurrentState().getName() + "\n");
			}

			public void smStateLooped(StateMachineEvent e) {
				System.out.println("State looped on \n"
						+ e.getCurrentState().getName());
			}

			public void smSuspended(StateMachineEvent e) {
				System.out.println("State machine suspended\n" + e);
			}

			public void smResumed(StateMachineEvent e) {
				System.out.println("State machine resumed\n" + e);
			}

			public void smReset(StateMachineEvent e) {
				System.out.println("State machine reset\n" + e);
			}

			public void smInited(StateMachineEvent e) {
				System.out.println("State machine inited\n" + e);
			}

		});
	}

	public void doClick() {
		System.out.println("ACTION!");
	}

	public CShape getShape() {

		return rectangle;
	}

	static public void main(String[] args) {
		JFrame frame = new JFrame("Bouton");
		Canvas canvas = new Canvas(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(canvas);
		frame.pack();
		frame.setVisible(true);

		SimpleButton simple = new SimpleButton(canvas, "simple", 1000,
				BasicInputStateMachine.BUTTON1);
		simple.getShape().translateBy(100, 100);

		// StateMachineVisualization
		JFrame smframe = new JFrame("State Machine Visualization");
		smframe.add(new StateMachineVisualization(simple.stateMachine));
		smframe.pack();
		smframe.setVisible(true);
	}

}
