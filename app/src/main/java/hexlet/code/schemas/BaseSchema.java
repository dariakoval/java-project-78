package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private boolean required = false;
    private final Map<String, Predicate<Object>> checks;

    protected BaseSchema() {
        this.checks = new HashMap<>();
    }

    protected final boolean isRequired() {
        return required;
    }

    protected final void setRequired() {
        this.required = true;
    }

    protected final void addCheck(String name, Predicate<Object> check) {
        checks.put(name, check);
    }

    public final boolean isValid(Object object) {
        if (checks.get("required").test(object)) {
            if ((object == null || Objects.equals(object, "")) && !isRequired()) {
                return true;
            } else if ((object == null || Objects.equals(object, "")) && isRequired()) {
                return false;
            } else {
                return checks.values().stream().allMatch(check -> check.test(object));
            }
        }
        return false;
    }
}
