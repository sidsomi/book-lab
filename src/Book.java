//A few assumptions.......

//Words will be separated by spaces. 
//There can be punctuation in a word, we will only add/keep punctuation at the end of a string if it is at the end of a string.
//    for examples: Hello.==> Ellohay.    Good-bye! ==> Ood-byegay!    so... ==> osay...

import java.io.InvalidObjectException;
import java.util.Scanner;
import java.net.URL;

public class Book
{
  private String book;

  public Book(String url)
  {
    readBook(url);
  }

  private void readBook(String link)  
  public String pigLatin(String word)
  {
     String newWord= "";
    String digits = "0123456789";
    String vowels = "aeiouy";
    if (word.length()==0) {
      return word;
    }
    else if (digits.indexOf(word.substring(0,1))>=0) {
      return word;
    }
    else if (vowels.indexOf(word.substring(0,1))>=0) {
      return word + "yay";
    }
    else if (word.length()==1) {
      return word;
    }
for(int i = 0; i< word.length(); i++)
{
  if (vowels.indexOf(word.substring(i,i+1)) >= 0) {
    String left = word.substring(0,i);
    String right  = word.substring(i);
    return right + left + "ay";
  }
}



    return newWord;
  }
  
  public int endPunctuation(String word)  //return the index of where the punctuation is at the end of a String. If it is all punctuation return 0, if there is no punctuation return -1
  {

    return -1;
  }
public String translateWord(String word)    //to share with class
  {
 int endIndex = word.length() -1;

    while (endIndex >= 0 && !Character.isLetterOrDigit(word.charAt(endIndex))) {

      endIndex--;
      
    }
    String punctuation = "";
    if(endIndex < word.length() -1){
      punctuation = word.substring(endIndex + 1);
      word = word.substring(0, endIndex + 1);
    }
   
    boolean isCaps = Character.isUpperCase(word.charAt(0));

    word = word.toLowerCase();

    String vowels = "aeiou";
    int vowelIndex = -1;
    for(int i = 0; i <word.length(); i++)
    {
      if(vowels.indexOf(word.charAt(i)) != -1){

        vowelIndex = i;
        break;

      }
    }
    String newWord;
    if(vowelIndex == 0){
      newWord = word + "yay";
    }
    else if (vowelIndex > 0 ){
      newWord = word.substring(vowelIndex) + word.substring(0 , vowelIndex) + "ay";
    }
    else{
      newWord = word + "ay";
    }
    if (isCaps){
      newWord = Character.toUpperCase(newWord.charAt(0)) + newWord.substring(1);
    }
    
      
      //word.substring(1) + word.charAt(0) + "ay";
      return newWord + punctuation;
    }


  

  public String translateSentence(String sentence)
  {
   String  retSentence = "";
     int m = sentence.indexOf(" ");
      
       while(m>=0)
       {
      String  word = sentence.substring(0, m);
     retSentence+=translateWord(word)+" ";
      sentence = sentence.substring(m+1);
     m = sentence.indexOf(" ");
      
     
       }
       if(sentence.length()>0)
       {
        retSentence+=translateWord(sentence)+ " ";
        
       }
return retSentence;   


    
  }
}  
