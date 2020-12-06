import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// 3 right 1 down, how many trees?
/*
    Read file to arraylist
    Read each line into array
    have a index variable
    if the index >= array.length, set index to 0 (pattern loops)
    have a variable to keep track on what to do
    at every array, index += 3; count how many # is passed
*/ 
public class Program{

    public static int index = 0;
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("values.txt"));
            ArrayList<String> al = new ArrayList<String>();
            String line = "";
            int hits = 0;
            int currentPos;
            while((line = reader.readLine()) != null) {
                al.add(line);
            }
            int size = al.get(0).length();
            for(int i = 0; i < al.size(); i++) {
                currentPos = (3 * i) % size;
                if (al.get(i).charAt(currentPos) == '#' ) {
                    hits++;
                }
            }
            System.out.println(hits);
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: File not found " + ex);
        } catch (IOException io) {
            System.out.println("ERROR: IO exception " + io);
        }

        long one = part2(1, 1);
        long second = part2(3, 1);
        long third = part2(5, 1);
        long fourth = part2(7, 1);
        long fifth = part2(1, 2);
        long result = one * second * third * fourth * fifth;
        System.out.println("Answer: " + result);

    }


    public static long part2(int right, int down) {
        long hits = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("values.txt"));
            ArrayList<String> al = new ArrayList<String>();
            String line = "";
            int currentPos = 0;
            while((line = reader.readLine()) != null) {
                al.add(line);
            }
            int size = al.get(0).length();  
            for(int i = 0; i < al.size(); i = i + down) {
                if (al.get(i).charAt(currentPos) == '#' ) {
                    hits++;
                }
                currentPos = (currentPos + right) % size;
            }            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: File not found " + ex);
        } catch (IOException io) {
            System.out.println("ERROR: IO exception " + io);
        }
        return hits;
    }
}