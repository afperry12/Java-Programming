public class ReadArgs {
    public static void main(String[] args) {
        // Print number of arguments
        System.out.println("Program called with " + args.length + " arguments:");
        // Print each argument
        for (int x = 0 ; x < args.length ; x++) {
            if (x==args.length-1) {
            System.out.print(args[x]);
            } else {
            System.out.println(args[x]);
            }
        }
    }
}