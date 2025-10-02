package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Optional;
import java.util.function.Consumer;

public class PawnView extends JLayeredPane {

    private static final int PAWN_PADDING = 5;
    public static final int RADIUS = 45;

    private final int id;

    private final Color color;

    private Point mouseOffset;
    private JLayeredPane rootLayer;
    private Container formerParent;
    private Point formerLocation;
    private Container currentParent;
    private Point currentLocation;

    private boolean listenersEnabled;
    private boolean selected;

    private Optional<int[]> cellId;

    private Consumer<Point> onMousePressed;
    private Consumer<Point> onMouseReleased;
    private Consumer<Point> onMouseDragged;

    public PawnView(int x, int y, Color color, int id) {
        this.color = color;
        this.id = id;
        this.selected = false;

        this.setLayout(null);
        this.setBounds(x, y, 2 * PawnView.RADIUS, 2 * PawnView.RADIUS);
        this.setOpaque(false);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if (PawnView.this.onMousePressed != null && PawnView.this.listenersEnabled) {
                    PawnView.this.onMousePressed.accept(event.getPoint());
                }
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                if (PawnView.this.onMouseReleased != null && PawnView.this.listenersEnabled) {
                    PawnView.this.onMouseReleased.accept(event.getPoint());
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent event) {
                if (PawnView.this.onMouseDragged != null && PawnView.this.listenersEnabled) {
                    PawnView.this.onMouseDragged.accept(event.getPoint());
                }
            }
        });
    }

    public Point getMouseOffset() {
        return this.mouseOffset;
    }

    public void setMouseOffset(Point mouseOffset) {
        this.mouseOffset = mouseOffset;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public Optional<int[]> getCellId() {
        return this.cellId;
    }

    public JLayeredPane getRootLayer() {
        return this.rootLayer;
    }

    public Container getCurrentParent() {
        return this.currentParent;
    }

    public void setCurrentParent(Container currentParent) {
        this.currentParent = currentParent;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Color getColor() {
        return this.color;
    }

    public void enableListeners() {
        this.listenersEnabled = true;
    }

    public void disableListeners() {
        this.listenersEnabled = false;
    }

    public void setOnMousePressed(Consumer<Point> handler) {
        this.onMousePressed = handler;
    }

    public void setOnMouseReleased(Consumer<Point> handler) {
        this.onMouseReleased = handler;
    }

    public void setOnMouseDragged(Consumer<Point> handler) {
        this.onMouseDragged = handler;
    }

    public Point convertPointToRootLayer(Point point) {
        return SwingUtilities.convertPoint(this, point, this.rootLayer);
    }

    public void startDrag() {
        this.selected = true;
        this.rootLayer = SwingUtilities.getRootPane(this).getLayeredPane();

        Point point = SwingUtilities.convertPoint(
                this.currentParent,
                this.currentLocation,
                this.rootLayer
        );

        this.currentParent.remove(this);
        this.rootLayer.add(this, JLayeredPane.DRAG_LAYER);
        this.setLocation(point);
        this.rootLayer.repaint();
    }

    public CellView findTargetCell(Point mousePosition) {
        CellView cell = null;
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        GridView gridView = (GridView) frame.getContentPane().getComponents()[0];

        for (CellView cellView : gridView.getCells()) {
            Rectangle bounds = SwingUtilities.convertRectangle(cellView.getParent(), cellView.getBounds(), this.rootLayer);
            if (bounds.contains(mousePosition)) {
                cell = cellView;
                break;
            }
        }
        return cell;
    }

    public void snapToCell(CellView cell) {
        this.rootLayer.remove(this);
        this.rootLayer.repaint();

        this.cellId = Optional.of(new int[] {cell.getRowId(), cell.getColumnId()});
        this.setLocation(PawnView.PAWN_PADDING, PawnView.PAWN_PADDING);
        cell.add(this, JLayeredPane.PALETTE_LAYER);
        cell.revalidate();
        cell.repaint();

        this.formerParent = this.currentParent;
        this.currentParent = cell;
    }

    public void cancelMove() {
        this.rootLayer.remove(this);
        this.rootLayer.repaint();

        this.currentParent.add(this);
        this.setLocation(this.currentLocation);
        this.currentParent.revalidate();
        this.currentParent.repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D) graphics.create();

        int x = 0;
        int y = 0;

        float centerX = x + PawnView.RADIUS;
        float centerY = y + PawnView.RADIUS;

        float[] dist = {0.4f, 1.0f};
        Color[] colors = {this.color, this.color.darker()};

        RadialGradientPaint paint = new RadialGradientPaint(
                new Point2D.Float(centerX, centerY),
                PawnView.RADIUS,
                dist,
                colors
        );

        g2d.setPaint(paint);
        g2d.fillOval(x, y, PawnView.RADIUS * 2, PawnView.RADIUS * 2);

        GradientPaint highlight = new GradientPaint(
                centerX, y, new Color(255, 255, 255, 50),
                centerX, y + PawnView.RADIUS / 3f, new Color(255, 255, 255, 0)
        );
        g2d.setPaint(highlight);
        g2d.fillOval(x + PawnView.RADIUS / 2, y, PawnView.RADIUS, PawnView.RADIUS / 2);

        g2d.dispose();
    }
}
