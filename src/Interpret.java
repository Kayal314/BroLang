
import java.util.*;

interface Keywords
{
    String IMPLEMENT="whereHeAt";
    String MATH="math";
    String MATH_FUNC="math:";
    String NUMBER="num";
    String BOOLEAN="boolean";
    String STRING ="string";
    String PRINT = "slay";
    String PRINTLN="slayQueen";
    String True="noCap";
    String False="Cap";
    String PLUS="+";
    String MINUS="-";
    String MULTIPLY="*";
    String DIVIDE="/";
    String MOD="mod";
    String POWER="**";
    String ASSIGN=":=";
    String AND="and";
    String OR="or";
    String NOT="nah";
    String XOR="xor";
    String LESS="<";
    String MORE=">";
    String LESS_OR_EQUAL="<=";
    String MORE_OR_EQUAL=">=";
    String EQUAL="==";
    String LEN="watchaPackin:";
    String GET_INDEX="<index>";
    String IF="vibeCheck";
    String ELSE="okBoomer";
    String ELSE_IF="bruhPlease";
    String ENDIF="peaceOut";
    String FOR="loopin";
    String WHILE="tillClapback";
    String SCAN="getSauce";
    String UNNAMED_REFERENCE ="[ERROR] : Unnamed Reference";
    String ARITHMETIC_EX = "[ERROR] : Arithmetic Exception";
    String INDEX_OUT_OF_BOUNDS="[ERROR] : String Index Out of Bounds Exception";
    String MATH_MODULE_UNIMPLEMENTED="[ERROR] : Math module not implemented";
    String EVAL="evalBro";
    String NO_SUCH_MODULE="[ERROR] : No such module exists";
    String UNKNOWN_ERROR = "[ERROR] : Unknown error";
    String END_PROGRAM="laterSkater";
}


public class Interpret extends MathFunc implements Keywords {
    final int MEMORY = 20;
    Hashtable<String, Double> memory_num = new Hashtable<>(MEMORY);
    Hashtable<String, String> memory_string = new Hashtable<>(MEMORY);
    Hashtable<String, Boolean> memory_bool = new Hashtable<>(MEMORY);
    Hashtable<String, Double> iterator_lim = new Hashtable<>(10);
    boolean if_block_execute = false;
    boolean else_block_execute = false;
    boolean else_if_block_execute = false;
    boolean f_if = false;
    boolean f_el_if = false;
    boolean hasMath = false;

    private void add(String sum, String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            memory_num.replace(sum, Double.parseDouble(var1) + Double.parseDouble(var2));
        else if (!isNumber(var1) && isNumber(var2))
            memory_num.replace(sum, memory_num.get(var1) + Double.parseDouble(var2));
        else if (isNumber(var1) && !isNumber(var2))
            memory_num.replace(sum, Double.parseDouble(var1) + memory_num.get(var2));
        else
            memory_num.replace(sum, memory_num.get(var1) + memory_num.get(var2));
    }

    private void subtract(String diff, String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            memory_num.replace(diff, Double.parseDouble(var1) - Double.parseDouble(var2));
        else if (!isNumber(var1) && isNumber(var2))
            memory_num.replace(diff, memory_num.get(var1) - Double.parseDouble(var2));
        else if (isNumber(var1) && !isNumber(var2))
            memory_num.replace(diff, Double.parseDouble(var1) - memory_num.get(var2));
        else
            memory_num.replace(diff, memory_num.get(var1) - memory_num.get(var2));
    }

    private void multiply(String product, String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            memory_num.replace(product, Double.parseDouble(var1) * Double.parseDouble(var2));
        else if (!isNumber(var1) && isNumber(var2))
            memory_num.replace(product, memory_num.get(var1) * Double.parseDouble(var2));
        else if (isNumber(var1) && !isNumber(var2))
            memory_num.replace(product, Double.parseDouble(var1) * memory_num.get(var2));
        else
            memory_num.replace(product, memory_num.get(var1) * memory_num.get(var2));
    }

