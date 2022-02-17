package application.poker.game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
//import poker.Game;
//import poker.Player;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			System.out.println(String.valueOf(screenSize.getHeight()));
			System.out.println(String.valueOf(screenSize.getWidth()));

//		      ObservableList<Screen> screenSizes = Screen.getScreens();
//		      screenSizes.forEach(screen -> {
//		        System.out.println(screen.getBounds());
//		      });



			int sceneWidth = 1500;
			int sceneHeight = 800;
			int cardWidth = sceneWidth/60;  //leaving some margin space

			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,sceneWidth,sceneHeight);
			//scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			String css = getClass().getResource("").toExternalForm();
			System.out.println(css);
			//scene.getStylesheets().add(css+"style.css");
			//scene.setFill(Color.GREEN);


			root.setStyle("-fx-background-color: GREEN");


//			getStyleClass().add("bordered-titled-border");

			Line line = new Line();
			line.setStartX(100.0);
			line.setStartY(150.0);
			line.setEndX(500.0);
			line.setEndY(150.0);

			Text text = new Text();
			text.setFont(new Font(20));
			text.setX(50);
			text.setY(180);
			text.setText("Welcome to my Poker table");

			Text text2 = new Text();
			text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			text2.setText("verdana bold font posture is regular with size 20");
			text2.setX(20);
			text2.setY(200);
			text2.setFill(Color.BEIGE);
			text2.setStrokeWidth(1);
			text2.setStroke(Color.BLUE);

			Ellipse ellipse = new Ellipse(34, 50);
			ellipse.setFill(Color.BROWN);


		      Path path = new Path();

		      //Moving to the starting point
		      MoveTo moveTo = new MoveTo(108, 71);

		      //Creating 1st line
		      LineTo line1 = new LineTo(321, 161);

		      //Creating 2nd line
		      LineTo line2 = new LineTo(126,232);

		      //Creating 3rd line
		      LineTo line3 = new LineTo(232,52);

		      //Creating 4th line
		      LineTo line4 = new LineTo(269, 250);

		      //Creating 4th line
		      LineTo line5 = new LineTo(108, 71);

		      //Adding all the elements to the path
		      path.getElements().add(moveTo);
		      path.getElements().addAll(line1, line2, line3, line4, line5);




		    //Creating an image
		      //Image image = new Image("/cards/8h.png");

		      //Setting the image view
		      //ImageView imageView = new ImageView(image);

		      //Setting the position of the image
		     // imageView.setX(100);
		     // imageView.setY(70);

		      //setting the fit height and width of the image view
		      //imageView.setFitHeight(200);
		      //imageView.setFitWidth(cardWidth);

		      //Setting the preserve ratio of the image view
		      //imageView.setPreserveRatio(true);


		      //Glow glow = new Glow();
		      //glow.setLevel(0.9);

		     // imageView.setEffect(glow);



		      ImageInput imageInput = new ImageInput();
		      imageInput.setX(50);
		      imageInput.setY(200);
		      //imageInput.setSource(image);

		      Rectangle rectangle = new Rectangle(50,50,100,0);
		      rectangle.setEffect(imageInput);


		      Translate translate = new Translate();
		      translate.setX(300);
		      translate.setY(50);
		      translate.setZ(20);

		      //imageView.getTransforms().addAll(translate);

		      TranslateTransition translateTransision = new TranslateTransition();
		      translateTransision.setDuration(Duration.millis(1000));
		     // translateTransision.setNode(imageView);
		      translateTransision.setByX(150);
		      translateTransision.setCycleCount(3);
		      translateTransision.setAutoReverse(false);
		      translateTransision.play();

		      ObservableList<Node> rootList =  root.getChildren();

		      Table table = new Table(0,0, 6, 300, 700);
		      table.getTablePane().getStyleClass().add("bordered-titled-border");
		      table.getTablePane().setStyle("-fx-background-color: BEIGE;");

		      ArrayList<Player> players = new ArrayList<Player>();
		      String[] playersNames = {"George", "John", "Fedel", "Feras", "Salem", "Jake"};
		      for(String player: playersNames) {
		      		Player newPlayer = new Player(player);
		      		newPlayer.setChips(100);
		    		players.add(newPlayer);
		      		table.seatPlayer(newPlayer);

		      }


		      Game myGame = new Game(players, 7, 0, 0, 0); //TODO
					//System.out.println(myGame.toString());

		      //////////////////////////////////////
		      //StackPane stack = new StackPane();
		      VBox stack = new VBox();

		     // Region r = new Region();
//		      r.setX(200);
//		      r.setY(500);
		      //r.setLayoutX(200);
		     // r.setLayoutY(500);
