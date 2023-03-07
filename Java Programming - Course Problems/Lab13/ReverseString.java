	void StringToCharsReverse(String input) {
		char output[] = new char[input.length()];
		int i = input.length();
		while (i > 0) 
			output[++i] = String.charAt(i);
		}
	}