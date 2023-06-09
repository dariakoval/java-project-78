package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {
    public Validator() {
    }

    public StringSchema string() {
        return new StringSchema(this);
    }

    public NumberSchema number() {
        return new NumberSchema(this);
    }

    public MapSchema map() {
        return new MapSchema(this);
    }
}
