package hexlet.code.schemas;

import java.util.Objects;

public final class StringSchema extends BaseSchema {
    private boolean required = false;

    public StringSchema() {
        addCheck("required", value -> value instanceof String || Objects.equals(value, ""));
        setStringSchema(true);
    }

    public boolean isRequired() {
        return required;
    }

    public StringSchema required() {
        required = true;
        return this;
    }
    public StringSchema minLength(int number) {
        addCheck("minLength", value -> value == null || ((String) value).length() >= number);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> value == null || ((String) value).contains(substring));
        return this;
    }
}
