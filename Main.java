package browser;

import javafx.application.Application;
import javafx.beans.value.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.*;
import javafx.stage.Stage;

public class Main extends Application {
  private WebEngine WebEngineOne;
  private String startPage =  "https://google.com";

  public void start(Stage stage) {
    stage.setTitle("WebBrowser");
    
    final TextField addressBar = new TextField();

    addressBar.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
	  String nonValidateUrl = addressBar.getText();
	  String ValidateUrl = validateUrl(nonValidateUrl);
	  
	  
        WebEngineOne.load(ValidateUrl);
      }
    });

    WebView myBrowser = new WebView();
    WebEngineOne = myBrowser.getEngine();
    WebEngineOne.getLoadWorker().exceptionProperty().addListener(new ChangeListener<Throwable>() {
      @Override public void changed(ObservableValue<? extends Throwable> observableValue, Throwable oldException, Throwable exception) {
        System.out.println("WebView encountered an exception loading a page: " + exception);
      }
    });
    WebEngineOne.load(startPage);

    VBox root = new VBox();
    root.getChildren().setAll(
        addressBar,
        myBrowser
    );
    stage.setScene(new Scene(root));
    stage.show();
  }
  
  private String validateUrl(String inputUrl)
  {
      if(inputUrl.startsWith("https://"))
      {
	  return inputUrl;
      }else if(inputUrl.startsWith("http://"))
      {
	  return inputUrl;
      }else{
	  inputUrl = "http://" + inputUrl;
	  return inputUrl;
      }
      
      
      
      
      
  }

  public static void main(String[] args) { launch(args); }
}