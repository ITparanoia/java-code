package me.arthinking.html.jsoup.custom.demo;

import java.util.Iterator;

import org.jsoup.helper.DescendableLinkedList;
import org.jsoup.helper.StringUtil;


public class DescendableLinkedListTest {

	public static void main(String[] args){
		
		DescendableLinkedList<String> list = new DescendableLinkedList<String>();
		list.add("a");
		list.add("b");
		Iterator<String> iterator =list.descendingIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		StringUtil.padding(-1);
		System.out.println("asdf");
	}
}
