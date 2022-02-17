package application.poker.game;

public class Bank {
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	private int money;

	public int getChips() {
		return chips;
	}

	public void setChips(int chips) {
		this.chips = chips;
	}

	private int chips;
//	public Bank(int chips) {
//		this.chips = chips;
//		this.money = money;
//	}
	public Bank() {
		this.chips = 0;
		this.money = 0;
	}
	
	public void buyChips() {
		//for later use
	}

	public void grantChips(int amount){
		this.chips+=1;
	}
	
}