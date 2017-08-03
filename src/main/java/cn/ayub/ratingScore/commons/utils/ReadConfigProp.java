package cn.ayub.ratingScore.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dingcc on 2017/7/18.
 */
public class ReadConfigProp {
    private static Properties init(){
        InputStream in = ReadConfigProp.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return prop;
    }

}
