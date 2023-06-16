package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private boolean stringSchema = false;
    private final Map<String, Predicate<Object>> checks;

    protected BaseSchema() {
        this.checks = new HashMap<>();
    }

    public boolean isStringSchema() {
        return stringSchema;
    }

    public void setStringSchema(boolean stringSchema) {
        this.stringSchema = stringSchema;
    }

    protected final void addCheck(String name, Predicate<Object> check) {
        checks.put(name, check);
    }

    protected abstract boolean isRequired();

    public final boolean isValid(Object object) {
        if ((object == null || (Objects.equals(object, "") && isStringSchema())) && !isRequired()) {
            return true;
        } else if ((object == null || (Objects.equals(object, "") && isStringSchema())) && isRequired()) {
            return false;
        }
        return checks.values().stream().allMatch(check -> check.test(object));
    }
}
