package mainGenerator;

import java.util.*;


public class WordCount {
 
    private String[] splitter;
    private int[] counter;
 
 
    /*
     * @param String - represents the sentence to be parsed
     *
     */
    public String countWords(String text){
         
        // remove any '\n' characters that may occur
        String temp = text.replaceAll("[\\n]", " ");
 
        // replace any grammatical characters and split the String into an array
        splitter = temp.replaceAll("[.,?!:;/]", " ").split(" ");
 
        // intialize an int array to hold count of each word
        counter= new int[splitter.length];
 
        // loop through the sentence
        for(int i =0; i< splitter.length; i++){
 
            // hold current word in the sentence in temp variable
            temp = splitter[i];
 
                // inner loop to compare current word with those in the sentence
                // incrementing the counter of the adjacent int array for each match
                for (int k=0; k< splitter.length; k++){
 
                    if(temp.equalsIgnoreCase(splitter[k]))
                    {
                        counter[k]++;
                    }
                }
        }
 
        return printResults();
    }
 
 
    private String printResults()
    {
 
      // create a HashMap to store unique combination of words and their counter
      // the word being the key and the number of occurences is the value
      HashMap map = new HashMap();
      
      String output = "";
      
      // populate the map
      for (int i=0; i< splitter.length; i++)
      {
          map.put(splitter[i].toLowerCase(), counter[i]);
      }
 
      // create an iterator on the map keys
      Iterator it = map.keySet().iterator();
 
        System.out.println("Word             Count");
        System.out.println("-----------------------");
 
        
        output += "\n";
        output += "\"Word             Count\"";
        output += "\n";
        output += "-----------------------";
        output += "\n";
        output += "\n";
        
        // loop for each key
        while(it.hasNext())
        {
            String temp =(String)it.next();
 
            // print the word itself
            System.out.print(temp);
            output += temp;
            
             // add relevant spacing to print consistently
            for (int i=0;i< (20 - temp.length());i++)
            {
                System.out.print(" ");
                output += " ";
            }
 
            // print the value (i.e. count of each word)
            String tempSTR = map.get(temp.toString()).toString();
            System.out.println(tempSTR);
            output += tempSTR;
            output += "\n";
            
            
        }
        
        return output;
    }
 
 
 
    // main method to test the class
    public static void main(String[] args){
 
        // example sentence
        String sentence = "How much wood, would a woodchuck chuck\n"+
                          "If a woodchuck could chuck wood?\n"+
                          "A woodchuck would chuck all the wood he could.\n"+
                          "If a woodchuck could chuck wood!";
 
        WordCount wc = new WordCount();
 
        wc.countWords(sentence);
 
    }
 
}