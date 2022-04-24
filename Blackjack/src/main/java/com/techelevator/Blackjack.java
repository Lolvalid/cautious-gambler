package com.techelevator;

public class Blackjack {
	static boolean playerHadAce;
	static boolean dealerHadAce;
	final static int BLACKJACK_BABY = 21;
	//int playerAces;//setting logic eventually to check how many aces the dealer and player have.
	//int dealerAces;

	public static void main(String[] args) {
		boolean playerBusted = false;
		int playerTotal = 0;
		int[] playerChoseStay = new int[]{0, 8}; // Setting array to a suite outside of normal bounds;  can pass to main player stayed.
		int[] playerChoosingHitStayValue = new int[2];
		int dealerTotal = 0;
		int didPlayerStay = 1;
		char dealerCard = 'D';
		char playerCard = 'P';
		int softAce = 10; //difference of ace as 1 and 11
		//Drawing first two cards for player and dealer card.
		dealerTotal += hitDeck(dealerTotal, dealerCard);
		printTotalDealer(dealerTotal);

		playerTotal += hitDeck(playerTotal, playerCard);
		playerTotal += hitDeck(playerTotal, playerCard);
		printTotalPlayer(playerTotal);

		while (playerChoosingHitStayValue != playerChoseStay) {
			if (playerTotal == BLACKJACK_BABY){
				System.out.println("~*~*~*~YOU HIT BLACKJACK!~*~*~*~");
				System.out.println("The dealer will now draw.");
				dealerTotal = dealerLogic.playBlackjack(dealerHadAce, dealerTotal, playerTotal);
				printResults(playerTotal, dealerTotal);
				break;
			} else {
				System.out.println("[H]it or [S]tay?");
			}

			playerChoosingHitStayValue = playerChoices.stayOrHitPlayer(playerTotal);
			if (playerChoosingHitStayValue[didPlayerStay] == playerChoseStay[didPlayerStay]) {
				System.out.println("You have chosen to stay, the dealer will now draw.");

				dealerTotal = dealerLogic.playBlackjack(dealerHadAce, dealerTotal, playerTotal);
				printResults(playerTotal, dealerTotal);
				break;
			}
			playerTotal += playerChoosingHitStayValue[0];
			if (playerTotal > BLACKJACK_BABY && !playerHadAce) {
				playerBusted = true;
			} else if (playerTotal > BLACKJACK_BABY) {
				playerTotal -= softAce; //difference of ace as 1 and 11
				playerHadAce = false;// ace was converted, removing safety unless another ace pops up.
				System.out.println("Previous total plus new card would have busted, reducing an Ace's value to 1");
			}
			printTotalPlayer(playerTotal);

			if (playerBusted) {
				System.out.println("You have busted!");
				break;
			}
		}
		System.out.println("Shall we play again? --- [Y]es or [N]o");
		playerChoices.isAQuiter();

	}


	public static int hitDeck(int currentTotalOfCards, char whosCard) {
		char playersCard = 'P';
		char dealersCard = 'D';
		int playerTotal = 0;
		int dealerTotal = 0;
		int[][] theDeck = cardInteractions.buildTheDeck();
		int[] cardValue = new int[1];
		int hitCardsValue = 0; //accessing first card in array which will be what card was drawn
		int checkIfWasAce = 1; //accessing second value in array, if present and is 1 is an ace.

		//counts the total hand size to set the correct array lengths.
		if (whosCard == playersCard) {
			playerTotal += currentTotalOfCards;
			cardValue = cardInteractions.hitTheDeck(theDeck, playersCard);
		} else if (whosCard == dealersCard) {
			dealerTotal += currentTotalOfCards;
			cardValue = cardInteractions.hitTheDeck(theDeck, dealersCard);
		}

		//setting values for who drew an ace to enable soft ace protection.
		if (cardValue[checkIfWasAce] == checkIfWasAce && whosCard == playersCard) {
			playerHadAce = true;
		} else if (cardValue[checkIfWasAce] == checkIfWasAce) {
			dealerHadAce = true;
		}

		//returning values to main for what card was drawn
		if (whosCard == playersCard) {
			playerTotal = cardValue[hitCardsValue];
			return playerTotal;
		} else {
			dealerTotal = cardValue[hitCardsValue];
		}
		return dealerTotal;

	}

	public static void printTotalPlayer(int totalFromMain) {
		System.out.println("************************");
		System.out.println("Your total is: " + totalFromMain + ".");
		System.out.println("************************");
	}


	public static int totalHandSize(int numberOfCards) {
		//incrementing hand size for array as its used.
		return numberOfCards++;

	}

	public static void printTotalDealer(int totalFromMain) {
		System.out.println("************************");
		System.out.println("The dealer's total is: " + totalFromMain + ".");
		System.out.println("************************");
	}

	public static void printResults(int playerTotal, int dealerTotal) {
		//resolving game based on totals -gitlab is annoying
		if(dealerTotal>BLACKJACK_BABY){
			System.out.println("The dealer has busted, you win!");
		}else if (dealerTotal == playerTotal) {
			System.out.println("Your total is: " + playerTotal + ".");
			System.out.println("      vs the      ");
			System.out.println("Dealer's total: " + dealerTotal + ".");
			System.out.println("Dealer has tied you, the hand is pushed.");
		} else if (dealerTotal > playerTotal) {
			System.out.println("Your total is: " + playerTotal + ".");
			System.out.println("      vs the      ");
			System.out.println("Dealer's total: " + dealerTotal + ".");
			System.out.println("Dealer has beat you, you lose your money.");
		} else {
			System.out.println("Your total is: " + playerTotal + ".");
			System.out.println("      vs the      ");
			System.out.println("Dealer's total: " + dealerTotal + ".");
			System.out.println("You win! You beat the dealer!");
		}
	}
}





