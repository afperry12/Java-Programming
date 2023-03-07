package BlackjackPlayer;

import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {
    public static boolean training = true;

    public static int Bet() {
        if (training == false) {

        }
        return 1;
    }

    public static String Play() {
        if(Player.trainPlayer){
            int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            if(randomNum==1) {
                try{
                    Player.numberOfTimesStand.put(Player.currentHandIndex, Player.numberOfTimesStand.get(Player.currentHandIndex)+1);
                } catch(Exception e){
                Player.numberOfTimesStand.put(Player.currentHandIndex,1);
                }
                return "stand";
            } else if(randomNum==2) {
                try{
                    Player.numberOfTimesHit.put(Player.currentHandIndex, Player.numberOfTimesHit.get(Player.currentHandIndex)+1);
                } catch(Exception e){
                Player.numberOfTimesHit.put(Player.currentHandIndex,1);
                }
                return "hit";
            } else if(randomNum==3) {
                try{
                    Player.numberOfTimesDoubled.put(Player.currentHandIndex, Player.numberOfTimesDoubled.get(Player.currentHandIndex)+1);
                } catch(Exception e){
                Player.numberOfTimesDoubled.put(Player.currentHandIndex,1);
                }
                return "double";
            } else if(randomNum==4) {
                try{
                    Player.numberOfTimesSplit.put(Player.currentHandIndex, Player.numberOfTimesSplit.get(Player.currentHandIndex)+1);
                } catch(Exception e){
                Player.numberOfTimesSplit.put(Player.currentHandIndex,1);
                }
                return "split";
            }
        } else {
            try{
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

                for(int x = 0; x < Player.totalNumberOfHands; x++) {
                    // PlayerDeck deckOfPlayerHand = (PlayerDeck) Player.allHandsInRound.get(x).keySet().toArray()[0];
                    valueOfFirstPlayerCard = Player.FirstPlayerCard.get(x).getCardValue();
                    valueOfSecondPlayerCard = Player.SecondPlayerCard.get(x).getCardValue();
                    try{
                    numberOfSplits = Player.numberOfTimesSplit.get(x);
                    } catch(Exception e) {
                        System.out.println(e);
                        e.printStackTrace(System.out);
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
            }
            Player.AIDOS.writeUTF(numberOf2CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf3CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf4CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf5CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf6CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf7CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf8CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf9CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf10CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf11CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+valueOfDealerCard+","+valueOfFirstPlayerCard+","+valueOfSecondPlayerCard+","+(numberOfSplits+1)+","+numberOfHits+","+numberOfDoubles+","+numberOfStands);
            Player.AIDOS.writeUTF(numberOf2CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf3CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf4CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf5CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf6CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf7CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf8CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf9CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf10CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf11CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+valueOfDealerCard+","+valueOfFirstPlayerCard+","+valueOfSecondPlayerCard+","+numberOfSplits+","+(numberOfHits+1)+","+numberOfDoubles+","+numberOfStands);
            Player.AIDOS.writeUTF(numberOf2CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf3CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf4CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf5CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf6CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf7CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf8CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf9CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf10CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf11CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+valueOfDealerCard+","+valueOfFirstPlayerCard+","+valueOfSecondPlayerCard+","+numberOfSplits+","+numberOfHits+","+(numberOfDoubles+1)+","+numberOfStands);
            Player.AIDOS.writeUTF(numberOf2CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf3CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf4CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf5CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf6CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf7CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf8CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf9CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf10CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+numberOf11CardsDividedByNumberOfDecks/numberOfDecksInShoe+","+valueOfDealerCard+","+valueOfFirstPlayerCard+","+valueOfSecondPlayerCard+","+numberOfSplits+","+numberOfHits+","+numberOfDoubles+","+(numberOfStands+1));
            String response = Player.AIDIS.readUTF();
            if(response.equals("split")) {
                try{
                    Player.numberOfTimesSplit.put(Player.currentHandIndex, Player.numberOfTimesSplit.get(Player.currentHandIndex)+1);
                } catch(Exception e){
                Player.numberOfTimesSplit.put(Player.currentHandIndex,1);
                }
            } else if(response.equals("hit")) {
                try{
                    Player.numberOfTimesHit.put(Player.currentHandIndex, Player.numberOfTimesHit.get(Player.currentHandIndex)+1);
                } catch(Exception e){
                Player.numberOfTimesHit.put(Player.currentHandIndex,1);
                }
            } else if(response.equals("double")) {
                try{
                    Player.numberOfTimesDoubled.put(Player.currentHandIndex, Player.numberOfTimesDoubled.get(Player.currentHandIndex)+1);
                } catch(Exception e){
                Player.numberOfTimesDoubled.put(Player.currentHandIndex,1);
                }
            } else if(response.equals("stand")) {
                try{
                    Player.numberOfTimesStand.put(Player.currentHandIndex, Player.numberOfTimesStand.get(Player.currentHandIndex)+1);
                } catch(Exception e){
                Player.numberOfTimesStand.put(Player.currentHandIndex,1);
                }
            }
            return response;
            } catch(Exception e) {
                System.out.println(e);
                e.printStackTrace(System.out);
                return "stand";
            }
        }
    // else{
    //     int highestCurrentHandValue = Player.currentHand.checkHighestValue();
    //     int dealersUpCardValue =Player.dealersUpCard.blackjackValue;
    //     int playerFirstCardValue = Player.currentHand.allMyCards.get(0).getCardValue();
    //     int playerSecondCardValue = Player.currentHand.allMyCards.get(1).getCardValue();
    //     // System.out.println("HIGHESTCURRENTHANDVALUE");
    //     // System.out.println(highestCurrentHandValue);
    //     if(training==false&&Player.currentHand.allMyCards.size()==2){
    //         if(dealersUpCardValue==11){
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==8&&playerSecondCardValue==8)){
    //                 return "split";
    //             } else if((playerFirstCardValue==10&&playerSecondCardValue==10)||(playerFirstCardValue==9&&playerSecondCardValue==9)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)||(playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==4&&playerSecondCardValue==4)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)) {
    //                 return "hit";
    //             } else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if(highestCurrentHandValue>=17) {
    //                 return "stand";
    //             } else {
    //                 return "hit";
    //             }
    //         } else if(dealersUpCardValue==10) {
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==8&&playerSecondCardValue==8)){
    //                 return "split";
    //             } else if((playerFirstCardValue==10&&playerSecondCardValue==10)||(playerFirstCardValue==9&&playerSecondCardValue==9)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==4&&playerSecondCardValue==4)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)) {
    //                 return "hit";
    //             } else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==7)||(playerFirstCardValue==7&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)) {
    //                 return "hit";
    //             } else if(highestCurrentHandValue>=17) {
    //                 return "stand";
    //             } else if(highestCurrentHandValue==11) {
    //                 return "double";
    //             } else {
    //                 return "hit";
    //             }
    //         } else if(dealersUpCardValue==9) {
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==8&&playerSecondCardValue==8)||(playerFirstCardValue==9&&playerSecondCardValue==9)){
    //                 return "split";
    //             } else if((playerFirstCardValue==10&&playerSecondCardValue==10)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==4&&playerSecondCardValue==4)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)) {
    //                 return "hit";
    //             } else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==7)||(playerFirstCardValue==7&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)) {
    //                 return "hit";
    //             } else if(highestCurrentHandValue>=17) {
    //                 return "stand";
    //             } else if(highestCurrentHandValue==11||highestCurrentHandValue==10) {
    //                 return "double";
    //             } else {
    //                 return "hit";
    //             }
    //         } else if(dealersUpCardValue==8) {
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==8&&playerSecondCardValue==8)||(playerFirstCardValue==9&&playerSecondCardValue==9)){
    //                 return "split";
    //             } else if((playerFirstCardValue==10&&playerSecondCardValue==10)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==4&&playerSecondCardValue==4)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)) {
    //                 return "hit";
    //             } else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)) {
    //                 return "hit";
    //             } else if(highestCurrentHandValue>=17) {
    //                 return "stand";
    //             } else if(highestCurrentHandValue==11||highestCurrentHandValue==10) {
    //                 return "double";
    //             } else {
    //                 return "hit";
    //             }
    //         } else if(dealersUpCardValue==7) {
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==8&&playerSecondCardValue==8)||(playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)){
    //                 return "split";
    //             } else if((playerFirstCardValue==10&&playerSecondCardValue==10)||(playerFirstCardValue==9&&playerSecondCardValue==9)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==4&&playerSecondCardValue==4)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)) {
    //                 return "hit";
    //             } else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)) {
    //                 return "hit";
    //             } else if(highestCurrentHandValue>=17) {
    //                 return "stand";
    //             } else if(highestCurrentHandValue==11||highestCurrentHandValue==10) {
    //                 return "double";
    //             } else {
    //                 return "hit";
    //             }
    //         } else if(dealersUpCardValue==6) {
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==9&&playerSecondCardValue==9)||(playerFirstCardValue==8&&playerSecondCardValue==8)||(playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==4&&playerSecondCardValue==4)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)){
    //                 return "split";
    //             } else if((playerFirstCardValue==10&&playerSecondCardValue==10)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==7)||(playerFirstCardValue==7&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==5)||(playerFirstCardValue==5&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==4)||(playerFirstCardValue==4&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==3)||(playerFirstCardValue==3&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==2)||(playerFirstCardValue==2&&playerSecondCardValue==11)) {
    //                 return "double";
    //             } else if(highestCurrentHandValue>=12) {
    //                 return "stand";
    //             } else if(highestCurrentHandValue>=9) {
    //                 return "double";
    //             } else {
    //                 return "hit";
    //             }
    //         } else if(dealersUpCardValue==5) {
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==9&&playerSecondCardValue==9)||(playerFirstCardValue==8&&playerSecondCardValue==8)||(playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==4&&playerSecondCardValue==4)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)){
    //                 return "split";
    //             } else if((playerFirstCardValue==10&&playerSecondCardValue==10)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==7)||(playerFirstCardValue==7&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==5)||(playerFirstCardValue==5&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==4)||(playerFirstCardValue==4&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==3)||(playerFirstCardValue==3&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==2)||(playerFirstCardValue==2&&playerSecondCardValue==11)) {
    //                 return "double";
    //             } else if(highestCurrentHandValue>=12) {
    //                 return "stand";
    //             } else if(highestCurrentHandValue>=9) {
    //                 return "double";
    //             } else {
    //                 return "hit";
    //             }
    //         } else if(dealersUpCardValue==4) {
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==9&&playerSecondCardValue==9)||(playerFirstCardValue==8&&playerSecondCardValue==8)||(playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)){
    //                 return "split";
    //             } else if((playerFirstCardValue==10&&playerSecondCardValue==10)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==4&&playerSecondCardValue==4)) {
    //                 return "hit";
    //             }
    //             else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==7)||(playerFirstCardValue==7&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==5)||(playerFirstCardValue==5&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==4)||(playerFirstCardValue==4&&playerSecondCardValue==11)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==3)||(playerFirstCardValue==3&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==2)||(playerFirstCardValue==2&&playerSecondCardValue==11)) {
    //                 return "hit";
    //             } else if(highestCurrentHandValue>=12) {
    //                 return "stand";
    //             } else if(highestCurrentHandValue>=9) {
    //                 return "double";
    //             } else {
    //                 return "hit";
    //             }
    //         } else if(dealersUpCardValue==3) {
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==9&&playerSecondCardValue==9)||(playerFirstCardValue==8&&playerSecondCardValue==8)||(playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)){
    //                 return "split";
    //             } else if((playerFirstCardValue==10&&playerSecondCardValue==10)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==4&&playerSecondCardValue==4)) {
    //                 return "hit";
    //             }
    //             else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==7)||(playerFirstCardValue==7&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==3)||(playerFirstCardValue==3&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==2)||(playerFirstCardValue==2&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==5)||(playerFirstCardValue==5&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==4)||(playerFirstCardValue==4&&playerSecondCardValue==11)) {
    //                 return "hit";
    //             } else if(highestCurrentHandValue==12) {
    //                 return "hit";
    //             }
                
    //             else if(highestCurrentHandValue>12) {
    //                 return "stand";
    //             } else if(highestCurrentHandValue>=9) {
    //                 return "double";
    //             } else {
    //                 return "hit";
    //             }
    //         } else if(dealersUpCardValue==2) {
    //             if((playerFirstCardValue==11&&playerSecondCardValue==11)||(playerFirstCardValue==9&&playerSecondCardValue==9)||(playerFirstCardValue==8&&playerSecondCardValue==8)||(playerFirstCardValue==7&&playerSecondCardValue==7)||(playerFirstCardValue==6&&playerSecondCardValue==6)||(playerFirstCardValue==3&&playerSecondCardValue==3)||(playerFirstCardValue==2&&playerSecondCardValue==2)){
    //                 return "split";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==7)||(playerFirstCardValue==7&&playerSecondCardValue==11)||(playerFirstCardValue==10&&playerSecondCardValue==10)) {
    //                 return "stand";
    //             } else if((playerFirstCardValue==4&&playerSecondCardValue==4)) {
    //                 return "hit";
    //             }
    //             else if((playerFirstCardValue==5&&playerSecondCardValue==5)) {
    //                 return "double";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==6)||(playerFirstCardValue==6&&playerSecondCardValue==11)) {
    //                 return "hit";
    //             } else if((playerFirstCardValue==11&&playerSecondCardValue==3)||(playerFirstCardValue==3&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==2)||(playerFirstCardValue==2&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==5)||(playerFirstCardValue==5&&playerSecondCardValue==11)||(playerFirstCardValue==11&&playerSecondCardValue==4)||(playerFirstCardValue==4&&playerSecondCardValue==11)) {
    //                 return "hit";
    //             } else if(highestCurrentHandValue==12) {
    //                 return "hit";
    //             }
    //             else if(highestCurrentHandValue>12) {
    //                 return "stand";
    //             } else if(highestCurrentHandValue>9) {
    //                 return "double";
    //             } else {
    //                 return "hit";
    //             }
    //         }
    //     return "stand";
    // }
    // return "stand";
    //     }
        return "stand";
    }

}
