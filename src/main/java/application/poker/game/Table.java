package application.poker.game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Table {
	final private int tableWidth = 1400;
	final private int tableHeight = 800;
	
	
	final int sceneWidth = 1500;
	final int sceneHeight = 800;
	final int cardWidth = sceneWidth/60; 
	
	private int smallBlind;
	private int bigBlind;
	private int numOfSeats;
	private int maxEntryChips;
	private int minEntryChips;
	
	private List<Seat> seatsList;
	
	private Pane tablePane;
	
	private int seatToTry;
	
	//private boolean tableFull;
	private int numOfFullSeats;
	
	private Pane tableCardsPane;
	
	public Pane getTableCardsPane() {
		return this.tableCardsPane;
	}
	
	public List<Seat> getSeatsList() {
		return seatsList;
	}

//
//	public void setSeatsList(List<Seat> seatsList) {
//		this.seatsList = seatsList;
//	}


	public Pane getTablePane() {
		return tablePane;
	}


//	public void setTablePane(Pane tablePane) {
//		this.tablePane = tablePane;
//	}
	

	public void seatPlayer(Player player) {
		if(this.numOfFullSeats < this.numOfSeats) {
			if(seatsList.get(seatToTry%this.numOfSeats).isAvailable){
				seatsList.get(seatToTry%this.numOfSeats).seatPlayer(player);
				this.seatToTry += 1;
				this.numOfFullSeats+=1;
				//TODO you need a function to also reduce the num of full seats in case the player leaves
			}
			else {
				//keep trying until you seat the player as long as the table is not full
				this.seatToTry += 1;
				seatPlayer(player);
			}
		}
	}

	public void attachCardToTable(String card, int num) {
		try {
			ImageView toAdd = Seat.setCard(card, num);
			//this.cardPane.getChildren().add(this.cards.get(cardNum));
			this.tableCardsPane.getChildren().add(toAdd);
		}
		catch(Error e) {
			System.out.print(e.getMessage());
		}
	}
	
	public void tableClearCards() {
		this.tableCardsPane.getChildren().clear();
	}

	
	public Table(int smallBlind, int bigBlind, int numOfSeats, int minEntryChips, int maxEntryChips) {
		this.maxEntryChips = maxEntryChips;
		this.minEntryChips = minEntryChips;
		this.tableCardsPane = new Pane();
//		this.tableCardsPane.setLayoutX(tableWidth/2 - (cardWidth*4));
//		this.tableCardsPane.setLayoutY(tableHeight/2 - (cardWidth*3));
		this.tableCardsPane.setLayoutX(350);
		this.tableCardsPane.setLayoutY(140);
		this.tableCardsPane.setMinWidth(cardWidth*10);
		this.tableCardsPane.setMinHeight(cardWidth*4);
		this.tableCardsPane.setStyle("-fx-background-color: teal "); //TODO remove
		
		this.seatToTry = 0;
		this.numOfFullSeats = 0;
		//this.tableFull = false;
		this.smallBlind = smallBlind;
		this.bigBlind = bigBlind;
		this.numOfSeats = numOfSeats;
		this.seatsList = new ArrayList<Seat>(this.numOfSeats);
		
		this.tablePane = new Pane();
		this.tablePane.setLayoutX(200);
		this.tablePane.setLayoutY(200);
		this.tablePane.setMinWidth(tableWidth);
		this.tablePane.setMinHeight(tableHeight);
		this.tablePane.getChildren().add(this.tableCardsPane);
		//this.tablePane.setStyle("-fx-background-color: YELLOW"); 
		
		//distributing the seats based on their number 6 or 9
		if(this.numOfSeats == 6) {
			int xSeatPosition = 0;
			int ySeatPosition = 0;
			int xSeatSpacing = tableWidth/this.numOfSeats;
			int ySeatSpacing = tableHeight/3;
			for(int i = 0; i < this.numOfSeats; i++) {
				if(i == 3) {
					ySeatPosition += ySeatSpacing;
				}
				if(i > 3) {
					xSeatPosition -= xSeatSpacing;
				}
				else if(i < 3) {
					xSeatPosition += xSeatSpacing;
				}
				Seat newSeat = new Seat(xSeatPosition, ySeatPosition);
				this.seatsList.add(newSeat);
				//adding the seat Pane to the table Pane
				this.tablePane.getChildren().add(newSeat.getSeatPane());
			}
			
		}
		else if(this.numOfSeats == 9) {//TODO
			
		}
		else {
			System.out.println("table should have 6 or 9 seats");
		}
		
	}
	
	
	
	

}
