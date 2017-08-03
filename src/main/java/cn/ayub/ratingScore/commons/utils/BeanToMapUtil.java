package cn.ayub.ratingScore.commons.utils;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

/**
 * map和bean之间互转
 */
public class BeanToMapUtil {
	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean  
    public static void MapToBean2(Map<String, Object> map, Object obj) {  
        if (map == null || obj == null) {  
            return; 
        }  
        try {  
            BeanUtils.populate(obj, map);
        } catch (Exception e) {  
            System.out.println("transMap2Bean2 Error " + e);  
        }  
    }

    /**
     * map转bean
     * @param map
     * @param obj
     */
 // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean  
    public static void MapToBean(Map<String, Object> map, Object obj) {  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                if (map.containsKey(key)) {  
                    Object value = map.get(key);  
                    // 得到property对应的setter方法  
                    Method setter = property.getWriteMethod(); 
                    if (setter != null) {
                    	setter.invoke(obj, value);
                    }
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transMap2Bean Error " + e);  
        }  
        return;  
    }

    /**
     * bean 转map
     * @param obj
     * @return
     */
    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public static Map<String, Object> BeanToMap(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
                    map.put(key, value);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
        return map;  
    }

    /**
     * map转bean
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;    
        Object obj = beanClass.newInstance();  
        Field[] fields = obj.getClass().getDeclaredFields();   
        for (Field field : fields) {    
            int mod = field.getModifiers();    
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                continue;    
            } 
            field.setAccessible(true);    
            field.set(obj, map.get(field.getName()));   
        }   
        return obj;    
    }

    /**
     * bean 转map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }
        Map<String, Object> map = new HashMap<String, Object>();    
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
        return map;
    }
}
