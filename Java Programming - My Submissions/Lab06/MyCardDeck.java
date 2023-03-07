public class MyCardDeck {
    public static void main(String[] args) {
        // If number of args is 0, then print deck
        if (args.length == 0) {
            Deck deck = new Deck();
            System.out.print(deck.print());
        }
        // If number of args is 1, then use overloading to insert one string and print
        // the card
        if (args.length == 1) {
            Card card = new Card();
            card.Card(args[0]);
            System.out.print(card.print());
        }
        // If number of args is 2 or more, then use overloading method to insert two
        // strings and print all the cards
        if (args.length > 1) {
            Card card = new Card();
            for (int x = 0; x < args.length; x += 2) {
                try {
                card.Card(args[x], args[x + 1]);
                System.out.println(card.print());
                } catch (Exception e) {
                    System.out.println("0ERROR");
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
        cardValue = 0;
        suitValue = "ERROR";
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
        } else if (cardAndSuit.replaceAll("[\\D]", "").length()>0){
            if (Integer.valueOf(cardAndSuit.replaceAll("[\\D]", "")) > 1
                && Integer.valueOf(cardAndSuit.replaceAll("[\\D]", "")) < 15) {

            cardValue = Integer.valueOf(cardAndSuit.replaceAll("[\\D]", ""));
        } }else {
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
        } else if (cardNumber.replaceAll("[\\D]", "").length()>0){
        if (Integer.parseInt(cardNumber) > 1 && Integer.parseInt(cardNumber) < 15) {

            cardValue = Integer.parseInt(cardNumber);
        }} else {
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

    String print() {
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
}