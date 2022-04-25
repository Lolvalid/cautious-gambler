package com.techelevator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class dealerLogic {
    final static int BLACKJACK_BABY = 21;
    public static void main(String[] args) {

    }
   //sending information to hitDeck method so dealer can draw.
    public static int dealerHit( int dealerTotal) {
        char dealerCard = 'D';
        dealerTotal += Blackjack.hitDeck(dealerTotal,dealerCard);
        return dealerTotal;
    }
    public static int playBlackjack(boolean dealerAce, int dealerTotal, int playerTotal) {

        int dealerMustStay = 17;
        //setting reduction of total so the value for a soft ace is used.
        int softAce = 10;

        //dealer must hit up to 17, if dealer has an ace, will hit passed 17 this is called a soft 17.
        if (dealerAce) {
            dealerMustStay = 18;

        }

        do {
            dealerTotal = dealerHit(dealerTotal);

            if (dealerTotal > BLACKJACK_BABY && dealerAce) {
                    dealerTotal -= softAce;
            } else if (dealerTotal > BLACKJACK_BABY){
                break;
            }
            }
            while ((dealerTotal < playerTotal) && (dealerTotal < dealerMustStay)) ;

            return dealerTotal;

      }
}