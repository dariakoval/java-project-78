package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class ValidatorTest {
    @Test
    void testIsValidString() {
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
        assertThat(schema3.isValid("what does the fox say")).isFalse();

        schema3.contains("whatthe");
        assertThat(schema3.isValid("what does the fox say")).isFalse();
        assertThat(schema3.isValid("what does the fox say")).isFalse();
    }
}
