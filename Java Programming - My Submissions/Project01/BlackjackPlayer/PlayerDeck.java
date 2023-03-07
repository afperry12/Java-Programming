package BlackjackPlayer;
import java.util.HashMap;
public class PlayerDeck {
    HashMap<Integer, PlayerCard> allMyCards = new HashMap<Integer, PlayerCard>();
    public void initialCardsInDeck(PlayerCard[] cards) {
        for (int x = 0;x<cards.length;x++) {
            allMyCards.put(x, cards[x]);
        }
    }
    public void addCards(PlayerCard[] cards) {
        for (int x = 0;x<cards.length;x++) {
            allMyCards.put(allMyCards.size()-1, cards[x]);
        }
    }

    int highestValueOfHand = 0;
    HashMap<Integer, Integer> possibleHandValues = new HashMap<Integer, Integer>();

    int checkHighestValue() {
        highestValueOfHand = 0;
        // Iterate through each card
            allMyCards.forEach((integer, card) -> {
                // If ace, create two new possible hands derived from original of +1 and +11
                if (card.blackjackValue == 11) {
                    // If something has already been added to hashmap
                    if (possibleHandValues.size() >= 1) {
                        HashMap<Integer, Integer> tmp = new HashMap<>();
                        possibleHandValues.forEach((integers, totalValue) -> {
                            tmp.put(tmp.size(), totalValue + 1);
                            tmp.put(tmp.size(), totalValue + 11);
                        });
                        possibleHandValues = tmp;
                    } else {
                        possibleHandValues.put(0, 1);
                        possibleHandValues.put(1, 11);
                    }
                } 
                // If card is not an ace do basically the same thing but only add value to each existing possible hand
                // do not create new possible hands since there are none
                else {
                    if (possibleHandValues.size() >= 1) {
                        HashMap<Integer, Integer> tmp = new HashMap<>();
                        possibleHandValues.forEach((integers, totalValue) -> {
                            tmp.put(integers, totalValue + card.blackjackValue);
                        });
                        possibleHandValues = tmp;
                    } else {
                        possibleHandValues.put(0, card.blackjackValue);
                    }
                }
            });
            // Otherwise, return the highest possible value of hand and 0 if bust
        possibleHandValues.forEach((integer, totalValue) -> {
            if (totalValue > highestValueOfHand && totalValue <= 21) {
                highestValueOfHand = totalValue;
            }
        });
        // reset all possible hands
        possibleHandValues = new HashMap<Integer, Integer>();
        return highestValueOfHand;
    }
}
