import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class sentimentAnalyser {
    public static float getRating(String input) throws IOException {
        List<String> positiveWords = new ArrayList<String>();
        List<String> negativeWords = new ArrayList<String>();

        Scanner inputNegativeFile = new Scanner(FileSystem.get(new Configuration()).open(new Path("/lexicons/negative-words.txt")));
        Scanner inputPositiveFile = new Scanner(FileSystem.get(new Configuration()).open(new Path("/lexicons/positive-words.txt")));
        //Adding Words from the lexicons
        while (inputPositiveFile.hasNext()) {
            positiveWords.add(inputPositiveFile.nextLine());
        }
        //Adding words from the lexicons
        while (inputNegativeFile.hasNext()) {
            negativeWords.add(inputNegativeFile.nextLine());
        }
        inputNegativeFile.close();
        inputPositiveFile.close();

        input = input.toLowerCase();
        input = input.trim();
        input = input.replaceAll("[^a-zA-Z0-9\\s]", "");

        // Calculate the negative and positive counts
        int negCounter = 0;
        int posCounter = 0;
        //Counting words based on spaces
        String[] words = input.split(" ");

        for (int i = 0; i < words.length; i++) {
            //If positive words arraylist has each words then increase positive count
            if (positiveWords.contains(words[i])) {

                posCounter++;
            }
            //If negative words arraylist has each words then increase negative count
            if (negativeWords.contains(words[i])) {
                negCounter++;
            }
        }
        //net result is the difference of the count
        int result = (posCounter - negCounter);

        return result;

    }
}