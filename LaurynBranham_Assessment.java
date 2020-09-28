// Technical Assessment — LaurynBranham_Assessment.java
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.*;

/*
    Public Class for Esker Technical Assessment
*/
public class LaurynBranham_Assessment {

    public static void main(String args[]){

        if(args.length != 1) {
            System.out.println("Incorrect Argument: Please enter one file name.");
            return;
        }
        String filename = args[0];
        writeResults(filename);  
    }

    /*
        Method used to write results to output file.
    */
    public static void writeResults(String filename){

        int numLines = 0;
        int numChars = 0;
        int numLetters = 0;
        int numFigures = 0;
        int numOther = 0;
        int numWords = 0;
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(); // key: num chars, value: how many words

        try {
            FileWriter writer = new FileWriter("results.txt"); 
            writer.write("File name: " + filename + "\n");

            File obj = new File(filename);
            Scanner scanner = new Scanner(obj);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                numLines++;

                if(line.isEmpty()){
                    continue;
                }

                String words[]=line.split(" ");
                
                for(int i = 0; i < words.length; ++i){
                    int key = words[i].length();
                    if(map.containsKey(key)){
                        map.put(key, map.get(key) + 1);
                    } else {
                        map.put(key, 1);
                    }
                }

                numWords += words.length;
                numChars += line.length(); // Includes white space

                for(int i = 0; i < line.length(); i++){
                    if(Character.isDigit(line.charAt(i))){
                        numFigures++;
                    } else if(Character.isLetter(line.charAt(i))){
                        numLetters++;
                    } else {
                        // Option if whitespace is not wanted: 
                        //     if whitespace, continue
                        numOther++;
                    }
                }
            }

            writer.write("Number of lines: " + numLines + "\n");
            writer.write("Number of characters (total): " + numChars + "\n");
            writer.write("Number of letters: " + numLetters + "\n");
            writer.write("Number of figures: " + numFigures + "\n");
            writer.write("Number of other characters: " + numOther + "\n");
            writer.write("Number of words: " + numWords + "\n");

            Set set = map.entrySet();
            Iterator i = set.iterator();

            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                writer.write("Number of " + me.getKey() + " letter words: " + me.getValue() + "\n");
            }
            
            writer.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found. Try another file.");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }
}