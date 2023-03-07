import java.util.ArrayList;
import java.util.List;

public class MyCardDeck {
    public static void main(String[] args) {
        // If number of args is 0, then print deck
        if (args.length == 0) {
            Deck deck = new Deck();
            System.out.print(deck.create());
        }
        // If there are one or more arguments, do the following
        if (args.length >= 1) {
            // Create new deck and build 52 cards in order within it
            Deck deck = new Deck();
            deck.create();
            // Splits and goes through each argument
            for (int x = 0; x < args.length; x++) {
                // If the argument says to shuffle, then create new deck and shuffle the deck
                if (args[x].toString().toLowerCase().equals("shuffle")) {
                    deck.create();
                    Card[] cards = deck.shuffle();
                    // Print cards after returning them from shuffle method
                    for (int y = 0; y < cards.length; y++) {
                        System.out.print(cards[y].print() + " ");
                    }
                    System.out.println("");

                } 
                // Otherwise, if the value contains an integer, then parse the int and deal that many cards
                else if (args[x].toString().matches(".*\\d.*")) {
                    int numberOfCardsToDeal = Integer.parseInt(args[x]);
                    if (numberOfCardsToDeal > 0 && numberOfCardsToDeal <= 52) {
                        Card[] cards = deck.deal(numberOfCardsToDeal);
                        // If cards is null then print null otherwise print cards
                        if (cards==null) {
                            System.out.println("null");
                        } else {
                        for (int y = 0; y < cards.length; y++) {
                            System.out.print(cards[y].print() + " ");
                        }
                        System.out.println("");
                    }
                    } else {
                        System.out.println("null");
                    }
                }
            }
        }
    }
}

// Create variables and methods associated with cards
class Card {
    int cardValue = 0;
    String suitValue = "";

    // for when an integer is entered along with a suit
    void Card(int cardNumber, String cardSuit) {
        // If in correct range then set card's value
        if (cardNumber > 1 && cardNumber < 15) {
            cardValue = cardNumber;
        } else {
            cardValue = 0;
            suitValue = "ERROR";
        }
        // If a possible suit is provided then set correct suit
        cardSuit = cardSuit.toUpperCase();
        if (cardSuit.equals("S") || cardSuit.equals("D") || cardSuit.equals("H") || cardSuit.equals("C")) {
            suitValue = cardSuit;
        } else {
            cardValue = 0;
            suitValue = "ERROR";
        }

    }

    // Method used if only one string is entered
    void Card(String cardAndSuit) {
        cardAndSuit = cardAndSuit.toUpperCase();
        // Remove suit from entered value to only have card value, then convert to
        // numeric number associated if face card
        String cardNumber = cardAndSuit.replace(String.valueOf(cardAndSuit.charAt((cardAndSuit.length() - 1))), "");
        if (cardNumber.toUpperCase().equals("J")) {
            cardValue = 11;
            cardAndSuit = cardAndSuit.replaceAll("J", "");
        } else if (cardNumber.toUpperCase().equals("Q")) {
            cardValue = 12;
            cardAndSuit = cardAndSuit.replaceAll("Q", "");
        } else if (cardNumber.toUpperCase().equals("K")) {
            cardValue = 13;
            cardAndSuit = cardAndSuit.replaceAll("K", "");
        } else if (cardNumber.toUpperCase().equals("A")) {
            cardValue = 14;
            cardAndSuit = cardAndSuit.replaceAll("A", "");
        } else if (Integer.valueOf(cardAndSuit.replaceAll("[\\D]", "")) > 1
                && Integer.valueOf(cardAndSuit.replaceAll("[\\D]", "")) < 15) {

            cardValue = Integer.valueOf(cardAndSuit.replaceAll("[\\D]", ""));
        } else {
            cardValue = 0;
            suitValue = "ERROR";
        }
        // Get suit value by replacing any leftover numbers from the card value with
        // nothing
        String suit = cardAndSuit.replaceAll("\\d", "");
        suitValue = suit;
    }

    // Method for handling multiple strings
    void Card(String cardNumber, String cardSuit) {
        // Normalize conversions for letters representing values and set card value
        // accordingly
        if (cardNumber.toUpperCase().equals("J")) {
            cardValue = 11;
        } else if (cardNumber.toUpperCase().equals("Q")) {
            cardValue = 12;
        } else if (cardNumber.toUpperCase().equals("K")) {
            cardValue = 13;
        } else if (cardNumber.toUpperCase().equals("A")) {
            cardValue = 14;
        } else if (Integer.parseInt(cardNumber) > 1 && Integer.parseInt(cardNumber) < 15) {

            cardValue = Integer.parseInt(cardNumber);
        } else {
            cardValue = 0;
            suitValue = "ERROR";
        }

        // Set suit value with leftover values
        cardSuit = cardSuit.toUpperCase();
        if (cardSuit.equals("S") || cardSuit.equals("D") || cardSuit.equals("H") || cardSuit.equals("C")) {
            suitValue = cardSuit;
        } else {
            cardValue = 0;
            suitValue = "ERROR";
        }
    }

