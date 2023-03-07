package BlackjackDealer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DealerDeck {
    boolean badInput = false;
    HashMap<Integer, DealerCard> allMyCards = new HashMap<Integer, DealerCard>();
    HashMap<Integer, DealerCard> playedCards = new HashMap<Integer, DealerCard>();
    int highestValueOfHand = 0;

    // Check if two cards have the same value
    boolean cardsSameValue(DealerCard card1, DealerCard card2) {
        return (card1.getCardValue() == card2.getCardValue());
    }

    HashMap<Integer, Integer> possibleHandValues = new HashMap<Integer, Integer>();

    // Check for the highest possible value of hand equal to or below 21
    // Still need to account for if dealer has a hand that autoforces them to stop betting(Ace and 6 for example)
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

    void add(DealerCard card) {
        // add card to deck
        allMyCards.put(allMyCards.size(), card);
    }

    HashMap<Integer, DealerCard> create(int decks) {
        // Create hashmaps for adding all cards at beginning and adding played cards as cards are dealt throughout game
        allMyCards = new HashMap<Integer, DealerCard>();
        playedCards = new HashMap<Integer, DealerCard>();
        int d = 0;
        int cardNumberAdded = 0;
        // Add cards to shoe given number of decks
        while (d < decks) {
            for (int c = 2; c < 15; c++) {
                for (int s = 1; s < 5; s++) {
                    DealerCard card = new DealerCard();
                    if (s == 1) {
                        card.cardString(c, String.valueOf("D"));
                        allMyCards.put(cardNumberAdded, card);
                        cardNumberAdded++;
                    } else if (s == 2) {
                        card.cardString(c, String.valueOf("S"));
                        allMyCards.put(cardNumberAdded, card);
                        cardNumberAdded++;
                    } else if (s == 3) {
                        card.cardString(c, String.valueOf("C"));
                        allMyCards.put(cardNumberAdded, card);
                        cardNumberAdded++;
                    } else if (s == 4) {
                        card.cardString(c, String.valueOf("H"));
                        allMyCards.put(cardNumberAdded, card);
                        cardNumberAdded++;
                    }
                }
            }
            d++;
        }
        return allMyCards;
    }

    // Deal a certain number of cards
    DealerCard[] deal(int numberOfCardsToDeal) {
        DealerCard[] dealtCards = new DealerCard[numberOfCardsToDeal];
        // If more cards are meant to be dealt than left in hand, deal those remaining cards,
        // create new hand and shuffle it, then keep dealing until enough cards have been dealt
        if (numberOfCardsToDeal > allMyCards.size()) {
            int originalSize = allMyCards.size();
            for (int x = 0; x < originalSize; x++) {
                DealerCard card = allMyCards.get(allMyCards.size() - 1);
                dealtCards[x] = card;
                allMyCards.remove(allMyCards.size() - 1);
                playedCards.put(playedCards.size(), card);
            }
            this.create(Dealer.deckCount);
            this.shuffle();
            for (int x = 0; x < numberOfCardsToDeal - originalSize; x++) {
                DealerCard card = allMyCards.get(allMyCards.size() - 1);
                dealtCards[x] = card;
                allMyCards.remove(allMyCards.size() - 1);
                playedCards.put(playedCards.size(), card);
            }
        } 
        // Otherwise just deal cards
        else {
            for (int x = 0; x < numberOfCardsToDeal; x++) {
                DealerCard card = allMyCards.get(allMyCards.size() - 1);
                dealtCards[x] = card;
                allMyCards.remove(allMyCards.size() - 1);
                playedCards.put(playedCards.size(), card);
            }
        }
        return dealtCards;
    }

    // Shuffle cards
    HashMap<Integer, DealerCard> shuffle() {
        List<DealerCard> values = new ArrayList<>(allMyCards.values());
        Collections.shuffle(values);
        allMyCards.forEach((integer, card)->{
            allMyCards.put(integer, (DealerCard) values.get(integer));
        });
        return allMyCards;
    }
}