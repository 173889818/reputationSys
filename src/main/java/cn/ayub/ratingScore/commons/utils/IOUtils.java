package cn.ayub.ratingScore.commons.utils;
import java.io.Closeable;
import java.io.IOException;

/**
 * 流工具
 * Created by dingcc on 2017/7/5.
 */
public class IOUtils extends org.springframework.util.StreamUtils{

    public static void closeQuietly(Closeable Closeable){
        try {
            if (Closeable != null) {
                Closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    public static void closeQuietly(Closeable... Closeables){
        if (Closeables!=null)
            for (Closeable Closeable:Closeables) {
                closeQuietly(Closeable);
            }
    }
}
