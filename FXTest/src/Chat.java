

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

	public class Chat extends Application {

	    @Override
	    public void start(Stage primaryStage) throws Exception{
	    	VBox vBox=new VBox();
	    	HBox hBox=new HBox();
	    	Button btnsend= new Button("Send");
	    	Button callButton=new Button();
	    	ImageView img=new ImageView("Voice.png");
	    	img.setFitHeight(18);
	    	img.setFitWidth(18);
	    	callButton.setGraphic(img);
	    	Button videoButton=new Button();
	    	ImageView img1=new ImageView("Video.png");
	    	img1.setFitHeight(18);
	    	img1.setFitWidth(20);
	    	videoButton.setGraphic(img1);
	    	TextArea Chat=new TextArea();
	    	Chat.setPrefHeight(600);
	    	Chat.setPrefWidth(300);
	    	TextField message=new TextField();
	    	message.setPrefWidth(180);
	    	hBox.getChildren().addAll(message,btnsend,callButton,videoButton);
	    	vBox.getChildren().addAll(Chat,hBox);
	    	StringBuilder fieldContent = new StringBuilder(""); 
	    	btnsend.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	   	    	 
	 	    	   fieldContent.append(message.getText()+"\n");
	 	    	   Chat.setText(fieldContent.toString());
	    	    }
	    	}); 
	    	Chat.setDisable(true);
	    	Scene scene= new Scene(vBox,300,600);
	    
	        primaryStage.setTitle("Chat");
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	    }
	    
	    

	    public static void main(String[] args) {
	        launch(args);
	    }
	}