    private void divide(String quotient, String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            memory_num.replace(quotient, Double.parseDouble(var1) / Double.parseDouble(var2));
        else if (!isNumber(var1) && isNumber(var2))
            memory_num.replace(quotient, memory_num.get(var1) / Double.parseDouble(var2));
        else if (isNumber(var1) && !isNumber(var2))
            memory_num.replace(quotient, Double.parseDouble(var1) / memory_num.get(var2));
        else
            memory_num.replace(quotient, memory_num.get(var1) / memory_num.get(var2));
    }

    private void mod(String remainder, String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            memory_num.replace(remainder, Double.parseDouble(var1) % Double.parseDouble(var2));
        else if (!isNumber(var1) && isNumber(var2))
            memory_num.replace(remainder, memory_num.get(var1) % Double.parseDouble(var2));
        else if (isNumber(var1) && !isNumber(var2))
            memory_num.replace(remainder, Double.parseDouble(var1) % memory_num.get(var2));
        else
            memory_num.replace(remainder, memory_num.get(var1) % memory_num.get(var2));
    }

    private void raise_to_power(String answer, String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            memory_num.replace(answer, Math.pow(Double.parseDouble(var1), Double.parseDouble(var2)));
        else if (!isNumber(var1) && isNumber(var2))
            memory_num.replace(answer, Math.pow(memory_num.get(var1), Double.parseDouble(var2)));
        else if (isNumber(var1) && !isNumber(var2))
            memory_num.replace(answer, Math.pow(Double.parseDouble(var1), memory_num.get(var2)));
        else
            memory_num.replace(answer, Math.pow(memory_num.get(var1), memory_num.get(var2)));
    }

    private void concat(String newString, String var1, String var2) {
        if (var1.charAt(0) == '"' && var2.charAt(0) == '"')
            memory_string.put(newString, var1.substring(1, var1.length() - 1) + var2.substring(1, var2.length() - 1));
        else if (var1.charAt(0) == '"' && Character.isLetter(var2.charAt(0)))
            memory_string.put(newString, var1.substring(1, var1.length() - 1) + memory_string.get(var2));
        else if (var2.charAt(0) == '"' && Character.isLetter(var1.charAt(0)))
            memory_string.put(newString, memory_string.get(var1) + var2.substring(1, var2.length() - 1));
        else
            memory_string.put(newString, memory_string.get(var1) + memory_string.get(var2));
    }

    private boolean and_op(String var1, String var2) {
        if (var1.equals(True) || var1.equals(False)) {
            if (var2.equals(False) || var2.equals(True))
                return Boolean.parseBoolean(var1) && Boolean.parseBoolean(var2);
            else
                return Boolean.parseBoolean(var1) && memory_bool.get(var2);
        } else {
            if (var2.equals(False) || var2.equals(True))
                return memory_bool.get(var1) && Boolean.parseBoolean(var2);
            else
                return memory_bool.get(var1) && memory_bool.get(var2);
        }
    }

    private boolean or_op(String var1, String var2) {
        if (var1.equals(True) || var1.equals(False)) {
            if (var2.equals(False) || var2.equals(True))
                return Boolean.parseBoolean(var1) || Boolean.parseBoolean(var2);
            else
                return Boolean.parseBoolean(var1) || memory_bool.get(var2);
        } else {
            if (var2.equals(False) || var2.equals(True))
                return memory_bool.get(var1) || Boolean.parseBoolean(var2);
            else
                return memory_bool.get(var1) || memory_bool.get(var2);
        }
    }

    private boolean xor_op(String var1, String var2) {
        if (var1.equals(True) || var1.equals(False)) {
            if (var2.equals(False) || var2.equals(True))
                return Boolean.parseBoolean(var1) ^ Boolean.parseBoolean(var2);
            else
                return Boolean.parseBoolean(var1) ^ memory_bool.get(var2);
        } else {
            if (var2.equals(False) || var2.equals(True))
                return memory_bool.get(var1) ^ Boolean.parseBoolean(var2);
            else
                return memory_bool.get(var1) ^ memory_bool.get(var2);
        }
    }

    private boolean not_op(String var) {
        if (var.equals(True) || var.equals(False))
            return !Boolean.parseBoolean(var);
        else
            return !memory_bool.get(var);
    }

