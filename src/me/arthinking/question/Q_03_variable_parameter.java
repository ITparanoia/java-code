package me.arthinking.question;

import java.util.ArrayList;
import java.util.List;

public class Q_03_variable_parameter {

    public static void testVariableParam(Object ...param){
        for(Object str : param){
            System.out.println(str);
        }
    }
    
    public static void main(String[] args) {
        List<Object> param = new ArrayList<Object>();
        param.add("a");
        param.add("b");
        testVariableParam(param.toArray());
        /* output:
        a
        b
         */
        testVariableParam("arthinking", param.toArray());
        /* output:
        arthinking
        [Ljava.lang.Object;@c17164
         */
    }
}
