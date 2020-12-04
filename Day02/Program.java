import java.util.Scanner;
import java.util.List;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args){

        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get("values.txt"))) {

            while(scanner.hasNext()){
                list.add(scanner.nextLine());                
            }
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        int firstCtr = 0, secondCtr = 0;
        for(String str: list){
            if(passed(str))
                firstCtr++;
            
            if(passedSecond(str))
                secondCtr++;
        }

        System.out.println(firstCtr + " passed the first!");
        System.out.println(secondCtr + " passed the second!");

    }

    // Checks whether or not the password is valid
    public static boolean passed(String line) {

        // Start by splitting the line to get the important values 
        // and to clear it up a bit
        String[] parts = line.split(" ");
        String[] limits = parts[0].split("-");

        int lower = Integer.valueOf(limits[0]);
        int upper = Integer.valueOf(limits[1]);
        String letter = parts[1].replace(":", "");
        char l = letter.charAt(0);
        String pw = parts[2];

        // Convert the password into a char array
        char[] array = pw.toCharArray();

        // loop through the array and count how many times the letter appears
        int ctr = 0;
        
        for(Character c: array){
            if(c == l)
                ctr++;
        }

        
        return ctr >= lower && ctr <= upper;
    }

    public static boolean passedSecond(String line){
        
        // Start by splitting the line to get the important values 
        // and to clear it up a bit
        String[] parts = line.split(" ");
        String[] indices = parts[0].split("-");

        // 0 1 2 3 4
        // 1 2 3 4 5
        // 1 2 3 4 5

        int firstIndex = Integer.valueOf(indices[0]);
        int secondIndex = Integer.valueOf(indices[1]);
        firstIndex--;
        secondIndex--;

        String letter = parts[1].replace(":", "");
        char l = letter.charAt(0);
        String pw = parts[2];

        // Convert the password into a char array
        char[] array = pw.toCharArray();

        if(array[firstIndex] == l){
            if(secondIndex >= array.length)
                return true;
            else if(array[secondIndex] != l)
                return true;
        }

        if(!(secondIndex >= array.length)){
            if(array[secondIndex] == l)
                if(array[firstIndex] != l)
                    return true;
        }
        

        return false;
    }


}