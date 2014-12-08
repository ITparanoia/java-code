package me.arthinking.designpattern.proxy;

import java.util.ArrayList;
import java.util.List;

public class LuceneSearcher implements Searcher{

	@Override
	public List<String> searchSong(int songType) {
		List<String> result = new ArrayList<String>();
		result.add("Wings you are the hero");
		return result;
	}
}
