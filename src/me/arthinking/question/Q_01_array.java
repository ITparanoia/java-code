package me.arthinking.question;

import java.util.Arrays;

/**
 * How to test if an Array contains a certain value in a simple way
 * @author  Jason Peng
 * @create date 2014Äê10ÔÂ17ÈÕ
 */
public class Q_01_array {

    public static void main(String[] args) {
        String[] array = new String[]{"ab", "bc", "d", "e"};
        System.out.println(Arrays.asList(array).contains("d"));  // true
        
        int[] intArray = new int[]{1, 3, 5, 7, 9};
        System.out.println(Arrays.asList(intArray).contains(1));  // false
        System.out.println(Arrays.asList(intArray));  // [[I@de6ced]  returns a 'list<int[]>'
        Integer[] intArray2 = new Integer[]{2, 4, 6, 8};
        System.out.println(Arrays.asList(intArray2).contains(2));  // true
    }
    
}