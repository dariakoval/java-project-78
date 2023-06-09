package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.List;

public class BaseSchema {
    private final Validator validator;
    public BaseSchema(Validator validator) {
        this.validator = validator;
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
