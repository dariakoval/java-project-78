package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private Map<String, BaseSchema> valuesChecks;

    public MapSchema() {
        this.valuesChecks = new HashMap<>();
        addCheck("required", value -> value instanceof Map || value == null);
    }

    public MapSchema required() {
        setRequired();
        return this;
    }

    public MapSchema sizeof(int number) {
        addCheck("sizeof", value -> ((Map) value).size() == number);
        return this;
    }

    @SuppressWarnings("unchecked")
    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.valuesChecks = schemas;
        addCheck("shape", value -> checkMapValues((Map<String, Object>) value));
        return this;
    }

    public boolean checkMapValues(Map<String, Object> map) {
        StringSchema stringSchema = (StringSchema) valuesChecks.get("name");
        boolean isValidName = stringSchema.isValid(map.get("name"));

        NumberSchema numberSchema = (NumberSchema) valuesChecks.get("age");
        boolean isValidAge = numberSchema.isValid(map.get("age"));

        return isValidName && isValidAge;
    }
}
