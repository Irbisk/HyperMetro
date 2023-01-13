import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class MethodsDemo {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = SomeClass.class.getDeclaredMethods();
        SomeClass someClass = new SomeClass();
        for (Method method: methods) {
            int modifiers = method.getModifiers();
            method.setAccessible(true);
            if (Modifier.isStatic(modifiers)) {
                System.out.println(method.invoke(SomeClass.class, null));
            } else {
                System.out.println(method.invoke(someClass));

            }
        }
    }
}
