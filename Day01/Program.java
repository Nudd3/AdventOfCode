import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args){

        List<Integer> list = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("values.txt"));
            while(scanner.hasNext()){
                list.add(Integer.valueOf(scanner.next()));
            }


        } catch (Exception e){
            
        }

        int[] numbers = findTwoNumbers(list);
        System.out.println("Product of 2: " + (numbers[0] * numbers[1]));

        
        int[] numbers2 = findThreeNumbers(list);
        System.out.println("Product of 3: " + (numbers2[0] * numbers2[1] * numbers2[2]));
    }

    public static int[] findTwoNumbers(List<Integer> list){

        int[] array = new int[2];

        for(int i = 0; i < list.size(); i++){
            for(int j = i; j < list.size(); j++){

                if(list.get(i) + list.get(j) == 2020){
                    array[0] = list.get(i);
                    array[1] = list.get(j);
                    break;
                }

            }
        }

        return array;
    }

    public static int[] findThreeNumbers(List<Integer> list) {
        int[] array = new int[3];

        for(int i = 0; i < list.size(); i++){
            for(int j = i; j < list.size(); j++){
                for(int k = j; k < list.size(); k++){

                    if(list.get(i) + list.get(j) + list.get(k) == 2020){
                        array[0] = list.get(i);
                        array[1] = list.get(j);
                        array[2] = list.get(k);
                        break;
                    }

                }
            }
        }

        return array;
    }


}