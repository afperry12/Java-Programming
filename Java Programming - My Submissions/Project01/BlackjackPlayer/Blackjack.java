package BlackjackPlayer;
class Blackjack {
    public static void main(String[] args) {
        try{
            Player client = new Player();
            client.run(args[0],args[1],args[2],args[3]);
    } catch (Exception e) {
        System.err.println(e);
    }
    }
}