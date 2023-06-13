### Hexlet tests and linter status:
[![Actions Status](https://github.com/dariakoval/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/dariakoval/java-project-78/actions)                    [![Java CI](https://github.com/dariakoval/java-project-78/actions/workflows/generate.yml/badge.svg)](https://github.com/dariakoval/java-project-78/actions/workflows/generate.yml)                    [![Maintainability](https://api.codeclimate.com/v1/badges/58e938460031fc69942c/maintainability)](https://codeclimate.com/github/dariakoval/java-project-78/maintainability)                   [![Test Coverage](https://api.codeclimate.com/v1/badges/58e938460031fc69942c/test_coverage)](https://codeclimate.com/github/dariakoval/java-project-78/test_coverage)

## About the project
This project is called **"Data validator"**.

## Description
Data validator is a library that can be used to check the correctness of any data.
To use a validator, you need to create a validator object and define a data validation scheme. The schema is an object that contains data validation rules.

Library Features:
* String validation:
  * null restriction;
  * minimum length limit;
  * content limit.
* Number validation:
  * null restriction;
  * sign limit;
  * allowable range.
* Validation of objects of type Map:
  * null restriction;
  * size limit;
  * structure check support.

## Usage example
```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

// Strings
StringSchema schema = v.string().required().minLength(5).contains("hex");
schema.isValid("hexlet"); // true
schema.isValid(""); // false

// Numbers
NumberSchema schema = v.number().required().positive().range(5, 10);
schema.isValid(-10); // false
schema.isValid(10); // true

// Map object with structure checking support
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false
```
