package me.arthinking.question;

public class Q_04_compound_assignment_operators {

    public static void main(String[] args) {
        short x = 3;
        x += 4.6;  // E1 op= E2 is equivalent to E1 = (T)((E1) op (E2)), where T is the type of E1, except that E1 is evaluated only once.
        System.out.println(x);
        
        short y = 3;
        y = (short) (y + 4.6);
        System.out.println(y);
    }
}
