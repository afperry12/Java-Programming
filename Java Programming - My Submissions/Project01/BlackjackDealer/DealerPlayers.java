package BlackjackDealer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

public class DealerPlayers extends Thread {
    // Declare variables important to each player
    private Socket socket;
    public int balance = 500;
    public HashMap<Integer, DealerDeck> currentHands = new HashMap<Integer, DealerDeck>();
    HashSet<Integer> totalHandValue = new HashSet<>();
    boolean finishedWithCurrentHand = false;
    HashMap<Integer, Integer> currentBets = new HashMap<Integer, Integer>();
    int handsPlayedCount = 0;
    // Remember socket for disconnecting later
    public DealerPlayers(Socket socket){
        this.socket = socket;
    }
    // Request a player's username using login command
    public void requestUsername() {
        try{
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("login");
        // System.out.println("login");
        System.out.println(dis.readUTF());
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // Request a player's bet
    public boolean requestBet(DealerDeck deck) {
        currentBets = new HashMap<>();
        currentHands = new HashMap<Integer, DealerDeck>();
        handsPlayedCount++;
        String stringOfCardsPlayed = "";
        int bet = 0;
        // Send all cards previously played by accessing the played cards hashmap in the dealer's deck of cards
        // this hashmap has a card added to it everytime a card is dealt from a deck
        for(int x = 0;x<deck.playedCards.size();x++){
            stringOfCardsPlayed+=String.valueOf(deck.playedCards.get(x).cardValue+deck.playedCards.get(x).suitValue+":");
        }
        try{
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("bet:"+balance+":all:"+stringOfCardsPlayed);
            // System.out.println("bet:"+balance+":all:"+stringOfCardsPlayed);
            // Read response to bet command
            String readValue = dis.readUTF();
            // System.out.println(readValue);
            bet = Integer.parseInt(readValue.split(":")[1]);
            }catch(Exception e){
                System.out.println(e);
                return false;
            }
        if(bet>=1&&bet<=balance) {
            // If bet is allowed, subtract from current balance and add bet to hashmap to possibly use
            // for returning or split or double later
            // System.out.println(balance);
        System.out.println("start");
        System.out.println(balance);
        balance-=bet;
        System.out.println(balance);
        System.out.println("end");
        System.out.println(bet);
        currentBets.put(0,bet);
        currentBets.forEach((integ, bets) -> {
            System.out.println(bets);
        });
        return true;
        } else {
        return false;
        }
    }

    // Request a player's move for each of their current hands
    public String requestPlay(String dealerUpCard, DealerDeck deck){
        try{
            HashMap<Integer,String> hands = new HashMap<>();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            // Add each card in each of a player's hands to hashmap to send to player
            // int length = 0;
            // if(currentHands.size()==0){
            //     length = 1;
            // } else {
            //     length = currentHands.size();
            // }

            // for(int x = 0;x<currentHands.size();x++) {
            //     for(int y = 0;y<currentHands.get(x).allMyCards.size();y++) {
            //         if(hands.containsKey(x)){
            //             String cardsInHand = hands.get(x).toString();
            //             cardsInHand+=String.valueOf(currentHands.get(x).allMyCards.get(y).getCardAndSuit())+":";
            //             hands.put(x, cardsInHand);
            //         } else{
            //             hands.put(x, String.valueOf(currentHands.get(x).allMyCards.get(y).getCardAndSuit())+":");
            //         }
            //     }
            // }

            // Send info to player for each hand they have
            int size = currentHands.size();
            for(int x = 0;x<size;x++) {
                if(currentHands.get(x).checkHighestValue()!=0&&currentHands.get(x).badInput!=true){
                    // if(currentHands.get(x).allMyCards.size()==1){
                    //     DealerCard cardDealt = deck.deal(1)[0];
                    //     currentHands.get(x).add(cardDealt);
                    // }
                for(int y = 0;y<currentHands.get(x).allMyCards.size();y++) {
                    if(hands.containsKey(x)){
                        String cardsInHand = hands.get(x).toString();
                        cardsInHand+=String.valueOf(currentHands.get(x).allMyCards.get(y).getCardAndSuit())+":";
                        hands.put(x, cardsInHand);
                    } else{
                        hands.put(x, String.valueOf(currentHands.get(x).allMyCards.get(y).getCardAndSuit())+":");
                    }
                }
                if(hands.get(x)==null){
                    // dos.writeUTF("play:dealer:"+dealerUpCard+":you:");
                }  else {
                    if(Dealer.trainPlayer){
                    dos.writeUTF("play:dealer:"+dealerUpCard+":you:"+x+":"+hands.get(x));
                    } else {
                        dos.writeUTF("play:dealer:"+dealerUpCard+":you:"+hands.get(x));
                    }
                }
                // System.out.println("play:dealer:"+dealerUpCard+":you:"+hands.get(x));
                String response = dis.readUTF();
                // System.out.println(response);
                // Parse response for possible responses and do responses if allowed
                if(response.equals("hit")){
                    DealerCard cardDealt = deck.deal(1)[0];
                    currentHands.get(x).add(cardDealt);
                } else if(response.equals("stand")) {
                    finishedWithCurrentHand = true;
                } else if(response.equals("double")&&currentBets.get(x)<=balance&&currentHands.get(0).allMyCards.size()==2){
                    currentHands.forEach((integer,dealerDeck) -> {
                        for (int y=0;y<currentHands.get(integer).allMyCards.size();y++) {
                            // System.out.println(currentHands.get(integer).allMyCards.get(y).getCardAndSuit());
                        }
                    });
                    // System.out.println("start1");
                    // System.out.println(balance);
                    balance-=currentBets.get(x);
                    // System.out.println(balance);
                    // System.out.println("end1");
                    currentBets.put(x,currentBets.get(x)*2);
                    // currentBets.forEach((integ, bets) -> {
                    //     System.out.println(bets);
                    // });
                    DealerCard cardDealt = deck.deal(1)[0];
                    currentHands.get(x).add(cardDealt);
                    finishedWithCurrentHand = true;
                } else if(response.equals("split")&&currentHands.get(x).allMyCards.size()==2){
                    boolean areTheSame = currentHands.get(x).cardsSameValue(currentHands.get(x).allMyCards.get(0),currentHands.get(x).allMyCards.get(1));
                    if(areTheSame&&currentBets.get(x)<=balance){
                    //     System.out.println("start2");
                    // System.out.println(balance);
                    balance-=currentBets.get(x);
                    // System.out.println(balance);
                    // System.out.println("end2");
                        currentBets.put(currentBets.size(), currentBets.get(x));
                        currentBets.forEach((integ, bets) -> {
                            System.out.println(bets);
                        });
                        DealerDeck newdeck = new DealerDeck();
                        newdeck.add(currentHands.get(x).allMyCards.get(1));
                        DealerCard cardDealt2 = deck.deal(1)[0];
                        newdeck.add(cardDealt2);
                        currentHands.put(currentHands.size(), newdeck);
                        currentHands.get(x).allMyCards.remove(1);
                        DealerCard cardDealt = deck.deal(1)[0];
                        currentHands.get(x).add(cardDealt);
                    } else{
                        finishedWithCurrentHand=true;
                        currentHands.get(x).badInput = true;
                    }
                    } else if(response.equals("double")||response.equals("split")) {
                        finishedWithCurrentHand=true;
                        currentHands.get(x).badInput = true;
                    }
                }
            }
            String isPlayerFinished = "";
            for(int x = 0; x< currentHands.size();x++) {
                if(currentHands.get(x).checkHighestValue()==0||currentHands.get(x).badInput == true) {
                    isPlayerFinished+="true";
                } else {
                    isPlayerFinished+="false";
                }
            }
            if(isPlayerFinished.contains("false")) {
            } else if(isPlayerFinished.contains("true")) {
                finishedWithCurrentHand=true;
            }
            }catch(Exception e){
                System.out.println(e);
                return "ERROR: "+e;
            }
        return "";
    }

    // Send status of hand(s) to player
    public void SendStatus(int dealerHighestValueOfHand, DealerInfo dInfo) {
        try{
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        for(int x = 0;x<currentHands.size();x++) {
            if(currentHands.get(x).badInput==true) {
                dos.writeUTF("status:lose");
            } else{
            if(currentHands.get(x).checkHighestValue()==21&&currentHands.get(x).allMyCards.size()==2) {
                if(dealerHighestValueOfHand==21&&dInfo.dealerHand.allMyCards.size()==2){
                    // Blackjack tie
                    dos.writeUTF("status:push:dealer:21:you:21");
                    // System.out.println("start3");
                    // System.out.println(balance);
                    balance+=currentBets.get(x);
                    // System.out.println(balance);
                    // System.out.println("end3");
                    
                    // System.out.println("status:push:dealer:21:you:21");
                    // System.out.println(handsPlayedCount);
                } else{
                    // Blackjack win for player
                    dos.writeUTF("status:win:you:blackjack");
                    // System.out.println("start4");
                    // System.out.println(balance);
                    // System.out.println(currentBets.get(x));
                    balance+=(currentBets.get(x)*1.5)+currentBets.get(x);
                    // System.out.println(balance);
                    // System.out.println("end4");
                    
                    // System.out.println("status:win:you:blackjack");
                    // System.out.println(handsPlayedCount);
                }
            }else
            if(currentHands.get(x).checkHighestValue()==dealerHighestValueOfHand){
                if(currentHands.get(x).checkHighestValue()==0){
                    // Bust tie
                    
                    // System.out.println(dealerHighestValueOfHand);
                    // System.out.println(currentHands.get(x).highestValueOfHand);
                    dos.writeUTF("status:push:dealer:bust:you:bust");
                    // System.out.println("start5");
                    // System.out.println(balance);
                    balance+=currentBets.get(x);
                    // System.out.println(balance);
                    // System.out.println("end5");
                    // System.out.println("status:push:dealer:bust:you:bust");
                    // System.out.println(handsPlayedCount);
                } else {
                    // Regular tie
                    dos.writeUTF("status:push:dealer:"+dealerHighestValueOfHand+":you:"+currentHands.get(x).checkHighestValue());
                    // System.out.println("start6");
                    // System.out.println(balance);
                    balance+=currentBets.get(x);
                    // System.out.println(balance);
                    // System.out.println("end6");
                    // System.out.println("status:push:dealer:"+dealerHighestValueOfHand+":you:"+currentHands.get(x).checkHighestValue());
                    // System.out.println(handsPlayedCount);
                }
            }else
            if(currentHands.get(x).checkHighestValue()>dealerHighestValueOfHand){
                // Player win
                dos.writeUTF("status:win:dealer:"+dealerHighestValueOfHand+":you:"+currentHands.get(x).checkHighestValue());
                currentBets.forEach((integ, bets) -> {
                    // System.out.println(bets);
                });
                System.out.println("start7");
                    int f = balance;
                    // System.out.println(balance);
                    // System.out.println(currentBets.get(x));
                    // System.out.println(currentBets.get(x)*2);
                    int test = currentBets.get(x)*2;
                    // System.out.println(test);
                    // System.out.println(f + test);
                    balance=balance + (currentBets.get(x)*2);
                    // System.out.println(balance);
                    // System.out.println("end7");
                // System.out.println("status:win:dealer:"+dealerHighestValueOfHand+":you:"+currentHands.get(x).checkHighestValue());
                // System.out.println(handsPlayedCount);
            } else 
            if(currentHands.get(x).checkHighestValue()<dealerHighestValueOfHand) {
                // Dealer win
                dos.writeUTF("status:lose:dealer:"+dealerHighestValueOfHand+":you:"+currentHands.get(x).checkHighestValue());
                // System.out.println("status:lose:dealer:"+dealerHighestValueOfHand+":you:"+currentHands.get(x).checkHighestValue());
                // System.out.println(handsPlayedCount);
            }
        }
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    }

    // Remove player's socket from game
    public void disconnect(String message) {
        try{
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("Hands played: "+handsPlayedCount);
        dos.writeUTF("done:"+message);
        System.out.println("done:"+message);
        socket.close();
        } catch(Exception e) {

        }
    }
}
