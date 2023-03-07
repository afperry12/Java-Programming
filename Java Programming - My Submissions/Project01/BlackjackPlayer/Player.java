package BlackjackPlayer;
import java.io.IOException;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;

public class Player {
    public static boolean inGame = true;
    // public static HashSet<PlayerDeck> currentHands = new HashSet<PlayerDeck>();
    public static int currentHandIndex = 0;
    public static HashMap<Integer,PlayerCard> FirstPlayerCard = new HashMap();
    public static HashMap<Integer,PlayerCard> SecondPlayerCard = new HashMap();
    public static HashMap<Integer,Integer> numberOfTimesSplit = new HashMap<Integer,Integer>();
    public static HashMap<Integer,Integer> numberOfTimesDoubled = new HashMap<Integer,Integer>();
    public static HashMap<Integer,Integer> numberOfTimesHit = new HashMap<Integer,Integer>();
    public static HashMap<Integer,Integer> numberOfTimesStand = new HashMap<Integer,Integer>();
    public static PlayerDeck currentHand = new PlayerDeck();
    public static HashMap<Integer,HashMap<PlayerDeck,String>> allHandsInRound = new HashMap<Integer,HashMap<PlayerDeck,String>>();
    public static HashMap<Integer,String> statusOfHands = new HashMap<Integer,String>();
    public static HashMap<Integer,Integer> bets = new HashMap<>();
    public static PlayerDeck previouslyPlayedCards = new PlayerDeck();
    public static PlayerCard dealersUpCard = new PlayerCard();
    public static boolean trainPlayer = false;
    public static int totalNumberOfHands = 1;
    public static int timesStatusIsCalledInARow = 0;
    public static Socket AI;
    public static DataInputStream AIDIS;
    public static DataOutputStream AIDOS;
    public void run(String IP, String port, String IPOfAI, String portOfAI) throws IOException {
        try {
        Socket socket = new Socket(IP, Integer.valueOf(port));
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        int counter = 0;
        if(trainPlayer==false) {
        AI = new Socket(IP, Integer.valueOf(portOfAI));
        AIDIS = new DataInputStream(AI.getInputStream());
        AIDOS = new DataOutputStream(AI.getOutputStream());
        }
        while(inGame) {
            // Get Returned value from accessing
            // Parse then game logic
            String test = ParseInputs.Parse(dis.readUTF());
            //if returning certain things set to int etc
            if(inGame==false) {
                break;
            }
            // System.out.println(test);
            if(test==null){
            } else {
                // System.out.println("Value to send"+test);
                // System.out.println(test);
                dos.writeUTF(test);
                dos.flush();
            }
        }
        System.out.println("Game ended");

        socket.close();

        }catch(Exception e) {
            System.out.println("Hi");
            System.err.println(e);
            System.out.println(e.getMessage());
        }
    }
}
