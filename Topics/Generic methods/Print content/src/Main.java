// do not remove imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

class ArrayUtils {
    static <T> String info(T[] array) {
        List<String> list = Arrays.stream(array)
                .map(String::valueOf)
                .collect(Collectors.toList());
        return list.toString();

    }
}