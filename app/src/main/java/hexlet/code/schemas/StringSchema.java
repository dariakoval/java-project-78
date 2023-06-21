package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        super();
    }

    public StringSchema required() {
        addCheck("required", value -> value instanceof String string && string.length() > 0);
        return this;
    }

    public StringSchema minLength(int number) {
        addCheck("minLength", value -> {
            if (value instanceof String) {
                return ((String) value).length() >= number;
            }
            return true;
        });
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> {
            if (value instanceof String) {
                return ((String) value).contains(substring);
            }
            return true;
        });
        return this;
    }
}
