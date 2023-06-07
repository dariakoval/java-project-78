package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StringSchema {
    private Validator validator;
    private Map<String, Object> map;

    public StringSchema(Validator validator) {
        this.validator = validator;
        map = new HashMap<>();
        map.put("required", null);
        map.put("minLength", null);
        map.put("contains", null);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public StringSchema required() {
        map.put("required", true);
        return this;
    }

    public StringSchema minLength(int number) {
        map.put("minLength", number);
        return this;
    }

    public StringSchema contains(String substring) {
        map.put("contains", substring);
        return this;
    }

    public boolean isValid(Object obj) {
        if (obj instanceof String) {
            List<String> fieldCount = new ArrayList<>();
            List<Boolean> correctChecks = new ArrayList<>();

            map.entrySet().stream()
                    .filter(x -> x.getValue() != null)
                    .forEach(x -> {
                        fieldCount.add(x.getKey());
                        if (x.getKey().equals("contains")) {
                            if (((String) obj).contains((String) x.getValue())) {
                                correctChecks.add(true);
                            }
                        }
                        if (x.getKey().equals("minLength")) {
                            if (((String) obj).length() == (Integer) x.getValue()) {
                                correctChecks.add(true);
                            }
                        }
                        if (x.getKey().equals("required")) {
                            if (!Objects.equals(obj, "")) {
                                correctChecks.add(true);
                            }
                        }
                    });

            return fieldCount.size() == correctChecks.size();
        } else if ((obj == null || Objects.equals(obj, "")) && map.get("required") == null) {
            return true;
        }
        return false;
    }
}
