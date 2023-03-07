import java.io.*;

class Excerpt {
	static public void main(String[] args) {
		try {
			FileReader dis = new FileReader("C:\Temp\Excerpt.txt");
			final int L = 100;
			char inData[] = new char[L];
			for(int i = 0; i < L; i++) {
				int val = dis.read();
				if (val < 0) {
					throw new EOFException("EOF at position " + i);
				}
				inData[i] = (char) val;
			}
			dis.close();
			FileWriter dos = new FileWriter("Excerpt.out");
			String outStr = new String(inData);
			dos.write(outStr, 90, 10);
			dos.close();
		} catch (FileNotFoundException fnfe) {
			System.err.println("ERROR: file not found: " + fnfe);
		} catch (EOFException ioe) {
			System.err.println("ERROR: EOF: " + ioe);
		} catch (IOException ioe) {
			System.err.println("ERROR: IOException: " + ioe);
		}
	}
}