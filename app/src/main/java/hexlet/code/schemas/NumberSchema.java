package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        super();
    }

    public NumberSchema required() {
        addCheck("required", value -> value instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value instanceof Integer integer && integer > 0);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        addCheck("range", value -> value instanceof Integer integer && integer >= begin && integer <= end);
        return this;
    }
}
