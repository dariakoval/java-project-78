package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        addCheck("required", value -> value instanceof String || value == null);
    }

    public StringSchema required() {
        setRequired();
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
