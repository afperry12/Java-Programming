package BlackjackDealer;
public class DealerCard {
    int cardValue = 0;
    int blackjackValue = 0;
    String suitValue = "";

    public String getCardAndSuit(){
        // Return card and suit value as string
        return String.valueOf(cardValue+suitValue);
    }

    public int getCardValue() {
        return cardValue;
    }

    public void cardString(String cardAndSuit) {
        cardAndSuit = cardAndSuit.toUpperCase();
        // Remove suit from entered value to only have card value, then convert to
        // numeric number associated if face card
        String cardNumber = cardAndSuit.replace(String.valueOf(cardAndSuit.charAt((cardAndSuit.length() - 1))), "");
        if (cardNumber.toUpperCase().equals("J")) {
            cardValue = 11;
            blackjackValue=10;
            cardAndSuit = cardAndSuit.replaceAll("J", "");
        } else if (cardNumber.toUpperCase().equals("Q")) {
            cardValue = 12;
            blackjackValue=10;
            cardAndSuit = cardAndSuit.replaceAll("Q", "");
        } else if (cardNumber.toUpperCase().equals("K")) {
            cardValue = 13;
            blackjackValue=10;
            cardAndSuit = cardAndSuit.replaceAll("K", "");
        } else if (cardNumber.toUpperCase().equals("A")) {
            cardValue = 14;
            blackjackValue=11;
            cardAndSuit = cardAndSuit.replaceAll("A", "");
        } else if (Integer.valueOf(cardAndSuit.replaceAll("[\\D]", "")) > 1
                && Integer.valueOf(cardAndSuit.replaceAll("[\\D]", "")) < 11) {
            cardValue = Integer.valueOf(cardAndSuit.replaceAll("[\\D]", ""));
            blackjackValue = Integer.valueOf(cardAndSuit.replaceAll("[\\D]", ""));
        } else if (Integer.valueOf(cardAndSuit.replaceAll("[\\D]", "")) > 10
        && Integer.valueOf(cardAndSuit.replaceAll("[\\D]", "")) < 14) {
            cardValue = Integer.valueOf(cardAndSuit.replaceAll("[\\D]", ""));
            blackjackValue = 10;
        } else if (Integer.valueOf(cardAndSuit.replaceAll("[\\D]", "")) == 14) {
            cardValue = Integer.valueOf(cardAndSuit.replaceAll("[\\D]", ""));
            blackjackValue = 11;
        }else {
            cardValue = 0;
            suitValue = "ERROR";
        }
        // Get suit value by replacing any leftover numbers from the card value with
        // nothing
        String suit = cardAndSuit.replaceAll("\\d", "");
        suitValue = suit;
    }
    public void cardString(int card,String suiString) {
        cardString(card+suiString);
    }
}
