package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private boolean required = false;

    public StringSchema() {
        addCheck("required", value -> value instanceof String);
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
        addCheck("minLength", value -> ((String) value).length() >= number);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> ((String) value).contains(substring));
        return this;
    }
}
