import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Get value for a given public field or null if the field does not exist or is not accessible.
 */
class FieldGetter {

    public Object getFieldValue(Object object, String fieldName) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field: fields) {
            int modifier = field.getModifiers();
            if (!Modifier.isPrivate(modifier) && field.getName().equals(fieldName)) {
                object = field.get(object);
            } else {
                object = null;
            }
        }
        return object;
    }

}