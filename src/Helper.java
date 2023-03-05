import java.util.Hashtable;
import java.util.Scanner;

public class Helper {
    public static String stringVals = "slay\nslayQueen\nloopin\ntillClapback\nvibeCheck\nbruhPlease\nokBoomer\npeaceOut";
    private static final Hashtable<String, String> information = new Hashtable<>(){{
        put("goOff","goOff\nDescription: \nprints the string/ number / boolean to the console\nUsage: \nslay “Hello”\nslay “World”\n");
        put("goOffKing","goOffKing\nDescription: \nprints the string/ number / boolean to the console with a new line\nUsage: \nslayQueen “Hello”\ngslayQueen “World”\n");
        put("getSauce", "getSauce\nDescription: \ntakes input from the user\n There are three input types: %s, %n, and %b for strings, numbers and booleans respectively\nUsage: \nstring name\ngetSauce %s name");
        put("loopin","loopin\nDescription: \nA loop which executes the code, written in the block, (final-initial) times\nUsage: \nnum m := 0;\nnum n := 3;\nloopin i : (m,n)\n{\ngoOff i\ni++;\n}\n");
        put("tillClapback","tillClapback\nDescription: \nA loop which executes the code, written in the block, till the [condition] becomes Cap\nUsage: \nnum i =5;\ntillClapback(i > 0)\n{\ngoOff i\ni := i -1;\n}\n");
        put("vibeCheck","vibeCheck\nDescription: \nvibeCheck([condition])\nExecutes the code if the [condition] is NoCap\nbruhPlease([condition2])\nExecutes if vibeCheck is not executed and [condition2] is NoCap\nokBoomer\t\nExecutes if both vibeCheck and bruhPlease don’t get executed\t\tpeaceOut\nEnds the if loop\n\nUsage: \nnum i := 5;\nvibeCheck(i == 1)\n{\nslay i\n}\nbruhPlease(i == 3)\n{\nslay i\n}\nokBoomer\n{\nslay i\n}\npeaceOut;\n");
        put("bruhPlease","bruhPlease\nDescription: \nvibeCheck([condition])\nExecutes the code if the [condition] is NoCap\nbruhPlease([condition2])\nExecutes if vibeCheck is not executed and [condition2] is NoCap\nokBoomer\t\nExecutes if both vibeCheck and bruhPlease don’t get executed\t\tpeaceOut\nEnds the if loop\n\nUsage: \nnum i := 5;\nvibeCheck(i == 1)\n{\nslay i\n}\nbruhPlease(i == 3)\n{\nslay i\n}\nokBoomer\n{\nslay i\n}\npeaceOut;\n");
        put("okBoomer","okBoomer\nDescription: \nvibeCheck([condition])\nExecutes the code if the [condition] is NoCap\nbruhPlease([condition2])\nExecutes if vibeCheck is not executed and [condition2] is NoCap\nokBoomer\t\nExecutes if both vibeCheck and bruhPlease don’t get executed\t\tpeaceOut\nEnds the if loop\n\nUsage: \nnum i := 5;\nvibeCheck(i == 1)\n{\nslay i\n}\nbruhPlease(i == 3)\n{\nslay i\n}\nokBoomer\n{\nslay i\n}\npeaceOut;\n");
        put("peaceOut","peaceOut\nDescription: \nvibeCheck([condition])\nExecutes the code if the [condition] is NoCap\nbruhPlease([condition2])\nExecutes if vibeCheck is not executed and [condition2] is NoCap\nokBoomer\t\nExecutes if both vibeCheck and bruhPlease don’t get executed\t\tpeaceOut\nEnds the if loop\n\nUsage: \nnum i := 5;\nvibeCheck(i == 1)\n{\nslay i\n}\nbruhPlease(i == 3)\n{\nslay i\n}\nokBoomer\n{\nslay i\n}\npeaceOut;\n");
    }};
    private static String getHelp(String token) throws IllegalArgumentException{
        if(information.containsKey(token)){
            return information.get(token);
        }
        else{
            throw new IllegalArgumentException("Not found");
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        while(true){
            String input = scnr.next().trim();
            if(input.equals("done")){
                break;
            }
            if(input.equals("help")){
                System.out.println("Sorry we could not find information for the given method\nPlease choose from the following\n");
                System.out.println(stringVals);
            }
            try {
                System.out.println(getHelp(input));
            }catch (IllegalArgumentException e){
                System.out.println("Sorry we could not find information for the given method\nPlease choose from the following\n");
                System.out.println(stringVals);
            }
        }
    }
}
