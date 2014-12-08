package me.arthinking.designpattern.proxy;

import java.util.List;

public class CustomSearcher implements Searcher{

	private Searcher searcher;
	
	public CustomSearcher(Searcher searcher){
		this.searcher = searcher;
	}
	
	@Override
	public List<String> searchSong(int songType) {
		List<String> result = searcher.searchSong(songType);
		result.add("big blue ocean");
		return result;
	}

}
