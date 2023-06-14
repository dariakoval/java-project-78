package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        addCheck("map", value -> value instanceof Map || value == null);
    }

    public MapSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int number) {
        addCheck("sizeof", value -> value == null || ((Map) value).size() == number);
        return this;
    }

    @SuppressWarnings("unchecked")
    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck("shape", value -> value == null
                || (schemas.get("name").isValid(((Map<String, Object>) value).get("name"))
                    && schemas.get("age").isValid(((Map<String, Object>) value).get("age"))));
        return this;
    }
}
