package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        addCheck("string", value -> value instanceof String || value == null);
    }

    public StringSchema minLength(int number) {
        addCheck("minLength", value -> value == null || ((String) value).length() >= number);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> value == null || ((String) value).contains(substring));
        return this;
    }

    public StringSchema required() {
        addCheck("required", value -> !Objects.equals(value, "") && value != null);
        return this;
    }
}
