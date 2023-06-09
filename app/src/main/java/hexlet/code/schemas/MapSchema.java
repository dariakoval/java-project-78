package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private Map<String, Object> mapRules;
    private List<Integer> total;
    private Map<String, BaseSchema> schemas;
    private List<Integer> nestedList;

    public MapSchema() {
        mapRules = new HashMap<>();
        total = new ArrayList<>();
        nestedList = new ArrayList<>();
    }

    public BaseSchema required() {
        mapRules.put("required", true);
        return this;
    }

    public BaseSchema sizeof(int number) {
        mapRules.put("sizeof", number);
        return this;
    }

    public void shape(Map<String, BaseSchema> map) {
        mapRules.put("shape", true);
        this.schemas = map;
    }

    public void validationSizeof(Object obj) {
        if (obj == null && !mapRules.containsKey("required")) {
            total.add(1);
        } else if (obj == null) {
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

    public void validationShape(Object obj) {
        if (obj == null && !mapRules.containsKey("required")) {
            total.add(1);
        } else if (obj == null) {
            total.add(0);
        } else if (validationNestedData(obj)) {
            total.add(1);
        } else {
            total.add(0);
        }
    }

    @SuppressWarnings("unchecked")
    public boolean validationNestedData(Object obj) {
        Map<String, Object> mapData = (Map<String, Object>) obj;
        for (String key: mapData.keySet()) {
            if (schemas.get(key).isValid(mapData.get(key))) {
                nestedList.add(1);
            } else {
                nestedList.add(0);
            }
        }

        var result = super.isValid(nestedList);
        nestedList = new ArrayList<>();
        return result;
    }

    @Override
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
                if (key.equals("shape")) {
                    validationShape(obj);
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