//		      r.setWidth(200);
//		      r.setHeight(200);
//		      r.setArcWidth(20);
//		      r.setArcHeight(20);
		      //r.setFill(Color.BROWN);
		      //r.setStyle("-fx-background-color: #afaf4a;");
		     // r.getStyleClass().add("region");

		      //Text buttonRun = new Text("Run");


		      Button runButton = new Button("Run");
		      runButton.setWrapText(true);
		      runButton.setStyle("-fx-font-size: 15");
		      runButton.setStyle("-fx-Height: 100");
		      runButton.setMinSize(100, 100);
		      //runButton.setLayoutY(100);
		      //runButton.setId("button");
		      //runButton.setWidth(200);

		      //runButton.setFill(Color.BLUE);
		      //runButton.setStyle("-fx-background-color: #00ff00");
		      //runButton.getStyleClass().add("myButton");
		      runButton.setStyle("-fx-background-color: BROWN;  -fx-cursor: hand;");
		      runButton.setLayoutX(0);
		      runButton.setLayoutY(0);


		      Button resultButton = new Button("Result");
		      //resultButton.getStyleClass().add("myButton");
		      resultButton.setStyle("-fx-background-color: tomato; -fx-cursor: hand;");
		      //resultButton.setLayoutX(0);
		      //resultButton.setLayoutY(200);
		      resultButton.setMinSize(100, 100);
		      resultButton.setWrapText(true);


//		      StrokeType     strokeType     = StrokeType.INSIDE;
//		      StrokeLineJoin strokeLineJoin = StrokeLineJoin.MITER;
//		      StrokeLineCap  strokeLineCap  = StrokeLineCap.BUTT;
//		      double         miterLimit     = 10;
//		      double         dashOffset     = 0;
//		      List<Double>   dashArray      = null;
//
//		      BorderStrokeStyle borderStrokeStyle =
//		    	        new BorderStrokeStyle(
//		    	                strokeType,
//		    	                strokeLineJoin,
//		    	                strokeLineCap,
//		    	                miterLimit,
//		    	                dashOffset,
//		    	                dashArray
//		    	        );
//
//
//		    	BorderStroke borderStroke =
//		    	        new BorderStroke(
//		    	                Color.valueOf("08ff80"),
//		    	                borderStrokeStyle,
//		    	                new CornerRadii(0),
//		    	                new BorderWidths(8)
//		    	        );
//
//		    	Border border = new Border(borderStroke);



		      //stack.setAlignment(Pos.TOP_LEFT);
		      stack.setLayoutX(100);
		      stack.setLayoutY(300);
		      stack.setMaxHeight(500);
		      stack.setMaxWidth(500);

		      stack.getChildren().addAll(runButton, resultButton);
		      //stack.setBorder(border);
		      //stack.setStyle("-fx-background-color: YELLOW;");

		      //ObservableList<Node> newList = new StackPane().getChildren();

//		      Pane newList = new Pane();
//		      newList.setLayoutX(0);
//		      newList.setLayoutY(0);
//		      newList.setMinWidth(1000);
//		      newList.setMinHeight(1000);
//		      rootList.add(newList);

//		      table.getTablePane().setFill();
		      rootList.add(table.getTablePane());
		      rootList.add(stack);


		      //newList.setPrefWidth(getFitHeight());
		      //newList.setPrefSize(1000,1000);
		      //newList.setAlignment(Pos.TOP_LEFT);
		      //newList.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
		      EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
		         @Override
		         public void handle(MouseEvent e) {
		        		//System.out.println("yasss");
		        	 	//list.remove(newList);
		        	 	//rootList.remove(newList);

		        	 	//newList.getChildren().clear();

		        	 	myGame.gameStart();
		        		//gameGUI(newList, myGame, cardWidth);
		        		//gameGUI1(newList, myGame);
		        	 	gameGUI2(table, myGame);

		        		//newList.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		        		//note: when I add the new list the current button "run" gets covered with the
		        		//new Pane

		        		//rootList.add(newList);
		        		//rootList.remove(stack);

		        		//primaryStage.show();


		         }
		      };

		      EventHandler<ActionEvent> handCards = new EventHandler<ActionEvent>() {
			         @Override
			         public void handle(ActionEvent e) {
			        	 	myGame.gameStart();
			        	 	gameGUI2(table, myGame);
			         }
			      };

			  EventHandler<ActionEvent> bestHand = new EventHandler<ActionEvent>() {
				     @Override
				      public void handle(ActionEvent e) {

				        	guiBestHand(table, myGame);
				      }
				 };

		      //runButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		      runButton.setOnAction(handCards);
		      resultButton.setOnAction(bestHand);

		      /////////////////////////////////////////





		      //rootList.add(stack);
		      //scene.getChildren().add(stack);

