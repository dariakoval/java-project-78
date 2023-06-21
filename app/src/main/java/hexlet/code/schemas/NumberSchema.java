package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
//    public NumberSchema() {
//        addCheck("instanceCheck", value -> value instanceof Integer);
//    }

    public NumberSchema required() {
        addCheck("required", value -> value instanceof Integer);
        required = true;
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> ((int) value) > 0);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        addCheck("range", value -> ((int) value) >= begin && ((int) value) <= end);
        return this;
    }
}
