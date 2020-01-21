
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 与String相关的工具方法集
 * @author Jimmy
 *
 */
public class StringUtil {

    public static String getMatcher(String regex, String source) {
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }

    public static Pattern compile(String regex, boolean isInsensitive) {
        if (true == isInsensitive) {
            return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            return Pattern.compile(regex);
        }
    }

    public static Matcher matcher(String regex, String content) {
        return compile(regex, true).matcher(content);
    }

    public static List<String> matchAll(String regex, String content) {
        List<String> list = new LinkedList<String>();
        Matcher m = matcher(regex, content);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }


    public static void main(String[] args) {
        String url = "logoUrl=http://uat99image2.huaruntong.cn:10000/http://uat99image2.huaruntong.cn:10000/group1/M00/02/F2/CgDIKVspxhyAQmGdAAAM-f4hWhc522.png?token=7d3a3baed25735b805b4e8c383684173&ts=1557368534\n";
        String regex = "((http|https)://){1}((\\d+\\.\\d+\\.\\d+\\.\\d+)|([\\w-]+\\.[\\w-]+\\.[\\w-]+))(:\\d+)?/";
//        String regex = "(\\d{1,3}\\.){1,3}(\\d{1,3})";
        System.out.println(getMatcher(regex,url));
        System.out.println(matchAll(regex,url));
    }

}