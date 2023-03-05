import java.util.Hashtable;

public class Operators {

    private static final Hashtable<String, Integer> operators = new Hashtable<>() {{
        put("+",1);
        put("-",2);
        put("*",3);
        put("/",4);
        put("|",5);
        put("&",6);
        put("^",7);
        put("(",0);
        put(")",0);
    }};

    public static boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    public static int compareOperators(String t1, String t2) throws IllegalArgumentException {
        if(!(operators.containsKey(t1)&&operators.containsKey(t2)))
            throw new IllegalArgumentException("Only operators are comparable");
        return operators.get(t1)-operators.get(t2);
    }

    public static boolean isBooleanConstant(String token){
        token = token.trim();
        return token.equals("Cap") || token.equals("NoCap");
    }
    public static boolean literalToBoolean(String token){
        return !token.equals("Cap");
    }

    public static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        }
        catch(NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isPotentialVar(String token) {
        if(token.isBlank())
            return false;
        return Character.isLetter(token.charAt(0));
    }

    public static void main(String[] args) {
        System.out.println(2^2);
    }
}
