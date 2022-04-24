package com.techelevator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Blackjack {
	static boolean playerHadAce;
	static boolean dealerHadAce;

	public static void main(String[] args) {

		final int BLACKJACK_BABY = 21;
		boolean playerBusted = false;
		int playerTotal = 0;
		int[] playerChoseStay = new int[]{0, 8};
		int[] playerChoosingHitStayValue = new int[2];
		int dealerTotal = 0;
		char dealerCard = 'D';
		char playerCard = 'P';

		//Drawing first two cards for player and dealer card.
		dealerTotal += hitDeck(dealerTotal,dealerCard);
		printTotalDealer(dealerTotal);

		playerTotal += hitDeck(playerTotal,playerCard);
		playerTotal += hitDeck(playerTotal,playerCard);
		printTotalPlayer(playerTotal);

		while (playerChoosingHitStayValue != playerChoseStay) {
			System.out.println("[H]it or [S]tay?");

			playerChoosingHitStayValue = playerChoices.stayOrHitPlayer(playerTotal);
			if (playerChoosingHitStayValue[1] == playerChoseStay[1]) {
				System.out.println("You have chosen to stay, the dealer will now draw");
				break;
			}

			playerTotal += playerChoosingHitStayValue[0];

			if (playerTotal > BLACKJACK_BABY && !playerHadAce) {
				playerBusted = true;
			} else if (playerTotal > BLACKJACK_BABY && playerHadAce){
				playerTotal+=10; //difference of ace as 1 and 11
				playerHadAce = false;// ace was converted, removing safety unless another ace pops up.
			}
			printTotalPlayer(playerTotal);

			if (playerBusted) {
				System.out.println("You have busted!");
				break;
			}
		}
			System.out.println("Shall we play again? [Y]es or [N]o");
			playerChoices.isAQuiter();


	}


	public static int hitDeck(int currentTotalOfCards, char whosCard) {
		char playersCard = 'P';
		char dealersCard = 'D';
		final int BLACKJACK_BABY = 21;
		int playerTotal = 0;
		int dealerTotal = 0;
		int[][] theDeck = cardInteractions.buildTheDeck();
		int[] cardValue = new int[1];
		int cardDrawn = 0;

		if (whosCard == playersCard) {
			playerTotal =+ currentTotalOfCards;
			cardValue = cardInteractions.hitTheDeck(theDeck, playersCard);
		} else if (whosCard == dealersCard) {
			dealerTotal =+ currentTotalOfCards;
			cardValue = cardInteractions.hitTheDeck(theDeck, dealersCard);
		}
		if(cardValue[1] == 1 && whosCard == playersCard){
			playerHadAce = true;
		} else if(cardValue[1] == 1){
			dealerHadAce = true;
		}




		if(whosCard == playersCard) {
			playerTotal = cardValue[0];
			return playerTotal;
		} else {
			dealerTotal = cardValue[0];
		}return  dealerTotal;

	}

	public static void printTotalPlayer(int totalFromMain){
		System.out.println("************************");
		System.out.println("Your total is: " + totalFromMain + ".");
		System.out.println("************************");
	}

	public static int totalHandSize(int numberOfCards){
		return numberOfCards++;

		}
	public static void printTotalDealer(int totalFromMain){
		System.out.println("************************");
		System.out.println("The dealer's total is: " + totalFromMain + ".");
		System.out.println("************************");
		}
	}





