package com.techelevator;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class playerChoices {
	static String[] cardsOnTablePlayer = new String[1];
	static String[] cardsOnTableDealer = new String[1];
	static Scanner choice = new Scanner(System.in);
	static int gamesPlayed = 0;
	public static void main(String[] args) {
	}

	public static int[] stayOrHitPlayer(int currentTotalOfCards) {

		String playerChoice = "";
		int[] playerHitAndValue = new int[2];
		int playerStays = 8; // random number that would be out of bounds for suite to designate to main that the player has stayed.
		boolean validChoice = false;
		//asking player to choose hit or stay, if hit draws card, else stays and returns to main
		try {
			playerChoice = choice.next();

			if (playerChoice.equalsIgnoreCase("H") || playerChoice.equalsIgnoreCase("Hit") || (playerChoice.equalsIgnoreCase("Stay") || playerChoice.equalsIgnoreCase("S"))) {
				validChoice = true;
			}

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			//checking to see if play wants to hit, if so draw a card.
			if (playerChoice.equalsIgnoreCase("H") || playerChoice.equalsIgnoreCase("Hit")) {
				for (int i = 0; i <= playerHitAndValue.length - 1; i++) {
					if (i > 0) {
						playerHitAndValue[i] = 0;
					} else {
						playerHitAndValue[i] = Blackjack.hitDeck(currentTotalOfCards,'P');
					}
				}
			//checking to see if player has selected stay, returning blank array if stay.
			} else if (playerChoice.equalsIgnoreCase("Stay") || playerChoice.equalsIgnoreCase("S")) {
				playerHitAndValue[0] = 0;
				playerHitAndValue[1] = playerStays;
				return playerHitAndValue;
			//reprompts if error.
			} else if (!validChoice) {
				System.out.println("Please enter a valid response.");
				System.out.println("[H]it or [S]tay?");
				stayOrHitPlayer(currentTotalOfCards);
			}
			return playerHitAndValue;

		} catch (Exception e) {
			System.out.println("Please enter a valid response.");
		}
		return playerHitAndValue;
	}

	public static void playerHand(String newCard, int handSize) {
		boolean isFirstCard = false;
		int arraySize = 0;
			if (handSize == 1 ){
				arraySize = 2;
			} else {
				arraySize = handSize;
			}
		String[] toStoreNewCards = new String[arraySize];

			if (handSize < 2){
				isFirstCard =true;
			}

		for (int i = 0; i <= arraySize - 1; i++) {
			if (isFirstCard && (i ==0 )) {
				toStoreNewCards[i] = newCard;
				break;
				//breaking otherwise would loop on hand size 1 and not loop on others;
				//places array into new array that is 1 bigger than previous array.
			}else if ( i < arraySize - 1){
				toStoreNewCards[i] = cardsOnTablePlayer[i];

		 	}else {
				//should place the new card in the new array in the last spot.
				toStoreNewCards[i] = newCard;
			}

		}
			cardsOnTablePlayer = toStoreNewCards;

		return;
	}

	public static void dealerHand(String newCard, int handSize) {
		boolean isFirstCard = false;
		int arraySize = 0;
		if (handSize == 1 ){
			arraySize = 2;
		} else {
			arraySize = handSize;
		}
		String[] toStoreNewCards = new String[arraySize];

		if (handSize < 2){
			isFirstCard =true;
		}

		for (int i = 0; i <= arraySize - 1; i++) {
			if (isFirstCard && (i ==0 )) {
				toStoreNewCards[i] = newCard;
				break; //breaking otherwise would loop on hand size 1 and not loop on others;
				//places array into new array that is 1 bigger than previous array.
			}else if ( i < arraySize - 1){
				toStoreNewCards[i] = cardsOnTableDealer[i];

			}else {
				//should place the new card in the new array in the last spot.
				toStoreNewCards[i] = newCard;
			}

		}
		cardsOnTableDealer = toStoreNewCards;

		return;
	}

	public static void showHand(char whosCards){
		char playersCard = 'P';

		String playersHand = String.join(", ",cardsOnTablePlayer);
		String dealersHand = String.join(", ",cardsOnTableDealer);
		if (whosCards == playersCard) {
			System.out.println("************************");
			System.out.println("You have the following cards: " + playersHand);
		} else {
			System.out.println("************************");
			System.out.println("The dealer now has the following cards: " + dealersHand);
		}
	}

	public static boolean isAQuiter(){
		String[] resetCardsInHand = new String[]{null,null};
		int playLimit = 5;
		int defaultHandSize = 0;
		try {
			String playerAnswer = choice.next();
			 if (playerAnswer.equalsIgnoreCase("Y") || playerAnswer.equalsIgnoreCase("Yes")){
				 gamesPlayed++;

				 Blackjack.dealerHadAce =false;
				 Blackjack.playerHadAce=false;

				 if (gamesPlayed > playLimit){
					 cardInteractions.buildTheDeck();
					 System.out.println("The deck is getting low, shuffling the deck.");
					 cardInteractions.handSizeDealer =defaultHandSize;
					 cardInteractions.handSizePlayer =defaultHandSize;
					 cardsOnTablePlayer = resetCardsInHand;
					 cardsOnTableDealer = resetCardsInHand;
					 Blackjack.main(null);
				 }else {
					 cardInteractions.handSizeDealer =defaultHandSize;
					 cardInteractions.handSizePlayer =defaultHandSize;
					 cardsOnTablePlayer = resetCardsInHand;
					 cardsOnTableDealer = resetCardsInHand;
					 Blackjack.main(null);
				 }
			 } else if ( playerAnswer.equalsIgnoreCase("N") || playerAnswer.equalsIgnoreCase("No")){
				 System.out.println("Thank you for playing, have a great day!");
				 System.exit(0);
			 } else {
				 System.out.println("Please enter a valid response.");
				 isAQuiter();
			 }
		} catch (Exception e) {
			System.out.println("Please enter a valid response.");
		}return true;
	}
}