//			result.append("\n");
//			result.append(table.toString());
//			result.append("\n");
//			result.append("\t\t\t The winner is: "+this.winner.getName());
//			result.append("\t"+bestHand.getEvalResult()+" "+this.bestHand.toString());




//			list.add(line);
//			list.add(text);
//			list.add(ellipse);
//			list.add(path);
//			list.add(text2);
//			list.add(imageView);
			//list.add(rectangle);


			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.setMinWidth(500);
			primaryStage.setMinHeight(300);

			primaryStage.setTitle("PokerTable");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


//	public void gameGUI(Pane list, Game myGame, int cardWidth) {
//
//		int num=0;
//	      for(String card : myGame.toCards()) {
//	    	  Image newImage = new Image("./cards/"+card+".png");
//	    	  ImageView newImageView = new ImageView(newImage);
//	    	  newImageView.setX(num*cardWidth + 1);
//	    	  newImageView.setFitHeight(200);
//		      newImageView.setFitWidth(cardWidth);
//		      newImageView.setPreserveRatio(true);
//		      list.getChildren().add(newImageView);
//		      num+=1;
//	      }
//
//
//
//
//		for(int j=0; j< myGame.players.size(); j++) {
//			Player x = myGame.players.get(j);
//			Text playerName = new Text(x.getName());
//			playerName.setX(20);
//			playerName.setY(j*100 + 100);
//			list.getChildren().add(playerName);
//
//			int num1 = 0;
//			for(String card: x.getHand().toCard()) {
//				Image newImage = new Image("./cards/"+card+".png");
//		    	  ImageView newImageView = new ImageView(newImage);
//		    	  newImageView.setX(cardWidth*2*num1 + 20);
//		    	  newImageView.setY(j*100 + 100);
//		    	  newImageView.setFitHeight(200);
//			      newImageView.setFitWidth(cardWidth*2);
//			      newImageView.setPreserveRatio(true);
//			      list.getChildren().add(newImageView);
//			      num1+=1;
//			}
//		}
//
//		//System.out.println(myGame.table.toString());
//		int num2 = 0;
//		for(String card: myGame.table.toCard()) {
//			Image newImage = new Image("./cards/"+card+".png");
//	    	  ImageView newImageView = new ImageView(newImage);
//	    	  newImageView.setX(cardWidth*2*num2 + 300);
//	    	  newImageView.setY(300);
//	    	  newImageView.setFitHeight(200);
//		      newImageView.setFitWidth(cardWidth*2);
//		      newImageView.setPreserveRatio(true);
//		      list.getChildren().add(newImageView);
//		      num2+=1;
//		}
//
//
//		Collections.sort(myGame.bestHand.getHand(), new CardComparator());
//		int num3 = 0;
//		for(String card: myGame.bestHand.toCard()) {
//			Image newImage = new Image("./cards/"+card+".png");
//	    	  ImageView newImageView = new ImageView(newImage);
//	    	  newImageView.setX(cardWidth*2*num3 + 300);
//	    	  newImageView.setY(600);
//	    	  newImageView.setFitHeight(200);
//		      newImageView.setFitWidth(cardWidth*2);
//		      newImageView.setPreserveRatio(true);
//		      list.getChildren().add(newImageView);
//		      num3+=1;
//		}
//		String bestHandString =  myGame.bestHand.getEvalResult();
//		Text bestHandText = new Text("The winner is: "+myGame.winner.getName() +"  "+ bestHandString);
//		bestHandText.setX(cardWidth*2 - 30);
//		bestHandText.setY(700);
//		list.getChildren().add(bestHandText);
//
//	}
//




	public void gameGUI2(Table table, Game myGame) {
		//TODO clear the cards of all the players
		table.tableClearCards();
		for(int j=0; j< myGame.players.size(); j++) {
			Player x = myGame.players.get(j);

			//TODO the player will be linked to the seat
			Seat seat = table.getSeatsList().get(j);

			//clearing the the cards of the seat
			seat.clearHand();

			for(String card: x.getHand().toCard()) {
				//null is for the Pane 0 for the position of the card and false is for a table
				seat.attachCard(card);
			    //num1+=1;
			}

			//adding the seat pane to the list (later the table)
			//list.getChildren().add(newSeat.getSeatPane());
		}

		int num2 = 0;
		for(String card: myGame.table.toCard()) {
			table.attachCardToTable(card, num2);

	      if(num2>1) {
	    	  num2+=2;
	      }
	      else {
	    	  num2+=1;
	      }
		}
	}








