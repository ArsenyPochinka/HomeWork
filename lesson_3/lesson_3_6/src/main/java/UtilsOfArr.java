import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UtilsOfArr {
    public static Integer[] samplingFromAnArray(Integer... arr) {
        if (Arrays.stream(arr).noneMatch(i -> i == 4)) {
            throw new RuntimeException("This array hasn't \"4\"");
        } else {
            List<Integer> list = Arrays.asList(arr);
            Collections.reverse(list);
            list = list.stream().takeWhile(i -> i!=4).collect(Collectors.toList());
            Collections.reverse(list);
            return list.toArray(new Integer[0]);
        }
    }

    public static boolean checkingAnArray(Integer... arr) {
        return Arrays.stream(arr).allMatch(i -> i==1 || i==4) && Arrays.stream(arr).anyMatch(i -> i==1)  && Arrays.stream(arr).anyMatch(i -> i==4);
    }
}
