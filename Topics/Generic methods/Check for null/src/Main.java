// do not remove imports
import java.util.*;
import java.util.function.Function;

class ArrayUtils {
    static <T> boolean hasNull(T[] array) {
        long a = Arrays.stream(array)
                .filter(Objects::isNull)
                .count();
        return a != 0;
    }
}