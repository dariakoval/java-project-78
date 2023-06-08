package hexlet.code.schemas;

import java.util.List;

public abstract class BaseSchema {
    public BaseSchema() {
    }

    abstract BaseSchema required();

    @SuppressWarnings("unchecked")
    public boolean isValid(Object obj) {
        List<Integer> list = (List<Integer>) obj;
        int sum = 0;

        for (Integer item: list) {
            sum += item;
        }

        int length = list.size();
        return sum == length;
    }
}