//	public void gameGUI1(Pane list, Game myGame) {
//		int cardWidth = 1500/60;
//
//
//
//		for(int j=0; j< myGame.players.size(); j++) {
//			Player x = myGame.players.get(j);
//			int offset = 200;
//			if(j==3) {
//				offset = 300;
//			}
//			Seat newSeat = new Seat(offset, 100*(j+1));
//
//			//seating the player
//			newSeat.setPlayerName(x.getName());
//			//int num1 = 0;
//			for(String card: x.getHand().toCard()) {
//				newSeat.attachCard(card);
//			    //num1+=1;
//			}
//
//			//adding the seat pane to the list (later the table)
//			list.getChildren().add(newSeat.getSeatPane());
//		}

		//System.out.println(myGame.table.toString());
//		int num2 = 0;
//		for(String card: myGame.table.toCard()) {
//			Image newImage = new Image("./cards/"+card+".png");
//	    	  ImageView newImageView = new ImageView(newImage);
//	    	  newImageView.setX(cardWidth*2*num2 + 300);
//	    	  newImageView.setY(300);
//	    	  newImageView.setFitHeight(200);
//		      newImageView.setFitWidth(cardWidth*2);
//		      newImageView.setPreserveRatio(true);
//		      list.getChildren().add(newImageView);
//		      num2+=1;
//		}
//
//
//		Collections.sort(myGame.bestHand.getHand(), new CardComparator());
//		int num3 = 0;
//		for(String card: myGame.bestHand.toCard()) {
//			Image newImage = new Image("./cards/"+card+".png");
//	    	  ImageView newImageView = new ImageView(newImage);
//	    	  newImageView.setX(cardWidth*2*num3 + 300);
//	    	  newImageView.setY(600);
//	    	  newImageView.setFitHeight(200);
//		      newImageView.setFitWidth(cardWidth*2);
//		      newImageView.setPreserveRatio(true);
//		      list.getChildren().add(newImageView);
//		      num3+=1;
//		}
//		String bestHandString =  myGame.bestHand.getEvalResult();
//		Text bestHandText = new Text("The winner is: "+myGame.winner.getName() +"  "+ bestHandString);
//		bestHandText.setX(cardWidth*2 - 30);
//		bestHandText.setY(700);
//		list.getChildren().add(bestHandText);


//	}


//	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), evt -> label.setVisible(false)),
//        new KeyFrame(Duration.seconds( 0.1), evt -> label.setVisible(true)));
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();

//    Label label = new Label("Blink");
//    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.1), label);
//    fadeTransition.setFromValue(1.0);
//    fadeTransition.setToValue(0.0);
//    fadeTransition.setCycleCount(Animation.INDEFINITE);
//    fadeTransition.play();

	public void guiBestHand(Table table, Game myGame) {
		//Collections.sort(myGame.bestHand.getHand(), new CardComparator());
		int num3 = 0;
		if(myGame.winner==null){
			return;
		}
		for(Seat seat : table.getSeatsList()) {
			if(seat.getSeatPlayer().getName().equals(myGame.winner.getName())) {//TODO should be based on a unique user id
			    Text winMessage = new Text(myGame.bestHand.getEvalResult());
			    winMessage.setX(120);
			    winMessage.setY(50);
			    winMessage.setId("winMessage");
			    seat.printWinner(winMessage);
			    int currentChips = seat.getSeatPlayer().getChips();
				seat.getSeatPlayer().setChips(currentChips+10);
				seat.refreshSeat();

				for(String card: myGame.bestHand.toCard()) {
					for(Node seatCard: seat.getCardPane().getChildren()) {
						if(seatCard.getId().equals(card)) {
							seatCard.setTranslateY(20);
						}
					}
					for(Node tableCard: table.getTableCardsPane().getChildren()) {
						if(tableCard.getId().equals(card)) {
							tableCard.setTranslateY(20);
						}
					}
				}
			}
			else{//TODO this is just a temp solution
				int currentChips = seat.getSeatPlayer().getChips();
				seat.getSeatPlayer().setChips(currentChips-10);
				seat.refreshSeat();
			}
		}
		return;
	}










	public static void main(String[] args) {

		InputStream res =
			    Main.class.getResourceAsStream("/my-resource.txt");

			    BufferedReader reader =
			        new BufferedReader(new InputStreamReader(res));
			    String line = null;
			    try {
					while ((line = reader.readLine()) != null) {
					    System.out.println(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		launch(args);
	}
}
