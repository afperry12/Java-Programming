import java.sql.Timestamp;

class Limits {
    public static void main(String[] args) {
        // Create int and byte variables, and keep increasing the value of both by 1 until they aren't 
        // equivalent because the number has exceeded the maximum size limitation of a byte
        byte initialValueByte = 0;
        int testV = 0;
        boolean flag = true;
        while (flag) {
            initialValueByte = (byte) (initialValueByte + 1);
            testV = testV + 1;
            if (testV != initialValueByte) {
                initialValueByte = (byte) (initialValueByte - 1);
                System.out.println("Maximum byte value is " + initialValueByte);
                flag = false;
            }
        }

        // Use previous int and byte variables, and keep decreasing the value of both by 1 until they 
        // aren't equivalent because the number has passed the minimum size limitation of a byte
        initialValueByte = 0;
        testV = 0;
        flag = true;
        while (flag) {
            initialValueByte = (byte) (initialValueByte - 1);
            testV = testV - 1;
            if (testV != initialValueByte) {
                initialValueByte = (byte) (initialValueByte + 1);
                System.out.println("Minimum byte value is " + initialValueByte);
                flag = false;
            }
        }

        // Create short variable and use int variable. Keep increasing the value of both by 1 until they 
        // aren't equivalent because the number has exceeded the maximum size limitation of a short
        short initialValueShort = 0;
        testV = 0;
        flag = true;
        while (flag) {
            initialValueShort = (short) (initialValueShort + 1);
            testV = testV + 1;
            if (testV != initialValueShort) {
                initialValueShort = (short) (initialValueShort - 1);
                System.out.println("Maximum short value is " + initialValueShort);
                flag = false;
            }
        }

        // Use short and int variables. Keep decreasing the value of both by 1 until they 
        // aren't equivalent because the number has passed the minimum size limitation of a short
        initialValueShort = 0;
        testV = 0;
        flag = true;
        while (flag) {
            initialValueShort = (short) (initialValueShort - 1);
            testV = testV - 1;
            if (testV != initialValueShort) {
                initialValueShort = (short) (initialValueShort + 1);
                System.out.println("Minimum short value is " + initialValueShort);
                flag = false;
            }
        }

        // Create int and long variables. Keep increasing the value of both by 1 until they 
        // aren't equivalent because the number has exceeded the maximum size limitation of an int
        int initialValueInt = 0;
        long testValue = 0;
        flag = true;
        while (flag) {
            initialValueInt++;
            testValue++;
            if (testValue != initialValueInt) {
                initialValueInt = (int) (initialValueInt - 1);
                System.out.println("Maximum int value is " + initialValueInt);
                flag = false;
            }
        }
        // Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        // System.out.println("Timestamp After: " + timestamp2);

        // Use int and long variables. Keep decreasing the value of both by 1 until they 
        // aren't equivalent because the number has passed the minimum size limitation of an int
        initialValueInt = 0;
        testValue = 0;
        flag = true;
        while (flag) {
            initialValueInt--;
            testValue--;
            if (testValue != initialValueInt) {
                initialValueInt = (int) (initialValueInt + 1);
                System.out.println("Minimum int value is " + initialValueInt);
                flag = false;
            }
        }
    }
}
