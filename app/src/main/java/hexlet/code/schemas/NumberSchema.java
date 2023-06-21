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
        addCheck("positive", value -> {
            if (value instanceof Integer) {
                return (int) value > 0;
            }
            return true;
        });
        return this;
    }

    public NumberSchema range(int begin, int end) {
        addCheck("range", value -> {
            if (value instanceof Integer) {
                return (int) value >= begin && (int) value <= end;
            }
            return true;
        });
        return this;
    }
}
