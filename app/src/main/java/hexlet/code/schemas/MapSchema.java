package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        addCheck("required", value -> value instanceof Map);
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int number) {
        addCheck("sizeof", value -> ((Map) value).size() == number);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck("shape", value -> schemas.entrySet().stream().allMatch(e -> {
            Object v = ((Map) value).get(e.getKey());
            return e.getValue().isValid(v);
        }));
        return this;
    }
}