    private boolean less_than(String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            return Double.parseDouble(var1) < Double.parseDouble(var2);
        else if (!isNumber(var1) && isNumber(var2))
            return memory_num.get(var1) < Double.parseDouble(var2);
        else if (isNumber(var1) && !isNumber(var2))
            return Double.parseDouble(var1) < memory_num.get(var2);
        else
            return memory_num.get(var1) < memory_num.get(var2);
    }

    private boolean more_than(String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            return Double.parseDouble(var1) > Double.parseDouble(var2);
        else if (!isNumber(var1) && isNumber(var2))
            return memory_num.get(var1) > Double.parseDouble(var2);
        else if (isNumber(var1) && !isNumber(var2))
            return Double.parseDouble(var1) > memory_num.get(var2);
        else
            return memory_num.get(var1) > memory_num.get(var2);
    }

    private boolean less_than_or_equal(String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            return Double.parseDouble(var1) <= Double.parseDouble(var2);
        else if (!isNumber(var1) && isNumber(var2))
            return memory_num.get(var1) <= Double.parseDouble(var2);
        else if (isNumber(var1) && !isNumber(var2))
            return Double.parseDouble(var1) <= memory_num.get(var2);
        else
            return memory_num.get(var1) <= memory_num.get(var2);
    }

    private boolean more_than_or_equal(String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            return Double.parseDouble(var1) >= Double.parseDouble(var2);
        else if (!isNumber(var1) && isNumber(var2))
            return memory_num.get(var1) >= Double.parseDouble(var2);
        else if (isNumber(var1) && !isNumber(var2))
            return Double.parseDouble(var1) >= memory_num.get(var2);
        else
            return memory_num.get(var1) >= memory_num.get(var2);
    }

    private boolean equal(String var1, String var2) {
        if (isNumber(var1) && isNumber(var2))
            return Double.parseDouble(var1) == Double.parseDouble(var2);
        else if (!isNumber(var1) && isNumber(var2))
            return memory_num.get(var1) == Double.parseDouble(var2);
        else if (isNumber(var1) && !isNumber(var2))
            return Double.parseDouble(var1) == memory_num.get(var2);
        else
            return memory_num.get(var1).equals(memory_num.get(var2));
    }

    private void assign_bool(StringTokenizer tk) {
        String variable = tk.nextToken();
        if (tk.hasMoreTokens()) {
            String operator = tk.nextToken();
            if (operator.equals(ASSIGN)) {
                String var_const = tk.nextToken();
                if (var_const.equals(True) || var_const.equals(False))
                    memory_bool.put(variable, Boolean.parseBoolean(var_const));
                else
                    memory_bool.put(variable, memory_bool.get(var_const));
            }
        } else memory_bool.put(variable, false); //case 1
    }

    private void assign_num(StringTokenizer tk) {
        String variable = tk.nextToken();
        if (tk.hasMoreTokens()) {
            String operator = tk.nextToken();
            if (operator.equals(ASSIGN)) {
                String var_const = tk.nextToken();
                if (isNumber(var_const))
                    memory_num.put(variable, Double.parseDouble(var_const));
                else if (var_const.equals(LEN)) {
                    String str = tk.nextToken();
                    int length = memory_string.get(str).length();
                    memory_num.put(variable, (double) length);
                } else
                    memory_num.put(variable, memory_num.get(var_const));

            }
            //case 2
        } else memory_num.put(variable, 0.0); //case 1
    }

