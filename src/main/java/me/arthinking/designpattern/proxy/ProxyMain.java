package me.arthinking.designpattern.proxy;

public class ProxyMain {

	public static void main(String[] args){
		LuceneSearcher searcher = new LuceneSearcher();
		searcher.searchSong(1);
	}
}
