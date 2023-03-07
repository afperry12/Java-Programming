public class DataConvert {
    public static void main(String[] args) {
        // Float -> int
        float initFloat = (float) 2.5;
        int initInt = (int) initFloat;
        System.out.println("2.5 cast to int gives " + initInt);

        // Negative float -> int (Truncation)
        initFloat = (float) (-4.5);
        initInt = (int) initFloat;
        System.out.println("-4.5 cast to int gives " + initInt);

        // Double -> float
        double initDouble = (double) (1.0 / 3.0);
        initFloat = (float) initDouble;
        System.out.println("double 1/3 = " + initDouble + ", but float 1/3 = " + initFloat);

        // Double -> long, int
        initDouble = (double) (8.0e9);
        long initLong = (long) initDouble;
        initInt = (int) initDouble;
        System.out.println("long value of 8.0e9 = " + initLong + ", but int value of 8.0e9 = " + initInt);

        // Int -> byte
        initInt = 256;
        byte initByte = (byte) initInt;
        System.out.println("byte value of 256 is " + initByte);

        // Int -> byte
        initInt = 257;
        initByte = (byte) initInt;
        System.out.println("byte value of 257 is " + initByte);

        // Int -> byte
        initInt = 258;
        initByte = (byte) initInt;
        System.out.println("byte value of 258 is " + initByte);

        // Int -> byte
        initInt = 383;
        initByte = (byte) initInt;
        System.out.println("byte value of 383 is " + initByte);

        // Int -> byte
        initInt = 384;
        initByte = (byte) initInt;
        System.out.println("byte value of 384 is " + initByte);
        
        // Int -> byte
        initInt = 511;
        initByte = (byte) initInt;
        System.out.println("byte value of 511 is " + initByte);

        // The byte value from int is moving with shorts' maximums and minimums (127 and -128)
    }
}