    private void assign_Str(StringTokenizer tk) {
        String variable = tk.nextToken();
        if (tk.hasMoreTokens()) {
            String operator = tk.nextToken();
            if (operator.equals(ASSIGN)) {
                String var_const = tk.nextToken();
                /*tokens will me made using whitespace as delimiters
                 * but a string may have several spaces with it
                 * so we will merge all tokens till we get one string which begins with " and one string
                 * which ends with "
                 * Otherwise, it must be a variable if it does not start with "
                 * */
                if (var_const.charAt(0) == '"') {
                    StringBuilder sub_token = new StringBuilder(var_const);
                    while (sub_token.charAt(sub_token.length() - 1) != '"' || sub_token.length() == 1) {
                        sub_token.append(" ");
                        sub_token.append(tk.nextToken());
                    }
                    var_const = sub_token.toString();
                    memory_string.put(variable, var_const.substring(1, var_const.length() - 1));
                } else if (var_const.charAt(var_const.length() - 1) == ']') {
                    int arg_start = var_const.indexOf('[');
                    String arg = var_const.substring(arg_start + 1, var_const.length() - 1);
                    var_const = var_const.substring(0, arg_start);
                    StringTokenizer substringTk = new StringTokenizer(arg, ":");
                    if (substringTk.countTokens() == 2) // finding substrings
                    {
                        String start = substringTk.nextToken().trim();
                        String end = substringTk.nextToken().trim();
                        int start_index, end_index;
                        if (isNumber(start))
                            start_index = Integer.parseInt(start);
                        else
                            start_index = (int) memory_num.get(start).doubleValue();

                        if (isNumber(end))
                            end_index = Integer.parseInt(end);
                        else if (end.equals("*"))
                            end_index = memory_string.get(var_const).length();
                        else
                            end_index = (int) memory_num.get(end).doubleValue();

                        memory_string.put(variable, memory_string.get(var_const).substring(start_index, end_index));
                    } else if (substringTk.countTokens() == 1) {
                        String start = substringTk.nextToken().trim();
                        int pos;
                        if (isNumber(start))
                            pos = Integer.parseInt(start);
                        else
                            pos = (int) memory_num.get(start).doubleValue();
                        memory_string.put(variable, memory_string.get(var_const).substring(pos, pos + 1));
                    }
                } else {
                    System.out.println(var_const);
                    memory_string.put(variable, memory_string.get(var_const));
                }
            }
        } else memory_string.put(variable, ""); //case 1
    }

    private boolean if_else_compare(String tk1, String tk2, String compare) {
        boolean result = false;
        switch (compare) {
            case AND:
                result = and_op(tk1, tk2);
                break;
            case OR:
                result = or_op(tk1, tk2);
                break;
            case XOR:
                result = xor_op(tk1, tk2);
                break;
            case LESS:
                result = less_than(tk1, tk2);
                break;
            case MORE:
                result = more_than(tk1, tk2);
                break;
            case LESS_OR_EQUAL:
                result = less_than_or_equal(tk1, tk2);
                break;
            case MORE_OR_EQUAL:
                result = more_than_or_equal(tk1, tk2);
                break;
            case EQUAL:
                result = equal(tk1, tk2);
                break;
        }
        return result;
    }

