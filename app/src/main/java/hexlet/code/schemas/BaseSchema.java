package hexlet.code.schemas;

import java.util.List;

public class BaseSchema {
    public BaseSchema() {
    }

    @SuppressWarnings("unchecked")
    protected boolean isValid(Object obj) {
        List<Integer> list = (List<Integer>) obj;
        int sum = 0;

        for (Integer item: list) {
            sum += item;
        }

        int length = list.size();
        return sum == length;
    }
}
