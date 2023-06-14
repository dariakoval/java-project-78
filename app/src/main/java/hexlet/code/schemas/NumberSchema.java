package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema  extends BaseSchema {
    public NumberSchema() {
        addCheck("number", value -> value instanceof Integer || value == null);
    }

    public NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value == null || ((int) value) > 0);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        addCheck("range", value -> value == null || ((int) value) >= begin && ((int) value) <= end);
        return this;
    }
}
