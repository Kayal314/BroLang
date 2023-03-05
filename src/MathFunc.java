public abstract class MathFunc {
    final String ABS="abs:";
    final String CEIL="ceil:";
    final String FLOOR="floor:";
    final String ROUND="round:";
    final String SIN="sin:";
    final String COS="cos";
    final String TAN="tan:";
    final String COSEC="cosec:";
    final String SEC="sec:";
    final String COT="cot:";
    final String LOG="log:";
    final String LN="ln:";
    final String ARCSIN="arcsin:";
    final String ARCCOS="arccos:";
    final String ARCTAN="arctan:";
    final String TO_DEG="todeg:";
    final String TO_RAD="torad:";
    final String EULER_CONST="E";
    final String PI_CONST="PI";

    double math_functions(String op, Double num)
    {
       switch (op)
       {
           case ABS:
               return Math.abs(num);
           case CEIL:
               return Math.ceil(num);
           case FLOOR:
               return Math.floor(num);
           case ROUND:
               return Math.round(num);
           case SIN:
               return Math.sin(num);
           case COS:
               return Math.cos(num);
           case TAN:
               return Math.tan(num);
           case COSEC:
               return 1.0/Math.sin(num);
           case SEC:
               return 1.0/Math.cos(num);
           case COT:
               return 1.0/Math.tan(num);
           case LOG:
               return Math.log10(num);
           case LN:
               return Math.log(num);
           case ARCSIN:
               return Math.asin(num);
           case ARCCOS:
               return Math.acos(num);
           case ARCTAN:
               return Math.atan(num);
           case TO_DEG:
               return Math.toDegrees(num);
           case TO_RAD:
               return Math.toRadians(num);
           case EULER_CONST:
               return Math.E;
           case PI_CONST:
               return Math.PI;

       }
       return 0.0;
    }
}