    // Default constructor that sets values as if inputs were in error
    void Card() {
        cardValue = 0;
        suitValue = "ERROR";
    }

    // Retrieves card value
    int value() {
        return cardValue;
    }

    // Retrieves suit value
    String suit() {
        return suitValue;
    }

    // Method for printing out card's value and suit-denormalizes data to return to
    // easily readable format
    String print() {
        String stringToReturn = "";
        if (cardValue == 14) {
            stringToReturn += "A";
        } else if (cardValue == 13) {
            stringToReturn += "K";
        } else if (cardValue == 12) {
            stringToReturn += "Q";
        } else if (cardValue == 11) {
            stringToReturn += "J";
        } else if (cardValue > 1 && cardValue < 11) {
            stringToReturn += String.valueOf(cardValue);
        } else {
            cardValue = 0;
            suitValue = "ERROR";
        }

        if (suitValue.equals("S") || suitValue.equals("C") || suitValue.equals("H") || suitValue.equals("D")) {
            stringToReturn += suitValue;
        } else {
            cardValue = 0;
            suitValue = "ERROR";
        }
        if (suitValue == "ERROR") {
            stringToReturn = "" + cardValue + suitValue;
        }
        return stringToReturn;
    }
}

// Class that creates a brand new 52 card deck
class Deck {
    Card[] allMyCards = new Card[52];

    String create() {
        // Resize deck back to 52 cards to shuffle
        allMyCards = new Card[52];
        String stringToPrint = "";
        int x = 0;
        // Loop through all possible card values and suits and create a new card at each
        // value
        for (int c = 2; c < 15; c++) {
            for (int s = 1; s < 5; s++) {
                allMyCards[x] = new Card();
                if (s == 1) {
                    allMyCards[x].Card(c, String.valueOf("D"));
                } else if (s == 2) {
                    allMyCards[x].Card(c, String.valueOf("S"));
                } else if (s == 3) {
                    allMyCards[x].Card(c, String.valueOf("C"));
                } else if (s == 4) {
                    allMyCards[x].Card(c, String.valueOf("H"));
                }
                stringToPrint += allMyCards[x].print() + " ";
                x++;
            }
        }
        // Return final string variable after appending each card's values to it
        return stringToPrint;
    }

    Card[] deal(int numberOfCardsToDeal) {
        // No reason to continue if too many cards dealt
        if ((allMyCards.length-numberOfCardsToDeal)<0) {
            return null;
        }
        // Create a new object for only the dealt cards this call
        Card[] cardsDealt = new Card[numberOfCardsToDeal];
        // Create temp variable that points to original object of cards, therefore retaining a default deck
        Card[] tmp = allMyCards;
        // For the number of cards wanted to be dealt, deal them and remove them from the deck (allMyCards)
        for (int y = 0; y < numberOfCardsToDeal; y++) {
            // Deal cards from the top of deck (reverse order) both for style points and because that is 
            // how I remove cards from allMyCards after
            cardsDealt[y] = tmp[allMyCards.length - 1];
            allMyCards = new Card[(allMyCards.length - 1)];
            for (int t = 0; t < allMyCards.length - y; t++) {
                if (t > y) {
                    allMyCards[t] = tmp[t];
                }
            }
        }
        // Ensure that the allMyCards deck is retained if multiple arguments containing numbers are called
        for (int o = 0; o < tmp.length - numberOfCardsToDeal; o++) {
            allMyCards[o] = tmp[o];
        }
        // Finally, return only the cards dealt during this argument call
        return cardsDealt;
    }

    Card[] shuffle() {
        int length = allMyCards.length;
        // Create a temporary variable to save the original deck of cards
        Card[] saveOld = allMyCards;
        for (int x = 0; x < length; x++) {
            // Take a random card between 0 and the max index of allMyCards
            int randomCard = (int) Math.floor(Math.random() * (length - (x)));
            // Set allMyCard's new value at index X to the value of the card at the random index of the same deck of cards
            allMyCards[x] = saveOld[randomCard];
            // Create a temp variable to be able to remove card from saveOld object
            Card[] tmp = saveOld;
            // Create a new object to fill that is the new length we need after using another one of the card values
            saveOld = new Card[(length - (x))];
            // For the length of the cards, keep adding the temporary values to the saveOld object until
            // y hits the value of the previous card. At that point, make saveOld's index one behind tmp so that
            // the accurate length is achieved and the removed card cannot be called again
            for (int y = 0; y < (length - (x)); y++) {
                if (y <= randomCard) {
                    saveOld[y] = tmp[y];
                } else if (y > randomCard) {
                    saveOld[y - 1] = tmp[y];
                }
            }
        }
        // Return the shuffled deck of cards
        return allMyCards;
    }
}