package cn.ayub.ratingScore.commons.utils;
import com.baomidou.mybatisplus.toolkit.StringEscape;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.baomidou.mybatisplus.toolkit.StringUtils.match;
import static com.baomidou.mybatisplus.toolkit.StringUtils.sqlParam;

/**
 * 继承自Spring util的工具类，减少jar依赖
 * @author L.cm
 */
public class StringUtils extends org.springframework.util.StringUtils {
    /**
     * 空字符
     */
    public static final String EMPTY = "";

    /**
     * 下划线字符
     */
    public static final char UNDERLINE = '_';

    /**
     * 占位符
     */
    public static final String PLACE_HOLDER = "{%s}";

    /**
     * <p>
     * 判断字符串是否为空
     * </p>
     *
     * @param cs 需要判断字符串
     * @return 判断结果
     */
    public static boolean isEmpty(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * 字符串驼峰转下划线格式
     * </p>
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String camelToUnderline(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append(UNDERLINE);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * <p>
     * 字符串下划线转驼峰格式
     * </p>
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String underlineToCamel(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        String temp = param.toLowerCase();
        int len = temp.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = temp.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(temp.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * 判断字符串是否为纯大写字母
     * </p>
     *
     * @param str 要匹配的字符串
     * @return
     */
    public static boolean isUpperCase(String str) {
        return match("^[A-Z]+$", str);
    }
    /**
     * <p>
     * 正则表达式匹配
     * </p>
     *
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    /**
     * <p>
     * SQL 参数填充
     * </p>
     *
     * @param content 填充内容
     * @param args    填充参数
     * @return
     */
    public static String sqlArgsFill(String content, Object... args) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        if (args != null) {
            int length = args.length;
            if (length >= 1) {
                for (int i = 0; i < length; i++) {
                    content = content.replace(String.format(PLACE_HOLDER, i), sqlParam(args[i]));
                }
            }
        }
        return content;
    }
    /**
     * 获取SQL PARAMS字符串
     *
     * @param obj
     * @return
     */
    public static String sqlParam(Object obj) {
        String repStr;
        if (obj instanceof Collection) {
            repStr = StringUtils.quotaMarkList((Collection<?>) obj);
        } else {
            repStr = StringUtils.quotaMark(obj);
        }
        return repStr;
    }
    /**
     * <p>
     * 使用单引号包含字符串
     * </p>
     *
     * @param obj 原字符串
     * @return 单引号包含的原字符串
     */
    public static String quotaMark(Object obj) {
        String srcStr = String.valueOf(obj);
        if (obj instanceof CharSequence) {
            // fix #79
            return StringEscape.escapeString(srcStr);
        }
        return srcStr;
    }

    /**
     * <p>
     * 使用单引号包含字符串
     * </p>
     *
     * @param coll 集合
     * @return 单引号包含的原字符串的集合形式
     */
    public static String quotaMarkList(Collection<?> coll) {
        StringBuilder sqlBuild = new StringBuilder();
        sqlBuild.append("(");
        int _size = coll.size();
        int i = 0;
        Iterator<?> iterator = coll.iterator();
        while (iterator.hasNext()) {
            String tempVal = StringUtils.quotaMark(iterator.next());
            sqlBuild.append(tempVal);
            if (i + 1 < _size) {
                sqlBuild.append(",");
            }
            i++;
        }
        sqlBuild.append(")");
        return sqlBuild.toString();
    }

    /**
     * <p>
     * 拼接字符串第二个字符串第一个字母大写
     * </p>
     *
     * @param concatStr
     * @param str
     * @return
     */
    public static String concatCapitalize(String concatStr, final String str) {
        if (isEmpty(concatStr)) {
            concatStr = EMPTY;
        }
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            // already capitalized
            return str;
        }

        StringBuilder sb = new StringBuilder(strLen);
        sb.append(concatStr);
        sb.append(Character.toTitleCase(firstChar));
        sb.append(str.substring(1));
        return sb.toString();
    }

    /**
     * <p>
     * 字符串第一个字母大写
     * </p>
     *
     * @param str
     * @return
     */
    public static String capitalize(final String str) {
        return concatCapitalize(null, str);
    }

    /**
     * <p>
     * 判断对象是否为空
     * </p>
     *
     * @param object
     * @return
     */
    public static boolean checkValNotNull(Object object) {
        if (object instanceof CharSequence) {
            return isNotEmpty((CharSequence) object);
        }
        return object != null;
    }

    /**
     * <p>
     * 判断对象是否为空
     * </p>
     *
     * @param object
     * @return
     */
    public static boolean checkValNull(Object object) {
        return !checkValNotNull(object);
    }

    /**
     * <p>
     * 包含大写字母
     * </p>
     *
     * @param word 待判断字符串
     * @return
     */
    public static boolean containsUpperCase(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * 是否为大写命名
     * </p>
     *
     * @param word 待判断字符串
     * @return
     */
    public static boolean isCapitalMode(String word) {
        return null != word && word.matches("^[0-9A-Z/_]+$");
    }
    public static boolean endsWith(String str, String suffix) {
        return endsWith(str, suffix, false);
    }
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        return endsWith(str, suffix, true);
    }

    private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
        if (str == null || suffix == null) {
            return (str == null && suffix == null);
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        int strOffset = str.length() - suffix.length();
        return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
    }

    public static String[] split(final String str, final String separatorChars) {
        List<String> strings = splitWorker(str, separatorChars, -1, false);
        return strings.toArray(new String[strings.size()]);
    }
    /**
     * Performs the logic for the {@code split} and
     * {@code splitPreserveAllTokens} methods that return a maximum array
     * length.
     *
     * @param str               the String to parse, may be {@code null}
     * @param separatorChars    the separate character
     * @param max               the maximum number of elements to include in the array. A zero
     *                          or negative value implies no limit.
     * @param preserveAllTokens if {@code true}, adjacent separators are treated as empty
     *                          token separators; if {@code false}, adjacent separators are
     *                          treated as one separator.
     * @return an array of parsed Strings, {@code null} if null String input
     */
    public static List<String> splitWorker(final String str, final String separatorChars, final int max,
                                           final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return Collections.emptyList();
        }
        final List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            final char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list;
    }

    /**
     * 是否为CharSequence类型
     *
     * @param cls
     * @return
     */
    public static Boolean isCharSequence(Class<?> cls) {
        return cls != null && CharSequence.class.isAssignableFrom(cls);
    }

    /**
     * 是否为CharSequence类型
     *
     * @param propertyType
     * @return
     */
    public static Boolean isCharSequence(String propertyType) {
        Class<?> cls;
        try {
            cls = Class.forName(propertyType);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return isCharSequence(cls);
    }
    /**
     * <p>
     * 判断字符串是否不为空
     * </p>
     *
     * @param cs 需要判断字符串
     * @return 判断结果
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * <p>
     * 首字母转换小写
     * </p>
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String firstToLowerCase(String param) {
        if (isEmpty(param)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(param.length());
        sb.append(param.substring(0, 1).toLowerCase());
        sb.append(param.substring(1));
        return sb.toString();
    }

    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than
     * 0, and it contains at least one non-whitespace character.
     * <p><pre class="code">
     * StringUtils.isBlank(null) = true
     * StringUtils.isBlank("") = true
     * StringUtils.isBlank(" ") = true
     * StringUtils.isBlank("12345") = false
     * StringUtils.isBlank(" 12345 ") = false
     * </pre>
     * @param cs the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean isBlank(final CharSequence cs) {
        return !StringUtils.isNotBlank(cs);
    }
    
    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is
     *  not empty and not null and not whitespace
     * @see Character#isWhitespace
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return StringUtils.hasText(cs);
    }
    
    /**
     * Convert a {@code Collection} into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     * @param coll the {@code Collection} to convert
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Collection<?> coll, String delim) {
        return StringUtils.collectionToDelimitedString(coll, delim);
    }
    
    /**
     * Convert a {@code String} array into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     * @param arr the array to display
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Object[] arr, String delim) {
        return StringUtils.arrayToDelimitedString(arr, delim);
    }
    
    /**
     * 生成uuid
     * @return UUID
     */
    public static String getUUId() {
        return UUID.randomUUID().toString();
    }
}
