package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberSchema  extends BaseSchema {
    private Validator validator;
    private Map<String, Object> map;

    public NumberSchema(Validator validator) {
        this.validator = validator;
        map = new HashMap<>();
        map.put("required", null);
        map.put("positive", null);
        map.put("beginRange", null);
        map.put("endRange", null);

    }

    public NumberSchema required() {
        map.put("required", true);
        return this;
    }

    public NumberSchema positive() {
        map.put("positive", true);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        map.put("beginRange", begin);
        map.put("endRange", end);
        return this;
    }

    public boolean isValid(Object obj) {
        if (obj instanceof Integer) {
            List<String> fieldCount = new ArrayList<>();
            List<Boolean> correctChecks = new ArrayList<>();

            map.entrySet().stream()
                    .filter(x -> x.getValue() != null)
                    .forEach(x -> {
                        fieldCount.add(x.getKey());
                        if (x.getKey().equals("positive")) {
                            if ((Integer) obj > 0) {
                                correctChecks.add(true);
                            }
                        }
                        if (x.getKey().equals("beginRange")) {
                            if ((Integer) obj >= (Integer) x.getValue()) {
                                correctChecks.add(true);
                            }
                        }
                        if (x.getKey().equals("endRange")) {
                            if ((Integer) obj <= (Integer) x.getValue()) {
                                correctChecks.add(true);
                            }
                        }
                    });
            return fieldCount.size() == correctChecks.size();
        } else if (obj == null && map.get("required") == null) {
            return true;
        }
        return false;
    }
}
