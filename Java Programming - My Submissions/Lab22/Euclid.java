public class Euclid {
    public void GCF(int A, int B) {
        if (B == 0) {
            System.out.println(A);
        } else {
            this.GCF(B, A % B);
        }
    }

    public static void main(String[] args) {
        try {
            int numberA = Integer.parseInt(args[0]);
            int numberB = Integer.parseInt(args[1]);
            Euclid euclid = new Euclid();
            euclid.GCF(numberA, numberB);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}