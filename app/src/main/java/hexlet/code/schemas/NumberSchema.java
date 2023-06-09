package hexlet.code.schemas;

import lombok.experimental.ExtensionMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtensionMethod(BaseSchema.class)
public final class NumberSchema  extends BaseSchema {
    private Map<String, Object> mapRules;
    private List<Integer> total;

    public NumberSchema() {
        mapRules = new HashMap<>();
        total = new ArrayList<>();
    }

    public NumberSchema required() {
        mapRules.put("required", true);
        return this;
    }

    public NumberSchema positive() {
        mapRules.put("positive", true);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        mapRules.put("beginRange", begin);
        mapRules.put("endRange", end);
        return this;
    }

    public void validationPositive(Object obj) {
        if (obj == null && !mapRules.containsKey("required")) {
            total.add(1);
        } else if (obj != null && (Integer) obj > 0) {
            total.add(1);
        } else {
            total.add(0);
        }
    }

    public void validationRange(Object obj) {
        if (obj == null && !mapRules.containsKey("required")) {
            total.add(1);
        } else if (obj == null) {
            total.add(0);
        } else if ((Integer) obj >= (Integer) mapRules.get("beginRange")
                && (Integer) obj <= (Integer) mapRules.get("endRange")) {
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

    @Override
    public boolean isValid(Object obj) {
        if ((obj instanceof Integer || obj == null) && mapRules.isEmpty()) {
            total.add(1);
        } else if (obj instanceof Integer || obj == null) {
            for (String key: mapRules.keySet()) {
                if (key.equals("positive")) {
                    validationPositive(obj);
                }
                if (key.equals("required")) {
                    validationRequired(obj);
                }
                if (key.equals("beginRange")) {
                    validationRange(obj);
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
