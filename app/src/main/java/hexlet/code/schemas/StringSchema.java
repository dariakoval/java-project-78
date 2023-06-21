package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
//    public StringSchema() {
//        addCheck("instanceCheck", value -> value instanceof String);
//    }

    public StringSchema required() {
        addCheck("required", value -> value instanceof String && !((String) value).isEmpty());
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
