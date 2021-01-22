package fyi.sorenneedscoffee.stupid;

import com.google.common.primitives.Ints;
import fyi.sorenneedscoffee.stupid.lsfr.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

  // used for translating between codes and characters
  private static HashMap<Code, String> table = new HashMap<>() {{
    put(new Code(0, 0, 0, 0, 0), "A");
    put(new Code(0, 0, 0, 0, 1), "B");
    put(new Code(0, 0, 0, 1, 0), "C");
    put(new Code(0, 0, 0, 1, 1), "D");
    put(new Code(0, 0, 1, 0, 0), "E");
    put(new Code(0, 0, 1, 0, 1), "F");
    put(new Code(0, 0, 1, 1, 0), "G");
    put(new Code(0, 0, 1, 1, 1), "H");
    put(new Code(0, 1, 0, 0, 0), "I");
    put(new Code(0, 1, 0, 0, 1), "J");
    put(new Code(0, 1, 0, 1, 0), "K");
    put(new Code(0, 1, 0, 1, 1), "L");
    put(new Code(0, 1, 1, 0, 0), "M");
    put(new Code(0, 1, 1, 0, 1), "N");
    put(new Code(0, 1, 1, 1, 0), "O");
    put(new Code(0, 1, 1, 1, 1), "P");
    put(new Code(1, 0, 0, 0, 0), "Q");
    put(new Code(1, 0, 0, 0, 1), "R");
    put(new Code(1, 0, 0, 1, 0), "S");
    put(new Code(1, 0, 0, 1, 1), "T");
    put(new Code(1, 0, 1, 0, 0), "U");
    put(new Code(1, 0, 1, 0, 1), "V");
    put(new Code(1, 0, 1, 1, 0), "W");
    put(new Code(1, 0, 1, 1, 1), "X");
    put(new Code(1, 1, 0, 0, 0), "Y");
    put(new Code(1, 1, 0, 0, 1), "Z");
    put(new Code(1, 1, 0, 1, 0), ".");
    put(new Code(1, 1, 0, 1, 1), "!");
    put(new Code(1, 1, 1, 0, 0), "?");
    put(new Code(1, 1, 1, 0, 1), new String(Character.toChars(0x1F4A9))); // poo
    put(new Code(1, 1, 1, 1, 0), new String(Character.toChars(0x1F600))); // smiley
    put(new Code(1, 1, 1, 1, 1), new String(Character.toChars(0x1F926))); // facepalm
  }};

  public static void main(String[] args) {
      System.out.println("--THE 'CRYPTIONS--");
    System.out.println(crypt("HI", new LSFRA(1, 0, 1, 1)));
    System.out.println(crypt("BLUE", new LSFRA(1, 1, 0, 1, 1)));
    System.out.println(crypt("?SY", new LSFRA(0, 1, 1, 0)));
    System.out.println(crypt(".!?!.?", new LSFRA(1, 0, 1, 1, 0)));

    System.out.println();
    System.out.println("--THE MAXIMAL BOI--");
    System.out.println("This is apparently called a Fibonacci Register http://repository.uobabylon.edu.iq/mirror/resources/paper_1_17528_649.pdf");
    var lsfrf = new LSFRFibonacci(1, 1, 1, 1);
    for (int i = 0; i < Math.pow(2, 4); i++) {
        lsfrf.sample();
        System.out.println(Arrays.toString(lsfrf.getRegister()));
    }

    System.out.println();
    System.out.println("--THE SUBMAXIMAL BOI--");
    System.out.println("i dont know if this is what you asked for but here you go^tm");
    var lsfrb = new LSFRB(1, 1, 1, 1);
    for (int i = 0; i < Math.pow(2, 6); i++) {
      lsfrb.sample();
      System.out.println(Arrays.toString(lsfrb.getRegister()));
    }
  }

  // conveniently, this method doesnt require dedicated code for descryption.
  private static String crypt(String message, LSFR lsfr) {
    var messageStream = new int[0];
    // converts the message string to an array of ints between 1 and 0
    // the hashmap defined above is sorted through to find all the associated codes and the bit arrays they store
    for (String s : message.split("")) {
      var bits = table
          .entrySet()
          .stream()
          .filter(entry -> entry.getValue().equals(s))
          .map(Map.Entry::getKey)
          .findFirst()
          .get().bits;
      messageStream = Ints.concat(messageStream, bits);
    }

    var encrypt = splitArray(xor(lsfr, messageStream), 5);
    var builder = new StringBuilder();
    for (int[] i : encrypt) {
      var test = table.get(new Code(i));
      builder.append(test);
    }

    return builder.toString();
  }

  // returns an int array that has been xored with the lsfr provided
  private static int[] xor(LSFR lsfr, int[] stream) {
    var result = new ArrayList<Integer>();
    for (int i : stream) {
      result.add((i ^ lsfr.sample()) % 2);
    }
    return result.stream().mapToInt(i -> i).toArray();
  }

  // source: https://stackoverflow.com/questions/27857011/how-to-split-a-string-array-into-small-chunk-arrays-in-java
  private static int[][] splitArray(int[] arrayToSplit, int chunkSize) {
    if (chunkSize <= 0) {
      return null;  // just in case :)
    }
    // first we have to check if the array can be split in multiple
    // arrays of equal 'chunk' size
    int rest = arrayToSplit.length
        % chunkSize;  // if rest>0 then our last array will have less elements than the others
    // then we check in how many arrays we can split our input array
    int chunks = arrayToSplit.length / chunkSize + (rest > 0 ? 1
        : 0); // we may have to add an additional array for the 'rest'
    // now we know how many arrays we need and create our result array
    int[][] arrays = new int[chunks][];
    // we create our resulting arrays by copying the corresponding
    // part from the input array. If we have a rest (rest>0), then
    // the last array will have less elements than the others. This
    // needs to be handled separately, so we iterate 1 times less.
    for (int i = 0; i < (rest > 0 ? chunks - 1 : chunks); i++) {
      // this copies 'chunk' times 'chunkSize' elements into a new array
      arrays[i] = Arrays.copyOfRange(arrayToSplit, i * chunkSize, i * chunkSize + chunkSize);
    }
    if (rest > 0) { // only when we have a rest
      // we copy the remaining elements into the last chunk
      arrays[chunks - 1] = Arrays
          .copyOfRange(arrayToSplit, (chunks - 1) * chunkSize, (chunks - 1) * chunkSize + rest);
    }
    return arrays; // that's it
  }
}
