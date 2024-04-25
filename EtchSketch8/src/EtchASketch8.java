
/*
 * Name 1: Suresh Krishna Devendran
 * 
 * Name 2: Andrew Lane
 * 
 * Name 3:
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EtchASketch8 extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private static final int width = 600;
  private static final int height = 600;
  private GraphicsContext gc;
  private ColorPicker colorPicker;
  private Color color;

  @Override
  public void start(Stage stage) throws Exception {

    BorderPane pane = new BorderPane();
    Canvas canvas = new Canvas(width, height);
    pane.setCenter(canvas);
    gc = canvas.getGraphicsContext2D();

    // TODO 3: Register the handler
    EventHandler<MouseEvent> handler = new MouseHandler();
    canvas.setOnMouseClicked(handler);
    canvas.setOnMouseMoved(handler);

    // TODO 5: Consider a ColorPicker
    colorPicker = new ColorPicker();
    colorPicker.setValue(Color.RED);
    pane.setTop(colorPicker);
    
    
    // TODO 6: Handle color changes
    colorPicker.setOnAction(new ColorChanger());
    color = colorPicker.getValue();
    gc.setStroke(color);
    
    
    // Set the stage
    Scene scene = new Scene(pane, width, height);
    stage.setScene(scene);
    stage.show();
  }

  private class ColorChanger implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
      color = colorPicker.getValue();
      gc.setStroke(color);
    }
  }

  // TODO 1: Add a mouse handler 
  private class MouseHandler implements EventHandler<MouseEvent>{

    private boolean drawing;
    double oldX, oldY;
    double newX, newY;

    public MouseHandler() {
      drawing = false;
    }

    @Override
    public void handle(MouseEvent event) {
   		if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
   		  drawing = !drawing;
   		  newX = event.getX();
   		  newY = event.getY();
   		  System.out.println("Mouse clicked");
   		}
    
    
    // TODO 2: Handle a click to toggle drawing

    // TODO 4: Uncomment the drawing code when the mouse moves  
       
    if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
      // Construct a line between the oldX, oldY and where the
      // mouse was just clicked, but only if drawing.  
      // Then call repaint that draws the list of lines.
      if (drawing) {
        oldX = newX;
        oldY = newY;
        newX = event.getX();
        newY = event.getY();
        System.out.println(newX + " Moved " + newY);
    
        // Draw a new line from the last place the mouse moved to the present
       
        gc.strokeLine(oldX, oldY, newX, newY);
      }
    }
    }
     
  }
}