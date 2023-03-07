import java.util.concurrent.ThreadLocalRandom;
class Dice {
    public static int rollDie() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 7);
        return randomNum;
    }
}

class BackAlleyPlayer {
    public static int balance = 20;
}
public class BackAlley {
    public static void main(String[] args) {
        while (BackAlleyPlayer.balance > 0) {
            BackAlleyPlayer.balance -= 1;
            int die1 = Dice.rollDie();
            int die2 = Dice.rollDie();
            int totalValue = die1 + die2;
            String winOrLose = "";
            if (totalValue == 7 || totalValue == 11) {
                BackAlleyPlayer.balance += 2;
                winOrLose = "win";
            } else if (totalValue == 2 || totalValue == 3 || totalValue == 12) {
                winOrLose = "lose";
            } else if (totalValue >= 2 && totalValue <= 12) {
                while (true) {
                    int point1 = Dice.rollDie();
                    int point2 = Dice.rollDie();
                    int totalPoint = point1 + point2;
                    if (totalPoint == 7) {
                        winOrLose = "lose";
                        break;
                    } else if (totalPoint == totalValue) {
                        BackAlleyPlayer.balance += 2;
                        winOrLose = "win";
                        break;
                    }
                }
            }
            System.out.println("$" + BackAlleyPlayer.balance + " " + winOrLose);
        }
    }
}
