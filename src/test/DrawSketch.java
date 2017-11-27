package test;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class DrawSketch extends JPanel implements MouseMotionListener {

    private static final int recW = 75;
    private static final int MAX = 1;
    private Rectangle[] rect = new Rectangle[MAX];
    private int numOfRecs = 0;
    private int currentSquareIndex = -1;

    public DrawSketch() {

        addMouseListener(new MouseAdapter() {

            @Override

            public void mousePressed(MouseEvent evt) {

                int x = evt.getX();

                int y = evt.getY();

                currentSquareIndex = getRec(x, y);

                if (currentSquareIndex < 0) // not inside a square

                {

                    add(x, y);

                }

            }

//            @Override
//
//            public void mouseClicked(MouseEvent evt) {
//
//                int x = evt.getX();
//
//                int y = evt.getY();
//
//                if (evt.getClickCount() >= 2) {
//
//                    remove(currentSquareIndex);
//
//                }
//
//            }
//
        });

        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.RED);

        for (int i = 0; i < numOfRecs; i++) {

            ((Graphics2D) g).draw(rect[i]);

        }
    }

    public int getRec(int x, int y) {

        for (int i = 0; i < numOfRecs; i++) {

            if (rect[i].contains(x, y)) {

                return i;

            }

        }

        return -1;
    }

    public void add(int x, int y) {

        if (numOfRecs < MAX) {

            rect[numOfRecs] = new Rectangle(x, y, recW, recW);

            currentSquareIndex = numOfRecs;

            numOfRecs++;

            repaint();

        }
    }

    @Override
    public void remove(int n) {

        if (n < 0 || n >= numOfRecs) {

            return;

        }

        numOfRecs--;

        rect[n] = rect[numOfRecs];

        if (currentSquareIndex == n) {

            currentSquareIndex = -1;

        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent event) {

        int x = event.getX();

        int y = event.getY();

        if (getRec(x, y) >= 0) {

            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        } else {

            setCursor(Cursor.getDefaultCursor());

        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {

        int x = event.getX();

        int y = event.getY();
        System.out.println(x+"---"+y);

        if (currentSquareIndex >= 0) {

            Graphics graphics = getGraphics();

            graphics.setXORMode(getBackground());
            graphics.setColor(Color.RED);

            ((Graphics2D) graphics).draw(rect[currentSquareIndex]);

            rect[currentSquareIndex].x = x;

            rect[currentSquareIndex].y = y;

            ((Graphics2D) graphics).draw(rect[currentSquareIndex]);

            graphics.dispose();

        }
    }

    public static void main(String[] args) {

        JFrame jFrame = new JFrame();

        jFrame.setTitle("");

        jFrame.setSize(1000, 500);

        jFrame.addWindowListener(new WindowAdapter() {

            @Override

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

        JLabel videoArea = new JLabel("load a video,please",JLabel.CENTER);
        videoArea.setFont(new Font("",Font.BOLD,40));
        videoArea.setBounds(new Rectangle(0, 0, 856, 480));
        videoArea.setBorder(new EmptyBorder(0, 0, 0, 0));

        Container cPane = jFrame.getContentPane();
        cPane.add(videoArea);
        cPane.add(new DrawSketch());
        jFrame.setVisible(true);
    }
}