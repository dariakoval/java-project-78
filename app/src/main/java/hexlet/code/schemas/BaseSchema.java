package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected boolean required = false;
    protected final Map<String, Predicate<Object>> checks;

    protected BaseSchema() {
        this.checks = new HashMap<>();
    }

    protected final void addCheck(String name, Predicate<Object> check) {
        checks.put(name, check);
    }

    public final boolean isValid(Object value) {
        if (!required) {
            Predicate validate = checks.get("required");
            if (!validate.test(value)) {
                return true;
            }
        }

        for (Predicate validate : checks.values()) {
            if (!validate.test(value)) {
                return false;
            }
        }

        return true;
    }
}
