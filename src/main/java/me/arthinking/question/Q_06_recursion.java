package me.arthinking.question;

import java.util.LinkedList;
import java.util.List;

/**
 * 给一个string of nested ternary operations
 * 例如a?b?c:d:e
 * build a tree：root是a，左子树是b?c:d对应的tree ，右子树是e。
 * 保证input都是valid的。这题用recursion的话非常容易写, 如何用recursion去写？
 * @author  Jason Peng
 * @create date 2015年1月27日
 */
public class Q_06_recursion {

    public static void main(String[] args) {
        boolean a = true;
        boolean b = true;
        Object c = new Object();
        Object d = new Object();
        Object e = new Object();
        // nested ternary operations like below:
        Object obj = 1==1?2==3?c:d:e;
        // build a tree
        resolveOperation();
    }

    // If use JavaScript, you can use eval() to execute String expression
    // In Java, try to use Fel: http://code.google.com/p/fast-el/
    // or Groovy
    private static void resolveOperation(){
        /*
        FelEngine fel = new FelEngineImpl();  
        FelContext ctx = fel.getContext();
        Math.random();
        System.out.println(fel.eval("java.lang.Math.random()"));
        */
    }
    
    private static Tree buildTree(String func){
        Tree root = new Tree();
        return root;
    }
}

class Tree {
    List<Node> list = new LinkedList<Node>();
    
    public void addNode(Node n){
        list.add(n);
    }
}

class Node{
    private String nodeName;
    private Object value;
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
}