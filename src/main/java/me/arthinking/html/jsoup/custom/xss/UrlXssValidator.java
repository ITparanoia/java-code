package me.arthinking.html.jsoup.custom.xss;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Attribute;

public class UrlXssValidator implements XssValidator{

	// private static Pattern onsiteUrlPattern = null;
	
	private static Pattern offsiteUrlPattern = null;
	
	static {
	    /**
	     * \p{L} matches a single code point in the category "letter".
         * \p{N} matches any kind of numeric character in any script.
	     */
		// String onsiteURL = "([\\p{L}\\p{N}\\\\\\.\\#@\\$%\\+&;\\-_~,\\?=/!]+|\\#(\\w)+)";
		String offsiteURL = "(\\s)*((ht|f)tp(s?)://|mailto:)[\\p{L}\\p{N}]+[\\p{L}\\p{N}\\p{Zs}\\.\\#@\\$%\\+&;:\\-_~,\\?=/!\\(\\)]*(\\s)*";
		// onsiteUrlPattern = Pattern.compile(onsiteURL);
		offsiteUrlPattern = Pattern.compile(offsiteURL);
	}
	
	@Override
	public boolean validateAttribute(Attribute attr) {
		if(attr.getKey().equals("src") || attr.getKey().equals("href")){
			// Matcher onsiteUrlMatcher = onsiteUrlPattern.matcher(attr.getValue());
			Matcher offsiteUrlMatcher = offsiteUrlPattern.matcher(attr.getValue());
	        if(/*!onsiteUrlMatcher.find() && */ !offsiteUrlMatcher.find()){
	        	return false;
	        }
		}
		return true;
	}
}
