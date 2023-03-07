
/**
 * class Node
 *
 * class Node represents one element in a doubly linked list.
 * It stores a char value and has references ('prev' and 'next')
 * to other Nodes in a list.
 */
class Node {
	private final char m_val;
	Node prev;
	Node next;

	Node(char c) {
		m_val = c;
		prev = null;
		next = null;
	}

	public char val() {
		return m_val;
	}
}

/**
 * class DoubleList
 *
 * class DoubleList implements an actual doubly linked list.
 * There are several methods below that must be implemented by the student.
 */
class DoubleList {
	////
	//// Class member variables
	////
	// Node that tracks the head of this list.
	Node m_head;

	// Node that tracks the 'current' i.e. currently-selected Node in this list.
	Node m_current;

	////
	//// Class methods
	////
	DoubleList() {
		m_head = null;
		m_current = null;
	}

	// Reset list so current points to the head.
	Node head() {
		m_current = m_head;
		return current();
	}

	// Return current node
	Node current() {
		return m_current;
	}

	// Return node AFTER current.
	// This lets us see if we are almost at the end of the list.
	Node peek() {
		if (m_current == null) {
			return null;
		}
		return m_current.next;
	}

	// Return 'current' node but also advance 'current' to point to the following
	// node.
	//
	// If next() is called repeatedly, current will eventually == null,
	// after moving past the end of this list.
	Node next() {
		Node retval = m_current;
		if (m_current != null) {
			m_current = m_current.next;
		}
		return retval;
	}

	// add()
	//
	// Insert 'n' into this list, after current and before current.next .
	// Make 'n' the new 'current' after the insertion.
	// Be sure to handle cases of empty list, current == head, current == last .
	//
	// Throw an IndexOutOfBoundsException if user calls add() when current == null
	void add(Node n)
			throws IndexOutOfBoundsException {
		if (m_head == null && m_current == null) {
			m_current = n;
			m_head = n;
		} else if (m_current == null) {
			throw new IndexOutOfBoundsException();
		} else if (m_current.next == null) {
			m_current.next = n;
			n.prev = m_current;
			m_current = n;
		} else if (m_current != null && m_current.next != null) {
			n.next = m_current.next;
			n.prev = m_current;
			m_current.next.prev = n;
			m_current.next = n;
			m_current = n;
		}
	}

	// Helper function so we can add chars rather than Nodes to this list.
	void add(char c) {
		add(new Node(c));
	}

	// remove()
	//
	// Remove current node, afterwards having current point to the predecessor of
	// the old 'current'.
	// Be sure to handle cases of empty lists, current == head, current == last .
	//
	// Throw an IndexOutOfBoundsException if user calls remove() when current ==
	// null
	void remove()
			throws IndexOutOfBoundsException {
		if (m_current == m_head && m_current.next == null) {
			m_current = null;
			m_head = null;
		} else if (m_current == m_head) {
			m_head = m_current.next;
			m_current = m_head;
		} else if (m_current.next == null) {
			m_current.prev.next = null;
		} else if (m_current == null) {
			throw new IndexOutOfBoundsException();
		} else if (m_current != null && m_current.next != null && m_current.prev != null) {
			m_current.prev.next = m_current.next;
			m_current.next.prev = m_current.prev;
			m_current = m_current.prev;
		}
	}
} // end class DoubleList

/**
 * class Double
 *
 * This class implements the program's main() function, which exercises class
 * DoubleList
 * and generates output.
 */
public class Double {

	// PrintList()
	//
	// Print all char values in the list 'd' in order, from head to last element.
	// Print a '<' after the value that corresponds to the 'current'
	// element of 'd'.
	static void PrintList(DoubleList d) {
		Node n = d.m_head;
		while (n != null) {
			if(n==d.m_current) {
			System.out.print(n.val()+"<");
			} else {
			System.out.print(n.val());
			}
			n = n.next;
		}
	}

	public static void main(String[] args) {
		// Create an empty doubly linked list.
		DoubleList list = new DoubleList();
		// Put the letters 'a' through 'z' into the list.
		for (char c = 'a'; c <= 'z'; c++) {
			list.add(c);
		}

		// Go through a sequence of list operations.
		// These should not cause any errors.
		// PrintList(list);
		list.head();
		list.add('!');
		list.head();
		list.remove();
		list.next();
		list.remove();
		list.next();
		list.remove();
		list.add('@');
		list.next();
		list.next();
		list.add('#');
		list.remove();
		list.next();
		list.add('$');
		list.next();
		list.remove();
		list.next();
		list.next();
		list.next();
		list.next();
		list.next();
		list.next();
		list.remove();
		list.next();
		list.next();

		// Output the final list.
		PrintList(list);
	} // end main()

} // end class Double