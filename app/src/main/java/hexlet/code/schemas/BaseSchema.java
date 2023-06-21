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
        if (!required && value == null) {
            return true;
        }
//
//        if (!required) {
//            return checks.values().stream().anyMatch(check -> check.test(value));
//        }

        return checks.values().stream().allMatch(check -> check.test(value));

//        for (Predicate<Object> check: checks.values()) {
//            if (!required && check.test(value)) {
//                return true;
//            }
//            if (!check.test(value)) {
//                return false;
//            }
//        }
//
//        return true;
    }
}
