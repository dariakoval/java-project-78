package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        super();
    }

    public MapSchema required() {
        addCheck("required", value -> value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int number) {
        addCheck("sizeof", value -> value instanceof Map map && map.size() == number);
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
