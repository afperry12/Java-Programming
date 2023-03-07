public class Node implements Comparable<Node> {
    char inputChar;
    int charCount;
    String codedSymbol;
    public int compareTo(Node n) {
        if(charCount<n.charCount) {
            return -1;
        } else if(charCount==n.charCount) {
            return 0;
        } else if(charCount>n.charCount) {
            return 1;
        }
        return -1;
    }
    Node leftNode;
    Node rightNode;

    public void setSymbol(String s) {
        codedSymbol = s;
        if(leftNode!=null) {
        leftNode.setSymbol(s+"0");
        }
        if(rightNode!=null) {
        rightNode.setSymbol(s+"1");
        }
    }
}
