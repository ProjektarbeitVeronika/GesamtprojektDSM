package userinterface;

import java.util.ArrayList;
import java.util.List;

import algorithm.Measurement;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.application.*;
import algorithm.ToolMeasure;
import algorithm.DataProcessor;
import algorithm.DataService;

public class Diagramm extends Application {

	/*
	 * Create a button named "add" create a button named "start".
	 */
	Button add, start;

	@SuppressWarnings("unchecked")

	/*
	 * Create a method which contains the stage. Stage is the main window and
	 * all elements of the window are entered there
	 */

	@Override
	public void start(Stage stage) throws InterruptedException {

		/* Create variables x, y and z from type string to store each axis */

		String x = "X-Achse";
		String y = "Y-Achse";
		String z = "Z-Achse";

		/* The stage is named "x-y-z-Ebene" */
		stage.setTitle("x-y-z-Ebene");

		/*
		 * Create new axes and fix the scale for the axes: plotting a range of
		 * numbers.
		 */
		final NumberAxis xAxis = new NumberAxis(0, 30, 1);
		final NumberAxis yAxis = new NumberAxis(0, 30, 1);

		final NumberAxis xAxis1 = new NumberAxis(0, 30, 1);
		final NumberAxis yAxis1 = new NumberAxis(0, 30, 1);

		final NumberAxis xAxis2 = new NumberAxis(0, 30, 1);
		final NumberAxis yAxis2 = new NumberAxis(0, 30, 1);

		/* Set labels for the axes (with declared string-variables). */
		xAxis.setLabel(x);
		yAxis.setLabel(y);

		xAxis1.setLabel(x);
		yAxis1.setLabel(z);

		xAxis2.setLabel(z);
		yAxis2.setLabel(y);

		/* Create new scatter-charts and put the created axes on them. */
		final ScatterChart<Number, Number> s1 = new ScatterChart<Number, Number>(xAxis, yAxis);
		final ScatterChart<Number, Number> s2 = new ScatterChart<Number, Number>(xAxis1, yAxis1);
		final ScatterChart<Number, Number> s3 = new ScatterChart<Number, Number>(xAxis2, yAxis2);

		/* Set title for the scatter-charts. */
		s1.setTitle("XY-Ebene");
		s2.setTitle("XZ-Ebene");
		s3.setTitle("YZ-Ebene");

		/*
		 * Create new series for the coordinate system and set name.
		 */
		XYChart.Series series1 = new XYChart.Series();
//		series1.setName("XY-Diagramm");
		XYChart.Series series2 = new XYChart.Series();
//		series2.setName("XZ-Diagramm");
		XYChart.Series series3 = new XYChart.Series();
//		series3.setName("YZ-Diagramm");

		/*
		 * set size for each scatter-chart and add the series on the
		 * scatter-charts, which contain the axes
		 */
		s1.setPrefSize(400, 300);
		s1.getData().addAll(series1);

		s2.setPrefSize(400, 300);
		s2.getData().addAll(series2);

		s3.setPrefSize(400, 300);
		s3.getData().addAll(series3);

		/* create a new scene */
		Scene scene = new Scene(new Group());

		/* create vbox and hbox for the scatter-charts */
		final VBox vbox = new VBox();
		final HBox hbox = new HBox();

		/* create new button "start" with the name "Start" */
		start = new Button("Start");

		/*
		 * add action on the button "start" if the button is clicked, there will
		 * be shown the values x, y and z on the axes of the scatter-charts
		 */
		start.setOnAction((event) -> {
			series1.getData().clear();

			// String choice = "xyz";

			// if (radioB1.isSelected()) {
			// choice = "xyz";
			// series1.setName("XY-Diagramm");
			// series2.setName("XZ-Diagramm");
			// }

			// create an object from the class "DataService" in package
			// algorithm
			DataService da = new DataService();

			// number of passes
			Gui myGui = new Gui();
			int countGoToNext = myGui.toloadvalue;  

			// all Tools with all measurements
			List<ToolMeasure> tools = da.loadNextData(countGoToNext);

			// List<Measurement> l = new ArrayList();

			// ToolMeasure tx = new ToolMeasure();

			/*
			 * The for statement picks the tools from the List <ToolMeasure>
			 * until the list-size of the tools
			 */
			for (int i = 0; i < tools.size(); i++) {
				ToolMeasure tool = tools.get(i);

				// all measurements from one tool
				List<Measurement> li = tool.getMeasurement();

				/*
				 * Call up the method "drawAchsen" from the class
				 * "Coordinatesystem". Hand over the "choice", the tool,
				 * required series and axes
				 */
				Coordinatesystem.drawAchsen("xy", li, series1, xAxis, yAxis);
				Coordinatesystem.drawAchsen("xz", li, series2, xAxis, yAxis);
				Coordinatesystem.drawAchsen("zy", li, series3, xAxis, yAxis);
			}
		});

		hbox.setSpacing(10);
		// hbox.getChildren().addAll(s1, s2, s3);

		/*
		 * vbox added to the hbox
		 */
		hbox.getChildren().addAll(vbox);

		// vbox.getChildren().addAll(start, hbox);

		/*
		 * the button "start" and the scatter-charts added to the vbox
		 */
		vbox.getChildren().addAll(start, s1, s2, s3);
		hbox.setPadding(new Insets(50, 10, 50, 20));

		/*
		 * vbox added to the scene and the scene added to the main stage
		 */
		((Group) scene.getRoot()).getChildren().add(vbox);
		stage.setScene(scene);
		stage.show();

		// public static getStage(){
		// return stage;}

	}

	public static void main(String[] args) {

		launch(args);
	}
}
