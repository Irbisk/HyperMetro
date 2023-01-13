import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 Get list of public fields the object declares (inherited fields should be skipped).
 */
class FieldGetter {

    public String[] getPublicFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        ArrayList<String> list = new ArrayList<>();
        for (Field field: fields) {
            int n = field.getModifiers();
            if (Modifier.isPublic(n)) {
                list.add(field.getName());
            }
        }
        return list.toArray(new String[list.size()]);
    }

}