package BlackjackDealer;
import java.net.Socket;
import java.util.HashSet;
import java.net.ServerSocket;

public class Dealer {
    public static boolean gameRunning = true;
    public static int deckCount;
    // public static boolean playersAllFinished = false;
    public static String trueOrFalse;
    public static boolean trainPlayer = true;

    public static void main(String[] args) {
        // Take Input Parameters
        int portNumber = Integer.parseInt(args[0]);
        int playerCount = Integer.parseInt(args[1]);
        deckCount = Integer.parseInt(args[2]);
        try {
            // Connect to socket
            ServerSocket serverSocket = new ServerSocket(portNumber);
            // Create HashSet of Players
            HashSet<DealerPlayers> allPlayers = new HashSet<DealerPlayers>();
            // Keep accepting players until player size is hit
            for (;;) {
                Socket clientSocket = serverSocket.accept();
                DealerPlayers player = new DealerPlayers(clientSocket);
                allPlayers.add(player);
                if (allPlayers.size() == playerCount) {
                    break;
                }
            }
            // Request all usernames and remove if invalid response
            allPlayers.forEach((player) -> {
                try {
                    player.requestUsername();
                } catch (Exception e) {
                    allPlayers.remove(player);
                    player.disconnect("Provided wrong response to username");
                }
            });
            // Create dealer information and deck 
            DealerInfo dInfo = new DealerInfo();
            DealerDeck mainDeck = new DealerDeck();
            mainDeck.create(deckCount);
            mainDeck.shuffle();
            // While the game is running and at least one player remains, keep starting new hands
            while (gameRunning) {
                // Request the player to provide a bet for the hand and disconnect if the bet/response is invalid
                    allPlayers.forEach((player) -> {
                        boolean responseCorrect = player.requestBet(mainDeck);
                        if (!responseCorrect) {
                            allPlayers.remove(player);
                            player.disconnect("Provided wrong respone to bet command");
                        }
                    });
                    // Deal cards to players
                        allPlayers.forEach((player) -> {
                            DealerDeck playerHand = new DealerDeck();
                            player.currentHands.put(0, playerHand);
                            DealerCard card1 = mainDeck.deal(1)[0];
                            DealerCard card2 = mainDeck.deal(1)[0];
                            player.currentHands.get(0).allMyCards.put(0, card1);
                            player.currentHands.get(0).allMyCards.put(1, card2);
                        });
                        // Deal cards to dealer
                    DealerCard card1 = mainDeck.deal(1)[0];
                    DealerCard card2 = mainDeck.deal(1)[0];
                    dInfo.dealerHand = new DealerDeck();
                    dInfo.dealerHand.allMyCards.put(0, card1);
                    dInfo.dealerHand.allMyCards.put(1, card2);
                    // While any player is not finished with the hand, keep requesting their next play
                    while(true) {
                        trueOrFalse= "";
                        allPlayers.forEach((player) -> {
                            if(player.finishedWithCurrentHand==false){
                            String response = player.requestPlay(dInfo.dealerHand.allMyCards.get(0).getCardAndSuit(), mainDeck);
                            if(response.toUpperCase().contains("ERROR")){
                                allPlayers.remove(player);
                                player.disconnect(response);
                            }
                            }
                        });
                        // If all players are done with hand then break
                        allPlayers.forEach((player) -> {
                            trueOrFalse+=String.valueOf(player.finishedWithCurrentHand);
                            // System.out.println(trueOrFalse);
                        });
                        if(trueOrFalse.contains("false")) {
                        } else if (trueOrFalse.contains("true")) {
                            // Reset values for next hand
                            trueOrFalse="";
                            allPlayers.forEach((player) -> {
                                player.finishedWithCurrentHand=false;
                            });
                            break;
                        }
                    }
                    // Keep dealing cards while dealer hand is <17 and stop if >17 or bust
                    while(dInfo.dealerHand.checkHighestValue()<17){
                        if(dInfo.dealerHand.checkHighestValue()==0){
                            break;
                        } else {
                            DealerCard card = mainDeck.deal(1)[0];
                            dInfo.dealerHand.add(card);
                        }
                    }
                    // Send game status to player
                    allPlayers.forEach((player) -> {
                        player.SendStatus(dInfo.dealerHand.checkHighestValue(),dInfo);
                    });
                    // Remove players who lost all their money
                    allPlayers.forEach((player)->{
                        System.out.println("Check money start");
                        System.out.println(player.balance);
                        System.out.println("Check money end");
                        if(player.balance<=0){
                            allPlayers.remove(player);
                            player.disconnect("Balance empty");
                        }
                    });
                    // End game if no players left
                    if(allPlayers.isEmpty()){
                        break;
                    }
            }
            serverSocket.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
