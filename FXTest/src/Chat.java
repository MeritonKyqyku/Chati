

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

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;


	public class Chat extends Application implements EventHandler<ActionEvent> {
		 WebSocketClient cc;
		 VBox vBox=new VBox();
    	 HBox hBox=new HBox();
    	Button btnsend= new Button("Send");
    	Button callButton=new Button();
    	ImageView img=new ImageView("Voice.png");
    	
    	Button videoButton=new Button();
    	ImageView img1=new ImageView("Video.png");
    	Button connect=new Button();
    	
    	Button close= new Button();
    	
    	TextArea Chat=new TextArea();
    
    	TextField message=new TextField();

	    @Override
	    public void start(Stage primaryStage) throws Exception{
	    	
	    	img.setFitHeight(18);
	    	connect.setOnAction(this);
	    	
	    	img.setFitWidth(18);
	    	callButton.setGraphic(img);
	    	img1.setFitHeight(18);
	    	img1.setFitWidth(20);
	    	videoButton.setGraphic(img1);
	    	Chat.setPrefHeight(600);
	    	Chat.setPrefWidth(300);
	    	message.setPrefWidth(180);
	    	hBox.getChildren().addAll(message,btnsend,callButton,videoButton,connect,close);
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
	    public void actionPerformed( ActionEvent e ) {

			if( e.getSource() == message) {
				if( cc != null ) {
					cc.send( message.getText() );
					message.setText( "" );
					message.requestFocus();
				}

			} else if( e.getSource() == connect ) {
				try {
					// cc = new ChatClient(new URI(uriField.getText()), area, ( Draft ) draft.getSelectedItem() );
					cc = new WebSocketClient( new URI( "ws://localhost:81")) {

						@Override
						public void onMessage( String message ) {
							Chat.appendText( "got: " + message + "\n" );
							
						}

						@Override
						public void onOpen( ServerHandshake handshake ) {
							Chat.appendText( "You are connected to ChatServer: " + getURI() + "\n" );
							
						}

						@Override
						public void onClose( int code, String reason, boolean remote ) {
							Chat.appendText( "You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason + "\n" );
							
							
						}

						@Override
						public void onError( Exception ex ) {
							Chat.appendText( "Exception occured ...\n" + ex + "\n" );
						
							ex.printStackTrace();
							
						}
					};

					
					cc.connect();
				} catch ( URISyntaxException ex ) {
					Chat.appendText("Sosht ka ban is not a valid WebSocket URI\n" );
				}
			} else if( e.getSource() == close ) {
				cc.close();
			}
		}
	    
	    

	    public static void main(String[] args) {
	        launch(args);
	    }
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			
		}
	}

