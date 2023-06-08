package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
public class ValidatorTest {
    @Test
    void testIsValidStringSchema() {
        Validator v1 = new Validator();
        StringSchema schema1 = v1.string();

        assertThat(schema1.isValid("")).isTrue();
        assertThat(schema1.isValid(null)).isTrue();
        assertThat(schema1.isValid("hexlet")).isTrue();
        assertThat(schema1.isValid(5)).isFalse();

        schema1.contains("");
        assertThat(schema1.isValid(null)).isFalse();

        Validator v2 = new Validator();
        StringSchema schema2 = v2.string();

        schema2.minLength(0);
        assertThat(schema2.isValid("")).isTrue();
        assertThat(schema2.isValid(null)).isFalse();
        schema2.minLength(3);
        assertThat(schema2.isValid("")).isFalse();

        Validator v3 = new Validator();
        StringSchema schema3 = v3.string();

        schema3.required();
        assertThat(schema3.isValid("what does the fox say")).isTrue();
        assertThat(schema3.isValid("")).isFalse();
        assertThat(schema3.isValid(null)).isFalse();
        assertThat(schema3.isValid(5)).isFalse();


        schema3.minLength(10);
        assertThat(schema3.isValid("hexlet")).isFalse();

        schema3.contains("what");
        assertThat(schema3.isValid("what does the fox say")).isTrue();

        schema3.contains("whatthe");
        assertThat(schema3.isValid("what does the fox say")).isFalse();
        assertThat(schema3.isValid("what does the fox say")).isFalse();
    }

    @Test
    void testIsValidNumberSchema() {
        Validator v1 = new Validator();
        NumberSchema schema1 = v1.number();

        assertThat(schema1.isValid(null)).isTrue();
        assertThat(schema1.isValid("5")).isFalse();
        assertThat(schema1.isValid(5)).isTrue();

        schema1.positive();
        assertThat(schema1.isValid(null)).isTrue();
        assertThat(schema1.isValid(-4)).isFalse();

        Validator v2 = new Validator();
        NumberSchema schema2 = v2.number();

        schema2.range(1, 9);
        assertThat(schema2.isValid(null)).isFalse();
        assertThat(schema2.isValid(-1)).isFalse();
        assertThat(schema2.isValid(6)).isTrue();

        Validator v3 = new Validator();
        NumberSchema schema3 = v3.number();

        schema3.required();
        assertThat(schema3.isValid(null)).isFalse();
        assertThat(schema3.isValid("5")).isFalse();
        assertThat(schema3.isValid(10)).isTrue();

        schema3.positive();
        assertThat(schema3.isValid(-10)).isFalse();
        assertThat(schema3.isValid(0)).isFalse();

        schema3.range(5, 10);
        assertThat(schema3.isValid(5)).isTrue();
        assertThat(schema3.isValid(10)).isTrue();
        assertThat(schema3.isValid(4)).isFalse();
        assertThat(schema3.isValid(11)).isFalse();
    }

    @Test
    void testIsValidMapSchema() {
        Validator v1 = new Validator();
        MapSchema schema1 = v1.map();

        assertThat(schema1.isValid(null)).isTrue();

        schema1.required();
        assertThat(schema1.isValid(null)).isFalse();
        assertThat(schema1.isValid(new HashMap<>())).isTrue();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema1.isValid(data)).isTrue();

        schema1.sizeof(2);
        assertThat(schema1.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema1.isValid(data)).isTrue();

        Validator v2 = new Validator();
        MapSchema schema2 = v2.map();

        assertThat(schema2.isValid(null)).isTrue();
        schema2.sizeof(3);
        assertThat(schema2.isValid(null)).isFalse();
    }
}
