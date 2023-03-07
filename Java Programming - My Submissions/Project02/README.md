**Huffman Coding Project Tests Administered**
=====================
Peter Perry
<p>
December 2, 2022
<p>

Generate.java
<ul type="list-style-type:square">
  1. Ensuring that all input characters would be recognized<p>
  <ul>This was difficult because, for some reason, Java on Windows prioritizes higher level values of chars for chars that are duplicated. Here is more detail regarding this difficulties:  Using the phrase “erjnfrwfnwrfnwfwonofroowndsdmcd™™™” when I cast each char to an int, I get the following ints:<p></ul>
  <ul>101, 114, 106, 110, 102, 114, 119, 102, 110, 119, 114, 102, 110, 119, 102, 119, 111, 110, 111, 102, 114, 111, 111, 119, 110, 100, 115, 100, 109, 99, 100, 8482, 8482, 8482, 61537, 61538, 61543, 61540, 61541<p></ul>
  <ul>These last 8 values were copied from this website: https://www.ssec.wisc.edu/~tomw/java/unicode.html. However, they were not copied from the int shown above. For example, 8482 is the trademark symbol. I copied this into the file to attempt to get the value 153 in the position 0x0099 since that is also the trademark symbol. Java seems to be prioritizing an int value that I do not want it to be, or I am converting the char to an int in the wrong way. I have tried coding the program using both methods I know of converting char to int:<p></ul>
  <ul>int unicodeCheck = (int) nextLine.charAt(x); and Integer.valueOf(Character.valueOf(nextLine.charAt(x)).charValue())<p></ul>
  <ul>They both produce the same list of integers I mentioned<p></ul>

  <ul>In the end, while my program does not handle every char in the expected range on Windows, it should still work on other computers (I think) because I manually put at least 1 of every char in the set range into the huffman codings.<p></ul>
  <p>
2. I tested for all error handling. FileNotFoundException, ArrayIndexOutOfBoundsException, etc.<p>
<p>
3. To help my own understanding of what was happening, I created a Java class called PrintMethods, which would print the outline of the tree in the shape of a tree (kinda). This was a lot more helpful before I added 1 character for each possible character to the codebook in case multiple different text files would be used in a row without updating the codebook because then some characters might not exist in the codebook in that case. Anyways, I really gained a great understanding of how the tree ran into leaf nodes and was able to ask better questions as need with this better understanding. If you would like to print these methods, simply uncomment the PrintMethods lines in Generate.java. They should be somewhere around lines 49-52.</ul>

Encoding.java
<ul type="list-style-type:square"><p>
  1. I tested for error handling, including the same exceptions as Generate.java.<p>
  2. I tried running multiple different files in a row, making sure to try letters that were not in the original file used for Generate.java.<p>
  3. I also tested the percent compression in this file (you can test by uncommenting lines 16, 49, and 51, which print initial size, final size, and percent compression, respectively.<p>
  4. I tested putting values outside of the Unicode range into the uncompressed file to make sure they were skipped over.<p>
  5. I also tested adding multiple lines to the file sent to encoding to make sure new lines were accounted for. The code added to make sure this works is in lines 49-51.<p>
</ul>
Decoding.java<p>
<ul type="list-style-type:square"><p>
  1. I tested for error handling, including the same exceptions as Generate.java and Encode.java.<p>
  2. I obviously tested to make sure the final output was the same as the input (besides characters that were out of range if there were any).<p>
  3. Additionally, I tested inserting the end of transmission character into the middle of a sentence to make sure decoding worked. You can see where I tested this in lines 59-62 of Decode.java.<p>
  4. Also, I can compare the trees from Decode.java and Generate.java using the PrintMethods class, which confirms I recreated the tree correctly.<p>
</ul>
