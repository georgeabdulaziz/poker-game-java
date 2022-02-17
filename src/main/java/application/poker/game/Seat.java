package application.poker.game;

import java.util.ArrayList;
import java.util.List;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Seat {
	final static int sceneWidth = 1500;
	final static int sceneHeight = 800;
	final static int cardWidth = sceneWidth/60; 



//	public void setPlayerName(String playerName) {
//		this.playerName = playerName;
//		this.playerNameText = new Text(playerName);
//		this.playerNameText.setX(0);
//		this.playerNameText.setY(0);
//		this.seatPane.getChildren().add(this.playerNameText);
//		this.isAvailable = false;
//	}

	public void	seatPlayer(Player player){
		this.playerName = player.getName();
		this.playerNameText = new Text(this.playerName);
		this.playerNameText.setX(0);
		this.playerNameText.setY(0);
		this.seatPane.getChildren().add(this.playerNameText);


		this.playerChips = player.getChips();
		this.playerChipsText = new Text(String.valueOf(this.playerChips));
		this.playerChipsText.setX(50);
		this.playerChipsText.setY(0);
		this.seatPane.getChildren().add(this.playerChipsText);

		this.seatPlayer = player;
		this.isAvailable = false;
	}


//	public Image getPlayerImage() {
//		return playerImage;
//	}

	public Player getSeatPlayer(){
		return this.seatPlayer;
	}

	public void refreshSeat(){
		this.playerChips = this.seatPlayer.getChips();
		this.playerChipsText.setText(String.valueOf(this.playerChips));
	}

	public void setPlayerImage(String playerImage) {
		this.playerImage = new Image(playerImage);
		this.playerImageView = new ImageView(this.playerImage);
	}
	
	//TODO 
//	public void unSetPlayerName() {
//		
//	}


	public void clearHand() {
		this.cardPane.getChildren().clear();
		this.seatPane.setStyle("-fx-background-color: slategray");
		//this.seatPane.getChildren().clear();
		this.xCardOffset = 0;
	}

	public Pane getSeatPane() {
		return this.seatPane;
	}


	public void setSeatPane(Pane seatPane) {
		this.seatPane = seatPane;
	}


	public Circle getImageShape() {
		return imageShape;
	}


	public void setImageShape(Circle imageShape) {
		this.imageShape = imageShape;
	}



	public ImageView getPlayerImageView() {
		return playerImageView;
	}


	public void setPlayerImageView(ImageView playerImageView) {
		this.playerImageView = playerImageView;
	}
	
	public Pane getCardPane() {
		return this.cardPane;
	}

//	public Image getCard1() {
//		return card1;
//	}


//	public void setCard1(String card1) {
//		this.card1 = new Image(card1);
//		this.card1ImageView = new ImageView(this.card1);
//		this.card1ImageView.setX(0);
//		this.card1ImageView.setY(100);
//		this.card1ImageView.setFitHeight(200);
//		this.card1ImageView.setFitWidth(cardWidth*2); 
//		this.card1ImageView.setPreserveRatio(true);
//	}

//
//	public Image getCard2() {
//		return card2;
//	}

	//public ImageView setCard(String card, int num) {
	//this function is used by attach card function so it can't be called publically 
	//This function could be a part of a utility class
	public static ImageView setCard(String card, int num) {
		Image newImage = new Image("/cards/" +card+".png");
		//Image newImage = new Image("myCards/14h.png");
		ImageView newImageView = new ImageView(newImage);
		newImageView.setX((cardWidth+2)*2*num);
		newImageView.setY(0);
		newImageView.setFitHeight(200);
		newImageView.setFitWidth(cardWidth*2); 
		newImageView.setPreserveRatio(true);
		//adding the id is very important to be able to translate the card down when getting the result
		newImageView.setId(card);
		return newImageView;
		//this.cards.add(newImageView);
		
	}

	private Pane seatPane;
	private Pane cardPane;
	private Circle imageShape;
	private String playerName;
	private Image playerImage;
	private ImageView playerImageView;
	private Text playerNameText;
	private int playerChips;
	private Text playerChipsText;
	private List<ImageView> cards;
	private int xCardOffset;
	boolean isAvailable;
	public Player seatPlayer;
	//TODO The buttons must only appear for the main player and not for the server players
	//TODO The buttons must be hidden if the player is not playing right now
	private Button callButton;
	private Button foldButton;
	private Button raiseButton;
	
	//the arguments are the positions of the seat in the table and the player name of the seat 
	public Seat(int xPanePosition, int yPanePosition) {
		this.callButton = new Button("Call");
		this.raiseButton = new Button("Raise");
		this.foldButton = new Button("Fold");
		this.seatPane = new Pane();
		this.cardPane = new Pane();
		//the position of the card relative to the seat
		this.cardPane.setLayoutX(10);
		this.cardPane.setLayoutY(10);
		this.cardPane.setMinWidth(cardWidth*8);
		this.cardPane.setMinHeight(100);
		
		this.cards = new ArrayList<ImageView>();
		
		this.seatPane.setLayoutX(xPanePosition);
		this.seatPane.setLayoutY(yPanePosition);
		this.seatPane.setMinWidth(100);
		this.seatPane.setMinHeight(100);
		this.seatPane.setStyle("-fx-background-color: slategray"); //TODO remove
		//setPlayerName(playerName);
		//this.seatPane.getChildren().add(playerNameText);
		this.seatPane.getChildren().add(cardPane);
		this.xCardOffset = 0;
		this.isAvailable = true;
	}
	
	
	public void printWinner(Text m) {
		this.cardPane.getChildren().add(m);
		this.seatPane.setStyle("-fx-background-color: silver");
	}
	
	
	
	
	//public void attatchCard(String cardString, int cardNum) {
	public void attachCard(String cardString) {
		try {
			//paneList.add(playerImageView);
			//paneList.add(playerNameText);
			
			//setCard("./cards/"+cardString+".png", cardNum);
		
			
			ImageView toAdd = setCard(cardString, this.xCardOffset);
			//this.cardPane.getChildren().add(this.cards.get(cardNum));
			this.cardPane.getChildren().add(toAdd);
			this.xCardOffset +=1;

	
		}
		catch(Error e){
			System.out.println(e.getMessage());
		}
	}

}
