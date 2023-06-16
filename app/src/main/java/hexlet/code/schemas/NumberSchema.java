package hexlet.code.schemas;

public final class NumberSchema  extends BaseSchema {
    private boolean required = false;

    public NumberSchema() {
        addCheck("required", value -> value instanceof Integer);
    }

    public boolean isRequired() {
        return required;
    }

    public NumberSchema required() {
        required = true;
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