    Double evalNum(String line) {
        Queue<String> postFix = Parser.convertInfixToPostfix(Parser.convertStringToQueue(line.trim()));
        Stack<Double> evaluator = new Stack<>();
        for (String token : postFix) {

            if (Operators.isOperator(token)) {
                if (evaluator.size() < 2)
                    throw new IllegalArgumentException("Cannot parse the expression");
                double d1 = evaluator.pop();
                double d2 = evaluator.pop();
                switch (token) {
                    case "+" -> evaluator.push(d2 + d1);
                    case "-" -> evaluator.push(d2 - d1);
                    case "*" -> evaluator.push(d2 * d1);
                    case "/" -> evaluator.push(d2 / d1);
                    case "|" -> evaluator.push((double) ((int)d2 | (int)d1));
                    case "&" -> evaluator.push((double) ((int)d2 & (int)d1));
                    case "^" -> evaluator.push((double) ((int)d2 ^ (int)d1));
                }
            } else if (Operators.isNumber(token))
                evaluator.push(Double.parseDouble(token));
            else if (Operators.isPotentialVar(token)) {
                if (memory_num.containsKey(token))
                    evaluator.push(memory_num.get(token));
                else
                    throw new IllegalArgumentException("Unknown Variable");
            }
        }

        if (evaluator.size() > 1)
            throw new IllegalArgumentException("Cannot parse the expression");
        return evaluator.pop();
    }
    private int ln=0;
    private void execute(String[] codes, StringTokenizer tk, int i) {
        try {
            while (tk.hasMoreTokens()) {
                String token = tk.nextToken();
                if (token.startsWith("#")) // comments
                    return;
                /*
                 * if the first token is Int, Float, Bool, or Str then the user must either be declaring a variable
                 * or declaring and initializing the variable
                 * if just declaring, then put the variable in the respective hashtable with default value  ---> case 1
                 * else put it with the given value ---> case 2
                 **/
                switch (token) {
                    case IMPLEMENT:
                        String module = tk.nextToken();
                        if (module.equals(MATH))
                            hasMath = true;
                        else
                            System.out.println(NO_SUCH_MODULE);
                        break;
                    case NUMBER:
                        assign_num(tk);
                        break;
                    case BOOLEAN:
                        assign_bool(tk);
                        break;
                    case STRING:
                        assign_Str(tk);
                        break;
                    case PRINT:
                        ln=1;
                    case PRINTLN:
                        if(ln==0)
                            ln=2;
                        String to_be_printed = tk.nextToken();

                        /*
                         * extract the string to be printed from the token
                         * if the token begins and ends with double quote
                         * else:
                         * if its a number then just print
                         * else:
                         * must be a variable
                         * */
                        if (to_be_printed.charAt(0) == '"') {
                            StringBuilder sub_token = new StringBuilder(to_be_printed);
                            while (sub_token.charAt(sub_token.length() - 1) != '"' || sub_token.length() == 1) {
                                sub_token.append(" ");
                                sub_token.append(tk.nextToken());
                            }
                            to_be_printed = sub_token.toString();
                            to_be_printed = to_be_printed.substring(1, to_be_printed.length() - 1);
                            System.out.print(to_be_printed);
                        } else if (to_be_printed.equals(True) || to_be_printed.equals(False))
                            System.out.print(to_be_printed);

                        else {
                            // its either a non-string constant or a variable
                            if (memory_num.get(to_be_printed) != null)
                                System.out.print(memory_num.get(to_be_printed));
                            else if (memory_string.get(to_be_printed) != null)
                                System.out.print(memory_string.get(to_be_printed));
                            else if (memory_bool.get(to_be_printed) != null)
                                System.out.print(memory_bool.get(to_be_printed));

                            else {
                                if (isNumber(to_be_printed))
                                    System.out.print(to_be_printed);
                                if(Operators.isBooleanConstant(to_be_printed))
                                    System.out.print(to_be_printed);
                                else {
                                    System.out.println(UNNAMED_REFERENCE);
                                    return;
                                }
                            }
                        }
                        if(ln==2)
                            System.out.println();
                        ln=0;
                        break;
                    case IF:
                        String tk1 = tk.nextToken();
                        String compare = tk.nextToken();
                        String tk2 = tk.nextToken();
                        boolean result = if_else_compare(tk1, tk2, compare);

                        if (result) {
                            if_block_execute = true;
                        }
                        break;

                    case ELSE_IF:
                        if (!f_if) {
                            tk1 = tk.nextToken();
                            compare = tk.nextToken();
                            tk2 = tk.nextToken();
                            boolean rresult = if_else_compare(tk1, tk2, compare);
                            if (rresult)
                                else_if_block_execute = true;

                            break;
                        }
                    case ELSE:
                        else_block_execute = !f_if && !f_el_if;
                        break;
                    case ENDIF:

                        else_block_execute = false;
                        else_if_block_execute = false;
                        f_if = false;
                        f_el_if = false;
                        break;
                    case SCAN:
                        if(!tk.hasMoreTokens()) {
                            System.out.println("[ERROR]: Incorrect syntax for " + SCAN+". Type not specified");
                            return;
                        }
                        String type=tk.nextToken().trim();
                        Scanner sc=new Scanner(System.in);
                        if(!tk.hasMoreTokens()) {
                            System.out.println("[ERROR]: Incorrect syntax for " + SCAN+". Variable name not specified");
                            return;
                        }
                        String varName=tk.nextToken().trim();
                        try {
                            switch (type) {
                                case "%s" -> memory_string.replace(varName, sc.nextLine());
                                case "%n" -> memory_num.replace(varName, sc.nextDouble());
                                case "%b" -> memory_bool.replace(varName, Operators.literalToBoolean(sc.next().trim()));
                                default -> {
                                    System.out.println("[ERROR]: Unknown identifier");
                                    return;
                                }
                            }
                        }
                        catch (NoSuchElementException ex) {
                            System.out.println("[ERROR]: Type mismatch");
                            return;
                        }
                        break;


                    case EVAL:
                        String v = tk.nextToken();
                        tk.nextToken();
                        StringBuilder evalExp = new StringBuilder();
                        while (tk.hasMoreTokens())
                            evalExp.append(tk.nextToken());
                        memory_num.replace(v, evalNum(evalExp.toString()));
                        break;

                    case FOR:
                        String variable = tk.nextToken();
                        tk.nextToken();
                        String arg = tk.nextToken();
                        arg = arg.substring(1, arg.length() - 1);
                        StringTokenizer arg_tk = new StringTokenizer(arg, ",");
                        String start = arg_tk.nextToken().trim();
                        if (isNumber(start))
                            memory_num.replace(variable, Double.parseDouble(start));
                        else
                            memory_num.replace(variable, memory_num.get(start));
                        String end_str = arg_tk.nextToken().trim();
                        double end;
                        if (isNumber(end_str))
                            end = Double.parseDouble(end_str);
                        else
                            end = memory_num.get(end_str);
                        double step = 1;
                        if (arg_tk.hasMoreTokens()) {
                            String step_str = arg_tk.nextToken().trim();
                            if (isNumber(step_str))
                                step = Double.parseDouble(step_str);
                            else
                                step = memory_num.get(step_str);
                        }
                        iterator_lim.put(variable, end);
                        // collect the iterated code
                        StringBuilder iterated_codes = new StringBuilder();
                        boolean end_of_for = true;
                        i += 2;
                        while (!(codes[i].equals("}") && end_of_for)) {

                            String line = codes[i];
                            if (line.equals("{"))
                                end_of_for = false;
                            else if (line.equals("}"))
                                end_of_for = true;
                            iterated_codes.append(line).append("\n");
                            i++;
                        }

                        if (step >= 0) {
                            while (memory_num.get(variable) <= iterator_lim.get(variable)) {
                                interpret(iterated_codes.toString());
                                memory_num.replace(variable, memory_num.get(variable) + step);
                            }
                        } else {
                            while (memory_num.get(variable) >= iterator_lim.get(variable)) {
                                interpret(iterated_codes.toString());
                                memory_num.replace(variable, memory_num.get(variable) + step);
                            }
                        }
                        break;

                    case WHILE:
                        boolean end_of_while = true;
                        i += 2;
                        StringBuilder iterated_whiles = new StringBuilder();
                        while (!(codes[i].equals("}") && end_of_while)) {

                            String line = codes[i];
                            if (line.equals("{"))
                                end_of_while = false;
                            else if (line.equals("}"))
                                end_of_while = true;
                            iterated_whiles.append(line).append("\n");
                            i++;
                        }

                        String single_cond = "";
                        String val1 = "", cond = "", val2 = "";
                        int tokens_count = tk.countTokens();
                        if (tokens_count == 1) {
                            single_cond = tk.nextToken();
                            while (Boolean.parseBoolean(single_cond.trim()))
                                interpret(iterated_whiles.toString());
                        } else if (tokens_count == 2) {
                            val1 = tk.nextToken();
                            cond = tk.nextToken();
                            while (condition_check(val1, cond, val2))
                                interpret(iterated_whiles.toString());
                        } else {
                            val1 = tk.nextToken();
                            cond = tk.nextToken();
                            val2 = tk.nextToken();
                            while (condition_check(val1, cond, val2))
                                interpret(iterated_whiles.toString());
                        }

                        break;


                    case "{":
                    case "}":
                        break;
                    default:
                        if (token.equals("end"))
                            return;
                        else if (tk.countTokens() == 2) // = 4
                        {
                            String reformed_line = tk.nextToken();
                            reformed_line = reformed_line + " " + tk.nextToken();
                            reformed_line = token + " " + reformed_line;
                            if (memory_num.get(token) != null)
                                assign_num(new StringTokenizer(reformed_line));
                            else if (memory_string.get(token) != null)
                                assign_Str(new StringTokenizer(reformed_line));
                            else if (memory_bool.get(token) != null)
                                assign_bool(new StringTokenizer(reformed_line));
                        } else if (tk.countTokens() == 3) {
                            tk.nextToken();
                            String var1 = tk.nextToken();
                            String var2 = tk.nextToken();
                            if (var1.equals(MATH_FUNC)) {
                                if (hasMath)
                                    memory_num.replace(token, math_functions(var2, 0.0));
                                else
                                    System.out.println(MATH_MODULE_UNIMPLEMENTED);

                            } else if (var1.equals(LEN)) {
                                int length = memory_string.get(var2).length();
                                memory_num.replace(token, (double) length);
                            }

                        } else {
                            tk.nextToken();
                            if (memory_num.get(token) != null) {
                                String var1 = tk.nextToken();
                                String op2 = tk.nextToken();
                                String var2 = tk.nextToken();
                                if (var1.equals(MATH_FUNC)) {
                                    double num;
                                    if (isNumber(var2))
                                        num = Double.parseDouble(var2);
                                    else
                                        num = memory_num.get(var2);
                                    if (hasMath)
                                        memory_num.replace(token, math_functions(op2, num));
                                    else
                                        System.out.println(MATH_MODULE_UNIMPLEMENTED);
                                } else if (op2.equals(GET_INDEX)) {
                                    String a = memory_string.get(var1);
                                    String b = memory_string.get(var2);
                                    int index = a.indexOf(b);
                                    memory_num.replace(token, (double) index);
                                } else
                                    switch (op2) {
                                        case PLUS:
                                            add(token, var1, var2);
                                            break;
                                        case MINUS:
                                            subtract(token, var1, var2);
                                            break;
                                        case MULTIPLY:
                                            multiply(token, var1, var2);
                                            break;
                                        case DIVIDE:
                                            divide(token, var1, var2);
                                            break;
                                        case MOD:
                                            mod(token, var1, var2);
                                            break;
                                        case POWER:
                                            raise_to_power(token, var1, var2);
                                            break;
                                    }

                            } else if (memory_string.get(token) != null) {
                                String var1 = tk.nextToken();
                                if (var1.charAt(0) == '"') {
                                    StringBuilder sub_token = new StringBuilder(var1);
                                    while (sub_token.charAt(sub_token.length() - 1) != '"' || sub_token.length() == 1) {
                                        sub_token.append(" ");
                                        sub_token.append(tk.nextToken());
                                    }
                                    var1 = sub_token.toString();
                                }
                                String op2 = tk.nextToken();
                                String var2 = tk.nextToken();
                                if (var2.charAt(0) == '"') {
                                    StringBuilder sub_token = new StringBuilder(var2);
                                    while (sub_token.charAt(sub_token.length() - 1) != '"' || sub_token.length() == 1) {
                                        sub_token.append(" ");
                                        sub_token.append(tk.nextToken());
                                    }
                                    var2 = sub_token.toString();
                                }
                                if (op2.equals(PLUS))
                                    concat(token, var1, var2);
                            } else if (memory_bool.get(token) != null) {
                                if (tk.countTokens() == 3) {
                                    String var1 = tk.nextToken();
                                    String op = tk.nextToken();
                                    String var2 = tk.nextToken();
                                    switch (op) {
                                        case AND:
                                            memory_bool.replace(token, and_op(var1, var2));
                                            break;
                                        case OR:
                                            memory_bool.replace(token, or_op(var1, var2));
                                            break;
                                        case XOR:
                                            memory_bool.replace(token, xor_op(var1, var2));
                                            break;
                                        case LESS:
                                            memory_bool.replace(token, less_than(var1, var2));
                                            break;
                                        case MORE:
                                            memory_bool.replace(token, more_than(var1, var2));
                                            break;
                                        case MORE_OR_EQUAL:
                                            memory_bool.replace(token, more_than_or_equal(var1, var2));
                                            break;
                                        case LESS_OR_EQUAL:
                                            memory_bool.replace(token, less_than_or_equal(var1, var2));
                                            break;
                                        case EQUAL:
                                            memory_bool.replace(token, equal(var1, var2));
                                            break;
                                    }
                                } else if (tk.countTokens() == 2) {
                                    String operator = tk.nextToken();
                                    String var = tk.nextToken();
                                    if (operator.equals(NOT))
                                        memory_bool.replace(token, not_op(var));
                                }

                            }

                        }


                        break;
                }


            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            System.out.println(ARITHMETIC_EX);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println(INDEX_OUT_OF_BOUNDS);
        } catch (Exception e) {
            System.out.println(UNKNOWN_ERROR);
        }
    }

    boolean condition_check(String var1, String op, String var2) {
        switch (op) {
            case AND:
                return and_op(var1, var2);
            case OR:
                return or_op(var1, var2);
            case XOR:
                return xor_op(var1, var2);
            case LESS:
                return less_than(var1, var2);
            case MORE:
                return more_than(var1, var2);
            case MORE_OR_EQUAL:
                return more_than_or_equal(var1, var2);
            case LESS_OR_EQUAL:
                return less_than_or_equal(var1, var2);
            case EQUAL:
                return equal(var1, var2);
            case NOT:
                return not_op(var1);
        }
        return false;
    }

    public void interpret(String program) {
        String[] codes = program.split("\n");
        for (int i = 0; i < codes.length; i++) {

            if (if_block_execute) {
                if (codes[i].trim().equals("}")) {
                    if_block_execute = false;
                    f_if = true;
                    continue;
                }
                if (codes[i].trim().equals("{"))
                    execute(codes, new StringTokenizer(codes[++i]), i);
                else
                    execute(codes, new StringTokenizer(codes[i]), i);
            } else if (else_if_block_execute && !f_el_if) {
                if (codes[i].trim().equals("}")) {
                    else_if_block_execute = false;
                    f_el_if = true;
                    continue;
                }
                if (codes[i].trim().equals("{"))
                    execute(codes, new StringTokenizer(codes[++i]), i);
                else
                    execute(codes, new StringTokenizer(codes[i]), i);
            } else if (else_block_execute) {
                f_if = f_el_if = false;
                if (codes[i].trim().equals("}")) {
                    else_block_execute = false;
                    continue;
                }
                if (codes[i].trim().equals("{"))
                    execute(codes, new StringTokenizer(codes[++i]), i);
                else
                    execute(codes, new StringTokenizer(codes[i]), i);
            } else {
                String line = codes[i];
                if (line.trim().equals("{")) {
                    if (codes[i - 1].trim().equals(IF))
                        f_if = false;
                    else if (codes[i - 1].trim().equals(ELSE))
                        f_el_if = false;
                    while (!codes[i].equals("}"))
                        i++;
                    i++;
                }
                line = codes[i];
                execute(codes, new StringTokenizer(line), i);
                if (line.trim().startsWith(FOR)) {
                    boolean end_of_for = true;
                    i += 2;
                    while (!(codes[i].equals("}") && end_of_for)) {

                        String for_line = codes[i];
                        if (for_line.equals("{"))
                            end_of_for = false;
                        else if (for_line.equals("}"))
                            end_of_for = true;
                        i++;
                    }

                } else if (line.trim().startsWith(WHILE)) {
                    boolean end_of_while = true;
                    i += 2;
                    while (!(codes[i].equals("}") && end_of_while)) {

                        String for_line = codes[i];
                        if (for_line.equals("{"))
                            end_of_while = false;
                        else if (for_line.equals("}"))
                            end_of_while = true;
                        i++;
                    }

                }
            }
        }

    }

    boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException exception) {
            // if it can't be parsed to double then its not a number
            return false;
        }
    }

    public static void main(String[] args)  {
        Scanner sc=new Scanner(System.in);
        System.out.println("_____");
        StringBuilder code= new StringBuilder();
        String space="";
        while(true) {
            String line=sc.nextLine().trim();
            if(line.equals("{"))
                space+="  ";
            else if(line.equals("}"))
                space=space.substring(0,space.length()-2);
            else if(line.equals(END_PROGRAM))
                break;
            code.append(line);
            code.append("\n");
            System.out.print(space);
        }
        System.out.println("_____");
        new Interpret().interpret(code.toString());
    }

}