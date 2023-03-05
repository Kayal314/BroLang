import java.util.Hashtable;
import java.util.Scanner;

public class Helper {
    public static String stringVals = "goOff\nloopin\ntillClapback\nvibeCheck\nifOff\nifBasic\npeaceOut";
    private static final Hashtable<String, String> information = new Hashtable<>(){{
        put("goOff","prints the string/ number / boolean to the console\ngoOff “Hello”\ngoOff “World”\n");
        put("loopin","A loop which executes the code, written in the block, (final-initial) times\nnum m := 0;\nnum n := 3;\nloopin i : (m,n)\n{\ngoOff i\ni++;\n}\n");
        put("tillClapback","A loop which executes the code, written in the block, till the [condition] becomes Cap\nnum i =5;\ntillClapback(i > 0)\n{\ngoOff i\ni := i -1;\n}\n");
        put("vibeCheck","vibeCheck([condition])\nExecutes the code if the [condition] is NoCap\nifOff([condition2])\nExecutes if vibeCheck is not executed and [condition2] is NoCap\nifBasic\t\nExecutes if both vibeCheck and isOff don’t get executed\t\tpeaceOut\nEnds the if loop\n\nnum i := 5;\nvibeCheck(i == 1)\n{\ngoOff i\n}\nifOff(i == 3)\n{\ngoOff i\n}\nifBasic\n{\ngoOff i\n}\npeaceOut;\n");
        put("ifOff","vibeCheck([condition])\nExecutes the code if the [condition] is NoCap\nifOff([condition2])\nExecutes if vibeCheck is not executed and [condition2] is NoCap\nifBasic\t\nExecutes if both vibeCheck and isOff don’t get executed\t\tpeaceOut\nEnds the if loop\n\nnum i := 5;\nvibeCheck(i == 1)\n{\ngoOff i\n}\nifOff(i == 3)\n{\ngoOff i\n}\nifBasic\n{\ngoOff i\n}\npeaceOut;\n");
        put("ifBasic","vibeCheck([condition])\nExecutes the code if the [condition] is NoCap\nifOff([condition2])\nExecutes if vibeCheck is not executed and [condition2] is NoCap\nifBasic\t\nExecutes if both vibeCheck and isOff don’t get executed\t\tpeaceOut\nEnds the if loop\n\nnum i := 5;\nvibeCheck(i == 1)\n{\ngoOff i\n}\nifOff(i == 3)\n{\ngoOff i\n}\nifBasic\n{\ngoOff i\n}\npeaceOut;\n");
        put("peaceOut","vibeCheck([condition])\nExecutes the code if the [condition] is NoCap\nifOff([condition2])\nExecutes if vibeCheck is not executed and [condition2] is NoCap\nifBasic\t\nExecutes if both vibeCheck and isOff don’t get executed\t\tpeaceOut\nEnds the if loop\n\nnum i := 5;\nvibeCheck(i == 1)\n{\ngoOff i\n}\nifOff(i == 3)\n{\ngoOff i\n}\nifBasic\n{\ngoOff i\n}\npeaceOut;\n");
    }};
    private static String getHelp(String token) throws IllegalArgumentException{
        if(information.contains(token)){
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
                getHelp(input);
            }catch (IllegalArgumentException e){
                System.out.println("Sorry we could not find information for the given method\nPlease choose from the following\n");
                System.out.println(stringVals);
            }
        }
    }
}
