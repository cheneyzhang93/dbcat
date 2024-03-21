package cc.dbcat.dev.util;

import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class ClazzUtil {

    public static ActionListener createActionListenerInstance(String clazzName) {
        try {
            Class<?> listenerClass = Class.forName(clazzName);
            try {
                return (ActionListener) listenerClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
