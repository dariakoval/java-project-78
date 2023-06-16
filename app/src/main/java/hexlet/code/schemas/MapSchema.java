package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    private boolean required = false;

    public MapSchema() {
        addCheck("required", value -> value instanceof Map);
    }

    public boolean isRequired() {
        return required;
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int number) {
        addCheck("sizeof", value -> value == null || ((Map) value).size() == number);
        return this;
    }

    @SuppressWarnings("unchecked")
    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck("shape", value -> (schemas.get("name").isValid(((Map<String, Object>) value).get("name"))
                    && schemas.get("age").isValid(((Map<String, Object>) value).get("age"))));
        return this;
    }
}
