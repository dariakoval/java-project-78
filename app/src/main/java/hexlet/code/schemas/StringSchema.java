package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StringSchema extends BaseSchema {
    private Map<String, Object> mapRules;
    private List<Integer> total;

    public StringSchema() {
        mapRules = new HashMap<>();
        total = new ArrayList<>();
    }

    public StringSchema required() {
        mapRules.put("required", true);
        return this;
    }

    public StringSchema minLength(int number) {
        mapRules.put("minLength", number);
        return this;
    }

    public StringSchema contains(String substring) {
        mapRules.put("contains", substring);
        return this;
    }

    public void validationMinLength(Object obj) {
        if (obj == null && !mapRules.containsKey("required")) {
            total.add(1);
        } else if (obj == null) {
            total.add(0);
        } else if (((String) obj).length() >= (Integer) mapRules.get("minLength")) {
            total.add(1);
        } else {
            total.add(0);
        }
    }

    public void validationContains(Object obj) {
        if (obj == null && !mapRules.containsKey("required")) {
            total.add(1);
        } else if (obj == null) {
            total.add(0);
        } else if (((String) obj).contains((String) mapRules.get("contains"))) {
            total.add(1);
        } else {
            total.add(0);
        }
    }

    public void validationRequired(Object obj) {
        if (obj == null) {
            total.add(0);
        } else if (obj.equals("")) {
            total.add(0);
        } else {
            total.add(1);
        }
    }

    @Override
    public boolean isValid(Object obj) {
        if ((obj instanceof String || obj == null) && mapRules.isEmpty()) {
            total.add(1);
        } else if (obj instanceof String || obj == null) {
            for (String key: mapRules.keySet()) {
                if (key.equals("contains")) {
                    validationContains(obj);
                }
                if (key.equals("minLength")) {
                    validationMinLength(obj);
                }
                if (key.equals("required")) {
                    validationRequired(obj);
                }
            }
        } else {
            total.add(0);
        }

        var result = super.isValidTotal(total);
        total = new ArrayList<>();
        return result;
    }
}
