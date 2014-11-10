package me.arthinking.question;

import net.sf.json.JSONObject;

/**
 * fastjson-1.1.27.jar
 * json-lib
 * 
 * @author  Jason Peng
 * @create date 2014Äê10ÔÂ24ÈÕ
 */
public class Q_02_json {

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("status", 1);
        object.put("msg","aasdfa	");
        System.out.println(object.toString());
        System.out.println(quote("abc	"));
    }
    
    public static String quote(String string)
    {
      if ((string == null) || (string.length() == 0)) {
        return "\"\"";
      }

      char c = '\'';

      int len = string.length();
      StringBuffer sb = new StringBuffer(len * 2);

      char[] chars = string.toCharArray();
      char[] buffer = new char[1030];
      int bufferIndex = 0;
      sb.append('"');
      for (int i = 0; i < len; ++i) {
        if (bufferIndex > 1024) {
          sb.append(buffer, 0, bufferIndex);
          bufferIndex = 0;
        }
        char b = c;
        c = chars[i];
        switch (c)
        {
        case '"':
        case '\\':
          buffer[(bufferIndex++)] = '\\';
          buffer[(bufferIndex++)] = c;
          break;
        case '/':
          if (b == '<')
            buffer[(bufferIndex++)] = '\\';

          buffer[(bufferIndex++)] = c;
          break;
        default:
          if (c < ' ')
            switch (c)
            {
            case '\b':
              buffer[(bufferIndex++)] = '\\';
              buffer[(bufferIndex++)] = 'b';
              break;
            case '\t':
              buffer[(bufferIndex++)] = '\\';
              buffer[(bufferIndex++)] = 't';
              break;
            case '\n':
              buffer[(bufferIndex++)] = '\\';
              buffer[(bufferIndex++)] = 'n';
              break;
            case '\f':
              buffer[(bufferIndex++)] = '\\';
              buffer[(bufferIndex++)] = 'f';
              break;
            case '\r':
              buffer[(bufferIndex++)] = '\\';
              buffer[(bufferIndex++)] = 'r';
              break;
            default:
              String t = "000" + Integer.toHexString(c);
              int tLength = t.length();
              buffer[(bufferIndex++)] = '\\';
              buffer[(bufferIndex++)] = 'u';
              buffer[(bufferIndex++)] = t.charAt(tLength - 4);
              buffer[(bufferIndex++)] = t.charAt(tLength - 3);
              buffer[(bufferIndex++)] = t.charAt(tLength - 2);
              buffer[(bufferIndex++)] = t.charAt(tLength - 1);
            }
          else
            buffer[(bufferIndex++)] = c;
        }
      }
      
      for(char cc : buffer){
          System.out.println(cc);
      }
      
      sb.append(buffer, 0, bufferIndex);
      sb.append('"');
      return sb.toString();
    }

}
