import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import tsp.Edge;
import tsp.Node;
import tsp.TSP;
import tsp.Tour;

import java.awt.Point;
import java.io.File;

/**
 * Main View Controller
 * 
 * @author AJ Priola
 * @version 1.0
 */

public class MainViewController {

	@FXML Button runButton;
	@FXML Label fileLabel, progressLabel;
	@FXML Canvas canvas;

	private final double CANVAS_INSET = 8.0;
	private final double NODE_RADIUS = 2.0;

	private Tour tour;
	private File file;
	private double xOffset, yOffset;

	public MainViewController() {

	}

	public void setTour(Tour tour) {
		this.tour = tour;
		calculateOffset();
		drawNodes(canvas.getGraphicsContext2D());
	}

	public void setFile(File file) {
		this.file = file;
		runButton.setDisable(false);
		this.fileLabel.setText(file.getName());
	}

	private void reset() {
		clearNodes(canvas.getGraphicsContext2D());
		progressLabel.setText("0%");
	}

	public void runButtonClicked() {
		if (file != null) {
			clearNodes(canvas.getGraphicsContext2D());
			setTour(TSP.tour(file, progress -> {
				this.progressLabel.setText(String.format("%.02f%%", progress * 100));
			}));
		}
	}

	private void clearNodes(GraphicsContext context) {
		context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	private void drawNodes(GraphicsContext context) {

		System.out.println(String.format("Min: (%f, %f), Max: (%f, %f)", tour.xMin, tour.yMin, tour.xMax, tour.yMax));

		for (Node node : tour.getRoute()) {
			drawNode(context, node);
		}

		for (Edge edge : tour.getEdges()) {
			drawEdge(context, edge);
		}
	}

	private void calculateOffset() {
		if (tour.xMin < 0) xOffset = Math.abs(tour.xMin);
		if (tour.yMin < 0) yOffset = Math.abs(tour.yMin);
	}

	private Point displayPointFor(Node node) {

		double width = canvas.getWidth() - 2 * CANVAS_INSET;
		double height = canvas.getHeight() - 2 * CANVAS_INSET;

		double tourWidth = tour.xMax - tour.xMin + xOffset;
		double tourHeight = tour.yMax - tour.yMin + yOffset;

		int x = (int)(((node.x + (int)xOffset) / tourWidth) * width + CANVAS_INSET + NODE_RADIUS);
		int y = (int)(((node.y + (int)yOffset) / tourHeight) * height + CANVAS_INSET + NODE_RADIUS);

		return new Point(x, y);

	}

	private void drawEdge(GraphicsContext context, Edge edge) {
		context.setStroke(Color.BLACK);

		Point from = displayPointFor(edge.from);
		Point to = displayPointFor(edge.to);

		double x1 = (double)from.x + NODE_RADIUS;
		double y1 = (double)from.y + NODE_RADIUS;
		double x2 = (double)to.x + NODE_RADIUS;
		double y2 = (double)to.y + NODE_RADIUS;

		context.strokeLine(x1, y1, x2, y2);
	}

	private void drawNode(GraphicsContext context, Node node) {
		context.setFill(Color.BLACK);

		Point point = displayPointFor(node);

		int x = point.x;
		int y = point.y;

		context.fillOval(x, y, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
	}

	@FXML private void initialize() {
		runButton.setDisable(true);
	}
	

}
