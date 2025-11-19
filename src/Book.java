
import java.util.Scanner;
import java.io.IOException;
import java.net.URL;
import java.io.PrintWriter;

public class Book
{
  private String book = ""; // Initialize book as empty string

  public Book(String url){
    readBook(url);
  }

  private void readBook(String link){
    int wordCount = 0;
    boolean inBook = false;
    try{
      URL url = new URL(link);
      Scanner s = new Scanner(url.openStream());
      PrintWriter writer = new PrintWriter("piglatin.txt"); // Save Pig Latin output
      while(s.hasNext()){
        String text = s.nextLine();
        String lower = text.toLowerCase();
        if (!inBook && lower.contains("start of the project gutenberg ebook")) {
          inBook = true;
          continue;
        }
        if (inBook && lower.contains("end of the project gutenberg")) {
          break;
        }
        if (inBook) {
          String pigLatinLine = translateSentence(text);
          System.out.println(pigLatinLine); // Print Pig Latin translation
          writer.println(pigLatinLine); // Write Pig Latin translation to file
          book += text + "\n";
          // Count words in this line using Scanner
          int count = 0;
          Scanner lineScanner = new Scanner(text);
          while (lineScanner.hasNext()) {
            lineScanner.next();
            count++;
          }
          lineScanner.close();
          wordCount += count;
        }
      }
      s.close();
      writer.close();
      System.out.println("Number of words in book: " + wordCount);
    }
    catch(IOException e){
      System.out.println("Error reading book from url: ");
    }
  }

  public String pigLatin(String word)
  {
    if (word == null || word.length() == 0) {
      return word;
    }
    if (Character.isDigit(word.charAt(0))) {
      return word;
    }
    String vowels = "aeiouyAEIOUY";
    if (word.length() == 1) {
      if (vowels.indexOf(word.charAt(0)) >= 0) {
        return word + "yay";
      } else {
        return word + "ay";
      }
    }
    if (vowels.indexOf(word.charAt(0)) >= 0) {
      return word + "yay";
    }
    int firstVowel = -1;
    for (int i = 0; i < word.length(); i++) {
      if (vowels.indexOf(word.charAt(i)) >= 0) {
        firstVowel = i;
        break;
      }
    }
    if (firstVowel == -1) {
      return word + "ay";
    }
    return word.substring(firstVowel) + word.substring(0, firstVowel) + "ay";
  }
 
  public int endPunctuation(String word)  //return the index of where the punctuation is at the end of a String. If it is all punctuation return 0, if there is no punctuation return -1
  {
    if (word == null || word.length() == 0) return -1;
    int i = word.length() - 1;
    while (i >= 0 && !Character.isLetterOrDigit(word.charAt(i))) {
      i--;
    }
    if (i == word.length() - 1) {
      return -1;
    }
    if (i < 0) {
      return 0;
    }
    return i + 1;
  }

  public String translateWord(String word)    //to share with class
  {
    // Find end punctuation index
    int punctIdx = endPunctuation(word);
    String wordPart = word;
    String punctPart = "";
    if (punctIdx > 0) {
      wordPart = word.substring(0, punctIdx);
      punctPart = word.substring(punctIdx);
    } else if (punctIdx == 0) {
      // all punctuation, return as is
      return word;
    }
    // Check if first letter is uppercase
    boolean wasCapital = wordPart.length() > 0 && Character.isUpperCase(wordPart.charAt(0));
    String lowerWord = wordPart.toLowerCase();
    String pig = pigLatin(lowerWord);
    // Capitalize first letter if original was capitalized
    if (wasCapital && pig.length() > 0) {
      pig = pig.substring(0, 1).toUpperCase() + pig.substring(1);
    }
    String convertedWord = pig + punctPart;
    return convertedWord;
  }

  public String translateSentence(String sentence)
  {
    if (sentence == null || sentence.isEmpty()) return sentence;
    StringBuilder retSentence = new StringBuilder();
    int i = 0;
    int n = sentence.length();
    while (i < n) {
      // Skip leading spaces
      while (i < n && sentence.charAt(i) == ' ') {
        retSentence.append(' ');
        i++;
      }
      int start = i;
      // Find end of word
      while (i < n && sentence.charAt(i) != ' ') {
        i++;
      }
      if (start < i) {
        String word = sentence.substring(start, i);
        retSentence.append(translateWord(word));
      }
    }
    return retSentence.toString();
  }
}
