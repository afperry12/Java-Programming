package BlackjackPlayer;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;

public class ParseInputs {
    public static String Parse(String input) {
        // System.out.println(input);
        if(input.equals("login")) {
            // System.out.println("Login called");
            return Login();
        } else {
        String[] splitCommandsByColon = input.split(":");
        if(splitCommandsByColon[0].equals("bet")){
            // System.out.println("Bet called");
            return String.valueOf(Bet(splitCommandsByColon));
        }
        if(splitCommandsByColon[0].equals("play")){
            // System.out.println("Play called");
            return Play(splitCommandsByColon);
        }
        if(splitCommandsByColon[0].equals("status")){
            // System.out.println("Status called");
            Status(splitCommandsByColon);
            return null;
        }
        if(splitCommandsByColon[0].equals("done")){
            // System.out.println("Done called");
            Player.inGame=false;
            return splitCommandsByColon[1];
        }
        }
        return "error";
    }
    private static String Login(){
        return "afperry12:Penguins";
    }
    private static String Bet(String[] betCommandsString) {
        Player.numberOfTimesSplit = new HashMap<Integer,Integer>();
        Player.numberOfTimesDoubled = new HashMap<Integer,Integer>();
        Player.numberOfTimesHit = new HashMap<Integer,Integer>();
        Player.numberOfTimesStand = new HashMap<Integer,Integer>();
        // Maybe do something if the balance returned is different from balance my program thinks
        // int monetaryBalance = Integer.parseInt(betCommandsStrings[1]);
        Player.currentHandIndex = 0;
        Player.totalNumberOfHands = 1;
        Player.bets = new HashMap<Integer, Integer>();
        Player.timesStatusIsCalledInARow = 0;
        Player.allHandsInRound = new HashMap<Integer,HashMap<PlayerDeck,String>>();
        Player.statusOfHands = new HashMap<Integer, String>();
        Player.previouslyPlayedCards = new PlayerDeck();
        PlayerCard[] cards = new PlayerCard[betCommandsString.length-3];
        if(betCommandsString[2].equals("all")&&betCommandsString.length>=4) {
            int y = 0;
            for(int x = 3;x<betCommandsString.length;x++){
                PlayerCard card = new PlayerCard();
                card.cardString(betCommandsString[x]);
                cards[y]=card;
                y++;
            }
            PlayerDeck previousCards = new PlayerDeck();
            previousCards.initialCardsInDeck(cards);
            Player.previouslyPlayedCards = previousCards;
        }
        int bet = GameLogic.Bet();
        Player.bets.put(0, bet);
        return "bet:"+String.valueOf(bet);
    }
    private static String Play(String[] playCommandsString) {
        Player.currentHand = new PlayerDeck();
        PlayerCard dealersCard = new PlayerCard();
        PlayerCard[] cards = new PlayerCard[playCommandsString.length-4];
        boolean addDealersCard = false;
        boolean addPlayersCards = false;
        int y = 0;
        for(int x = 0;x<playCommandsString.length;x++) {
            if(addPlayersCards) {
                if(y==0&&Player.trainPlayer) {
                    Player.currentHandIndex = Integer.parseInt(playCommandsString[x]);
                    y++;
                } else{
                PlayerCard card = new PlayerCard();
                card.cardString(playCommandsString[x]);
                cards[y]=card;
                if(y==1){
                    Player.FirstPlayerCard.put(Player.currentHandIndex,card);
                }
                if(y==2){
                    Player.SecondPlayerCard.put(Player.currentHandIndex,card);
                }
                y++;
                }
            }
            if(playCommandsString[x].equals("you")){
                addPlayersCards=true;
            } 
            if(addDealersCard) {
                dealersCard.cardString(playCommandsString[x]);
                addDealersCard=false;
            }
            if(playCommandsString[x].equals("dealer")){
                addDealersCard=true;
            } 
        
        }
        
        // Add cards
        // for (int x =0;x<cards.length;x++) {
        //     System.out.println(cards[x].blackjackValue);
        // }
        Player.dealersUpCard = dealersCard;
        PlayerDeck playerHand = new PlayerDeck();
        playerHand.initialCardsInDeck(cards);
        PlayerDeck currentCards = new PlayerDeck();
        currentCards.initialCardsInDeck(cards);
        Player.currentHand = currentCards;
        for(int x =0;x<Player.currentHand.allMyCards.size();x++) {
            // System.out.println(Player.currentHand.allMyCards.get(x).getBlackjackValue());
        }
        // Play and get best move response
        String response = GameLogic.Play();
        if(response.equals("double")){
            Player.bets.put(Player.currentHandIndex, Player.bets.get(Player.currentHandIndex)*2);
        }
        if(response.equals("split")) {
            Player.bets.put(Player.bets.size(), Player.bets.get(Player.currentHandIndex));
            Player.totalNumberOfHands++;
        }
        // System.out.println(response);
        // Save all data HashMap <Integer,HashMap<Deck,String>>
        HashMap<PlayerDeck, String> hashMap = new HashMap<PlayerDeck, String>();
        hashMap.put(playerHand, response);
        try{
            int size = Player.allHandsInRound.get(Player.currentHandIndex).values().size();
            for(int x =0;x<size;x++){
                hashMap.put((PlayerDeck)Player.allHandsInRound.get(Player.currentHandIndex).keySet().toArray()[x], (String)Player.allHandsInRound.get(Player.currentHandIndex).values().toArray()[x]);
            }

            Player.allHandsInRound.put(Player.currentHandIndex, hashMap);
        } catch (Exception e ) {
            Player.allHandsInRound.put(Player.currentHandIndex, hashMap);
        }
        // if(Player.allHandsInRound.get(Player.currentHandIndex)==null){
        // Player.allHandsInRound.put(Player.currentHandIndex, hashMap);
        // } else{
        //     int size = Player.allHandsInRound.get(Player.currentHandIndex).values().size();
        //     for(int x =0;x<size;x++){
        //         hashMap.put((PlayerDeck)Player.allHandsInRound.get(Player.currentHandIndex).keySet().toArray()[x], (String)Player.allHandsInRound.get(Player.currentHandIndex).values().toArray()[x]);
        //     }

        //     Player.allHandsInRound.put(Player.currentHandIndex, hashMap);
        // }
        return response;
    }
    private static void Status(String[] status) {
        if(status[1].equals("lose")){
            Player.statusOfHands.put(Player.timesStatusIsCalledInARow, "lose");
        } else if(status[1].equals("push")) {
            Player.statusOfHands.put(Player.timesStatusIsCalledInARow, "push");
        } else if(status[1].equals("win")) {
            if(status[3].equals("blackjack")) {
                Player.statusOfHands.put(Player.timesStatusIsCalledInARow, "blackjackwin");
                // could make blackjack win in future
            } else {
                Player.statusOfHands.put(Player.timesStatusIsCalledInARow, "win");
            }
        }
        Player.timesStatusIsCalledInARow++;
        if((Player.timesStatusIsCalledInARow==Player.totalNumberOfHands)&&Player.trainPlayer) {
            // Store all data from all hands etc
            try {
                File file = new File("C:\\Users\\arthu\\Desktop\\CS112-afperry12\\Project01\\BlackjackData.csv");
                // BufferedReader reader = new BufferedReader(new FileReader(file));
                // int lines = 0;
                // while (reader.readLine() != null) lines++;
                // reader.close();
                BufferedWriter output = new BufferedWriter(new FileWriter(file, true));

                double numberOf2CardsDividedByNumberOfDecks = 0;
                double numberOf3CardsDividedByNumberOfDecks = 0;
                double numberOf4CardsDividedByNumberOfDecks = 0;
                double numberOf5CardsDividedByNumberOfDecks = 0;
                double numberOf6CardsDividedByNumberOfDecks = 0;
                double numberOf7CardsDividedByNumberOfDecks = 0;
                double numberOf8CardsDividedByNumberOfDecks = 0;
                double numberOf9CardsDividedByNumberOfDecks = 0;
                double numberOf10CardsDividedByNumberOfDecks = 0;
                double numberOf11CardsDividedByNumberOfDecks = 0;
                double numberOfDecksInShoe = 7;

                int valueOfDealerCard = 0;
                int valueOfFirstPlayerCard = 0;
                int valueOfSecondPlayerCard = 0;
                // or
                // int total initial player hand

                int numberOfSplits = 0;
                int numberOfHits = 0;
                int numberOfDoubles = 0;
                int numberOfStands = 0;

                for(int i = 0; i < Player.previouslyPlayedCards.allMyCards.size(); i++) {
                    PlayerCard card = Player.previouslyPlayedCards.allMyCards.get(i);
                    int cardValue = card.getCardValue();
                    if(cardValue==2) {
                        numberOf2CardsDividedByNumberOfDecks++;
                    } else if(cardValue==3) {
                        numberOf3CardsDividedByNumberOfDecks++;
                    } else if (cardValue==4) {
                        numberOf4CardsDividedByNumberOfDecks++;
                    } else if (cardValue==5) {
                        numberOf5CardsDividedByNumberOfDecks++;
                    } else if (cardValue==6) {
                        numberOf6CardsDividedByNumberOfDecks++;
                    } else if (cardValue==7) {
                        numberOf7CardsDividedByNumberOfDecks++;
                    } else if (cardValue==8) { 
                        numberOf8CardsDividedByNumberOfDecks++;
                    } else if (cardValue==9) {
                        numberOf9CardsDividedByNumberOfDecks++;
                    } else if (cardValue==10) {
                        numberOf10CardsDividedByNumberOfDecks++;
                    } else if (cardValue==11) {
                        numberOf11CardsDividedByNumberOfDecks++;
                    }

                }
                valueOfDealerCard = Player.dealersUpCard.getCardValue();

                for(int x = 0; x < Player.timesStatusIsCalledInARow; x++) {
                    // PlayerDeck deckOfPlayerHand = (PlayerDeck) Player.allHandsInRound.get(x).keySet().toArray()[0];
                    valueOfFirstPlayerCard = Player.FirstPlayerCard.get(x).getCardValue();
                    valueOfSecondPlayerCard = Player.SecondPlayerCard.get(x).getCardValue();
                    try{
                    numberOfSplits = Player.numberOfTimesSplit.get(x);
                    } catch(Exception e) {
                    numberOfSplits = 0;
                    }
                    try{
                    numberOfHits = Player.numberOfTimesHit.get(x);
                } catch(Exception e) {
                    numberOfHits = 0;
                }
                try{
                    numberOfDoubles = Player.numberOfTimesDoubled.get(x);
                }catch(Exception e) {
                    numberOfDoubles=0;
                }
                try{
                    numberOfStands = Player.numberOfTimesStand.get(x);
                }catch(Exception e) {
                    numberOfStands=0;
                }
                int finalWinLosePush = 0;
                String result = Player.statusOfHands.get(x);
                // System.out.println(result);
                // if(result.equals("win")) {
                //     finalWinLosePush=1;
                // }
                // if(result.equals("lose")) {
                //     finalWinLosePush=0;
                // }
                // if(result.equals("push")) {
                //     finalWinLosePush=2;
                // }


                output.write(numberOf2CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf3CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf4CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf5CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf6CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf7CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf8CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf9CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf10CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf11CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+valueOfDealerCard+","+valueOfFirstPlayerCard+","+valueOfSecondPlayerCard+","+numberOfSplits+","+numberOfHits+","+numberOfDoubles+","+numberOfStands+","+result);
                output.newLine();
            }
            output.close();
            } catch (Exception e) {
                System.out.println("here!");
                System.out.println(e);
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace(System.out);
            }
        }
    }

}