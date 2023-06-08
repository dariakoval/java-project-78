package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private Validator validator;
    private Map<String, Object> mapRules;
    private List<Integer> total;

    public MapSchema(Validator validator) {
        this.validator = validator;
        mapRules = new HashMap<>();
        total = new ArrayList<>();
    }

    public BaseSchema required() {
        mapRules.put("required", true);
        return this;
    }

    public BaseSchema sizeof(int number) {
        mapRules.put("sizeof", number);
        return this;
    }

    public void validationSizeof(Object obj) {
        if (obj == null && !mapRules.containsKey("required")) {
            total.add(0);
        } else if (((Map) obj).size() == (Integer) mapRules.get("sizeof")) {
            total.add(1);
        } else {
            total.add(0);
        }
    }

    public void validationRequired(Object obj) {
        if (obj == null) {
            total.add(0);
        } else {
            total.add(1);
        }
    }

    public boolean isValid(Object obj) {
        if ((obj instanceof Map || obj == null) && mapRules.isEmpty()) {
            total.add(1);
        } else if (obj instanceof Map || obj == null) {
            for (String key: mapRules.keySet()) {
                if (key.equals("sizeof")) {
                    validationSizeof(obj);
                }
                if (key.equals("required")) {
                    validationRequired(obj);
                }
            }
        } else {
            total.add(0);
        }
        var result = super.isValid(total);
        total = new ArrayList<>();
        return result;
    }
}
