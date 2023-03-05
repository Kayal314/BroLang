import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Parser {

    public static Queue<String> convertInfixToPostfix(Queue<String> infix) {
        Queue<String> postfix=new LinkedList<>();
        Stack<String> operatorStack=new Stack<>();
        for(String token: infix) {
            token=token.trim();
            if(Operators.isNumber(token)||Operators.isPotentialVar(token))
                postfix.add(token);
            else if(Operators.isOperator(token)) {
                if(!"(".equals(token))
                    while(!operatorStack.isEmpty()&&Operators.compareOperators(operatorStack.peek(),token)>0)
                        postfix.add(operatorStack.pop());
                if(")".equals(token)) {
                    while (!operatorStack.isEmpty() && !operatorStack.peek().equals("("))
                        postfix.add(operatorStack.pop());
                    if(operatorStack.isEmpty())
                        throw new IllegalStateException("Unmatched Parenthesis");
                    operatorStack.pop();
                }
                else
                    operatorStack.push(token);
            }
        }

        while(!operatorStack.isEmpty())
            postfix.add(operatorStack.pop());

        return postfix;
    }

    public static Queue<String> convertStringToQueue(String expression) {
        Queue<String> infix=new LinkedList<>();
        int pos=0;
        for(int i=0;i<expression.length();i++) {
            char ch=expression.charAt(i);
            if(Operators.isOperator(String.valueOf(ch))) {
                if(ch!='(') {
                    String token = expression.substring(pos, i);
                    token = token.trim();
                    if (!token.isEmpty())
                        infix.add(token);
                }
                infix.add(String.valueOf(ch));
                pos=i+1;
            }
        }

        // add the last token if one exists (after the last check operator)
        if(pos<expression.length())
            infix.add(expression.substring(pos));

        return infix;
    }

    public static void main(String[] args) {
        Queue<String> l=convertStringToQueue("a+b/(c*d+556)+980");
        System.out.println(convertStringToQueue("a+b/(c*d+556)+980"));
        System.out.println(convertInfixToPostfix(l));
    }

}

