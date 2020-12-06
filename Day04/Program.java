import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Paths;

public class Program{
    public static void main(String[] args) {
        System.out.println("\n");
        
        List<String> values = readFile();

        int partOneCtr = 0, partTwoCtr = 0;
        for(String s: values){
            if(passportCheck(s))
                partOneCtr++;
            
            if(partTwo(s))
                partTwoCtr++;
        }

        System.out.println(partOneCtr + " passed the first test");
        System.out.println(partTwoCtr + " passed the second test");
    }
    /*
        byr (Birth Year) - four digits; at least 1920 and at most 2002.
        iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        hgt (Height) - a number followed by either cm or in:
            If cm, the number must be at least 150 and at most 193.
            If in, the number must be at least 59 and at most 76.
        hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        pid (Passport ID) - a nine-digit number, including leading zeroes.

    */
    private static boolean partTwo(String passport){

        if(!passportCheck(passport)) return false;

        List<String> items = Arrays.asList(passport.split(" |\\:"));

        boolean byr = false, iyr = false, eyr = false, hgt = false, hcl = false, ecl = false, pid = false;

        for(int i = 0; i < items.size(); i++){
            if(items.get(i).equals("byr")){
                // Birth year check
                int birthYear = Integer.valueOf(items.get(i+1));
                if(birthYear >= 1920 && birthYear <= 2002){
                    byr = true;
                    
                }

            } else if(items.get(i).equals("iyr")){
                // Issue year check  2010 and at most 2020.
                int issueYear = Integer.valueOf(items.get(i+1));
                if(issueYear >= 2010 && issueYear <= 2020){
                    iyr = true;
                    
                }

            } else if(items.get(i).equals("eyr")){
                // Expiration year check 
                // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
                int expirationYear = Integer.valueOf(items.get(i+1));
                if(expirationYear >= 2020 && expirationYear <= 2030){
                    eyr = true;
                }

            } else if(items.get(i).equals("hgt")){
                // Height check
                String height = items.get(i+1);
                String getPrefix = height.substring(height.length() - 2); 
                //If cm, the number must be at least 150 and at most 193.
                //If in, the number must be at least 59 and at most 76.

                if(getPrefix.equals("cm")){ // centimeters
                    
                    int h = Integer.valueOf(height.replace("cm", ""));
                    if(h >= 150 && h <= 193)
                        hgt = true;

                } else if(getPrefix.equals("in")){ // inches
                    
                    int h = Integer.valueOf(height.replace("in", ""));
                    if(h >= 59 && h <= 76)
                        hgt = true;
                }
                
            } else if(items.get(i).equals("hcl")){
                // Hair color check hcl (Hair Color) 
                // a # followed by exactly six characters 0-9 or a-f.
                String hairColor = items.get(i+1);

                hcl = hairColor.matches("#[a-f0-9]{6}");                
                
            } else if(items.get(i).equals("ecl")){
                // Eye color check ecl (Eye Color) 
                // exactly one of: amb blu brn gry grn hzl oth.
                String eyeColor = items.get(i+1);
                ecl = eyeColor.equals("amb") || eyeColor.equals("blu") || eyeColor.equals("brn") || 
                      eyeColor.equals("gry") || eyeColor.equals("hzl") || eyeColor.equals("oth") ||
                      eyeColor.equals("grn");
 
                
            } else if(items.get(i).equals("pid")){
                // Passport ID check pid (Passport ID) 
                // a nine-digit number, including leading zeroes.
                String passportId = items.get(i + 1);
                pid = passportId.matches("[0-9]{9}");
            }
        }
        return byr && iyr && eyr && hgt && hcl && ecl && pid;
    }

    private static List<String> readFile(){

        List<String> values = new ArrayList<>();

        try (Scanner scanner = new Scanner(Paths.get("values.txt"))){
            String str = "";
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.isEmpty()){
                    values.add(str);
                    str = "";
                    
                } else {str += line + " "; }
                
                if(!scanner.hasNextLine()) { values.add(str); }
                                
            }

        } catch (Exception e){
            System.out.println("Wrong file name");
        }

        return values;
    }

    private static boolean passportCheck(String passport){
        
        String[] parts = passport.split(" ");
        if(parts.length == 8) return true;
        else if(parts.length < 7) return false;
        
        List<String> items = Arrays.asList(passport.split(" |\\:"));
        if(items.contains("cid")) return false;

        return true;
    }

    
   
}