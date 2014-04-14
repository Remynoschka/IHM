package gui;

import core.Dictionnaire;
import core.Document;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;

import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * Un editeur de texte sur lequel on peut dessiner
 *
 * @author DEMOL David
 */
public class TextEditor extends JTextArea implements MouseMotionListener, MouseListener, Serializable {

    private ArrayList<ColoredShape> shapes;
    private Document document;
    private ArrayList<Curve> curves;
    private int drawMode;
    private int initX1;
    private int initY1;
    private int initX2;
    private int initY2;
    private ColoredShape currentShape;
    protected final static int LINE = 1, SQUARE = 2, OVAL = 3, POLYGON = 4, ROUND_RECT = 5, FREE_HAND = 6, LIBRE = 7,
            SOLID_SQUARE = 22, SOLID_OVAL = 33, SOLID_POLYGON = 44, TEXT = 0,
            SOLID_ROUND_RECT = 55;
    private boolean isEdited = false;
    private static final String COMMIT_ACTION = "commit";


    private static enum Mode {

        INSERT, COMPLETION
    };
    private Mode mode = Mode.INSERT;

    public TextEditor() {
        shapes = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.white);
        this.drawMode = TEXT;
        curves = new ArrayList<>();
        curves.add(new Curve());

        InputMap im = this.getInputMap();
        ActionMap am = this.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_DOWN_MASK), COMMIT_ACTION);
        am.put(COMMIT_ACTION, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int pos = TextEditor.this.getCaretPosition();
                    System.out.println(pos);
                    String content = null;

                    content = getText(0, pos);

                    System.out.println(content);

                    // Find where the word starts
                    int w;
                    for (w = pos - 1; w >= 0; w--) {
                        if (!Character.isLetter(content.charAt(w))) {
                            break;
                        }
                    }

                    String prefix = content.substring(w + 1);
                    List<String> words = Dictionnaire.INSTANCE.getSignifications(prefix);
                    String completion = null;
                    for (String word : words) {
                            completion = word.substring(pos - w - 1);
                            SwingUtilities.invokeLater(
                                    new CompletionTask(completion, pos));
                            break;
                    }
                    if (completion == null) {
                        mode = Mode.INSERT;
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        for (ColoredShape s : shapes) {
            g2.setColor(s.getColor());
            if (s.isDrawShadow()) {
                drawShadow(s, g2);
            }
            g2.setColor(s.getColor());
            if (s.isFilled()) {
                g2.fill(s.getShape());
            } else {
                g2.draw(s.getShape());
            }
        }

        Point Pprev, Pcurrent;
        Iterator<Curve> itcurve = curves.iterator();

        Pprev = new Point();

        // Pour chaque courbe
        while (itcurve.hasNext()) {
            Iterator<Point> it = itcurve.next().points.iterator();

            if (it.hasNext()) {
                Pprev = it.next();
            }

            // Dessine les points d'une courbe
            while (it.hasNext()) {
                Pcurrent = it.next();
                g2.drawLine(Pprev.x, Pprev.y, Pcurrent.x, Pcurrent.y);
                Pprev = Pcurrent;
            }
        }


    }

    private void drawShadow(ColoredShape s, Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Paint orig = g2.getPaint();
        AffineTransform origAt = g2.getTransform();
        Composite comp = g2.getComposite();
        AffineTransform at = AffineTransform.getTranslateInstance(3.0, 2.0);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        g2.transform(at);
        g2.setPaint(Color.black.brighter());
        if (s.isFilled()) {
            g2.fill(s.getShape());
        } else {
            g2.draw(s.getShape());
        }
        g2.setPaint(orig);
        g2.setComposite(comp);
        g2.setTransform(origAt);
    }

    private ColoredShape drawShape() {
        ColoredShape s = new ColoredShape();
        int curX = initX1;
        int curY = initY1;
        if (initX1 > initX2) {
            int temp = initX2;
            initX2 = initX1;
            curX = temp;
        }
        if (initY1 > initY2) {
            int temp = initY2;
            initY2 = initY1;
            curY = temp;
        }
        float width = initX2 - curX;
        float height = initY2 - curY;
        switch (this.drawMode) {
            case SQUARE:
                Rectangle2D.Float rect;
                rect = new Rectangle2D.Float(curX, curY, width, height);
                s.setShape(rect);
                s.setFilled(false);
                break;
            case SOLID_SQUARE:
                Rectangle2D.Float rectFill;
                rectFill = new Rectangle2D.Float(curX, curY, width, height);
                s.setShape(rectFill);
                s.setFilled(true);
                break;
            case OVAL:
                Ellipse2D.Float circ = new Ellipse2D.Float(curX, curY, width, height);
                s.setShape(circ);
                s.setFilled(false);
                break;
            case SOLID_OVAL:
                Ellipse2D.Float circFill = new Ellipse2D.Float(curX, curY, width, height);
                s.setShape(circFill);
                s.setFilled(true);
                break;
            case ROUND_RECT:
                RoundRectangle2D.Float rndRect = new RoundRectangle2D.Float(curX, curY, width, height, 25, 25);
                s.setShape(rndRect);
                s.setFilled(false);
                break;
            case SOLID_ROUND_RECT:
                RoundRectangle2D.Float rndRectFill = new RoundRectangle2D.Float(curX, curY, width, height, 25, 25);
                s.setShape(rndRectFill);
                s.setFilled(true);
                break;
        }
        s.setColor(this.getForeground());
        s.setDrawShadow(false);
        return s;
    }

    public void setDrawMode(int mode) {
        this.drawMode = mode;
        if (mode == TEXT) {
            setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        } else {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public Color getForeGroundColor() {
        return this.getForeground();
    }

    public void setForeGroundColor(Color c) {
        this.setForeground(c);
    }

    public void setBackGroundColor(Color c) {
        this.setBackground(c);
    }

    public void setSolidMode(boolean b) {
    }

    public void flushPolygonBuffer() {
    }

    public boolean isExistPolygonBuffer() {
        return true;
    }

    public void clearCanvas() {
        this.shapes.clear();
    }

    public void addPoint(Integer x, Integer y) {
        curves.get(curves.size() - 1).addPoint(new Point(x, y));
        repaint();
    }


    /*
     * Implement MouseListener and MouseMotionListener
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (drawMode == TEXT) {
            //	 super.processMouseMotionEvent(e);
            return;
        }

        if ((drawMode == LIBRE)) {
            this.addPoint(e.getX(), e.getY());
        }

        initX2 = e.getX();
        initY2 = e.getY();
        if (currentShape != null) {
            this.shapes.set(this.shapes.size() - 1, drawShape());
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (drawMode == TEXT) {
            //		 super.processMouseEvent(e);
            return;
        }
        if ((drawMode == LIBRE) && (e.getButton() == MouseEvent.BUTTON1)) {
            curves.add(new Curve());
            return;
        }
        if (drawMode != POLYGON || drawMode != SOLID_POLYGON || drawMode != FREE_HAND) {
            //reset our position markers
            initX1 = e.getX();
            initY1 = e.getY();
            initX2 = e.getX();
            initY2 = e.getY();
            currentShape = this.drawShape();
            shapes.add(currentShape);
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (drawMode == TEXT) {
            //		 super.processMouseEvent(e);
            return;
        }
        currentShape = null;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (drawMode == TEXT) {
            //	 super.processMouseEvent(e);
            return;
        }
        if (e.getClickCount() == 2) {
            for (int i = shapes.size() - 1; i >= 0; i--) {
                ColoredShape s = shapes.get(i);
                if (s.getShape().contains(e.getPoint())) {
                    s.setDrawShadow(!s.isDrawShadow());
                    i = -1;
                }
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public Document getDocuments() {
        return this.document;
    }

    public void setDocuments(Document document) {
        this.document = document;
    }

    /**
     * A class to associate a Shape, the colour to draw it and whether the Shape
     * has a shadow
     *
     * @author tbanks
     *
     */
    private class ColoredShape {

        private Shape shape;
        private Color color;
        private boolean drawShadow;
        private boolean filled;

        public ColoredShape() {
        }

        public void setShape(Shape s) {
            this.shape = s;
        }

        public Shape getShape() {
            return shape;
        }

        public void setColor(Color c) {
            this.color = c;
        }

        public Color getColor() {
            return color;
        }

        public void setDrawShadow(boolean b) {
            this.drawShadow = b;
        }

        public boolean isDrawShadow() {
            return this.drawShadow;
        }

        public void setFilled(boolean f) {
            this.filled = f;
        }

        public boolean isFilled() {
            return this.filled;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Shape: " + this.shape + "\n");
            sb.append("Color: " + this.color + "\n");
            sb.append("Draw Shadow: " + this.drawShadow + "\n");
            sb.append("Fill Shape: " + this.filled + "\n");
            return sb.toString();
        }
    }

    class Point {

        public Integer x, y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }

    class Curve {

        public ArrayList<Point> points;

        Curve() {
            points = new ArrayList<>();
        }

        public void addPoint(Point P) {
            points.add(P);
        }

        public void clear() {
            points.clear();
        }
    }

    private class CompletionTask implements Runnable {

        String completion;
        int position;

        CompletionTask(String completion, int position) {
            this.completion = completion;
            this.position = position;
        }

        public void run() {

            //replaceRange(completion, position, position+completion.length());
            //replaceRange(completion, position, position+3);
            insert(completion, position);
            setCaretPosition(position - 1 + completion.length());
            moveCaretPosition(position);
            mode = Mode.COMPLETION;
        }
    }

    private class CommitAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ev) {
            if (mode == Mode.COMPLETION) {
                int pos = getSelectionEnd();
                insert(" ", pos);
                setCaretPosition(pos + 1);
                mode = Mode.INSERT;
            } else {
                replaceSelection("\n");
            }
        }
    }
}
