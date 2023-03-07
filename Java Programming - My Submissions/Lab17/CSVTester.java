import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
class CSVTester {
    // Keep track of recorded array length of strings
    public static int previousStringCount = -1;

    public static void main(String[] args) throws IOException, WrongNumberOfStringInputs {
        // Initiate file with specified name
        CSVWriter csvWriter = new CSVWriter("CSVTester.csv");
        Scanner scanner = new Scanner(System.in);
        boolean firstRun = true;
        try {
            // while receiving input check if next line is empty, if so throw error.
            // Otherwise, create array of strings by separating spaces and check if the new
            // input's array
            // of strings has the same length as the last input. If not throw error. If so,
            // write to file
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine.replaceAll(" ", "").equals("")) {
                    throw new BlankLine();
                }
                String[] stringsInEachLine = nextLine.trim().replaceAll("\s+", " ").split(" ");

                if (previousStringCount != stringsInEachLine.length && firstRun == false) {
                    throw new WrongNumberOfStringInputs(previousStringCount, stringsInEachLine.length);
                } else {
                    previousStringCount = stringsInEachLine.length;
                    csvWriter.writeln(stringsInEachLine);
                }
                firstRun = false;
            }
        } catch (BlankLine e) {
            // Flush if a line is blank to still write to file
            csvWriter.flush();
            csvWriter.close();
            System.exit(1);
        }
        // Flush to write to file
        csvWriter.flush();
        csvWriter.close();
    }
}

// Handle wrong number of string inputs
class WrongNumberOfStringInputs extends Exception {
    public WrongNumberOfStringInputs(int originalNumber, int errorNumber) {
        super("Received " + originalNumber + " string input(s). Expected " + errorNumber + ".");
    }

    public WrongNumberOfStringInputs(String blank) {
        super("Received " + blank + " line");
    }
}

// Handle if line is blank
class BlankLine extends WrongNumberOfStringInputs {
    public BlankLine() {
        super("empty");
    }
}

// Create and write to file
class CSVWriter extends FileWriter {
    public CSVWriter(File file) throws IOException {
        super(file);
    }

    public CSVWriter(String fileName) throws IOException {
        super(fileName);
    }

    void writeln(String[] stringsForALine) throws IOException {
        for (int i = 0; i < stringsForALine.length; i++) {
            if (i == stringsForALine.length - 1) {
                super.write(stringsForALine[i] + "\n");
            } else {
                super.write(stringsForALine[i] + ",");
            }
        }
    }
}