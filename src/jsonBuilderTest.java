import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;

import static org.junit.jupiter.api.Assertions.*;

class jsonBuilderTest {
    private static jsonBuilder builder;
    private static JsonObject personObject;
    private static JsonObject NumbersToAdd;
    private static JsonObject NumbersToAddEasy;


    static {
        builder = new jsonBuilder();
        // First TestObject
        personObject = Json.createObjectBuilder()
                .add("name", "John")
                .add("age", 13)
                .add("isMarried", false)
                .add("address",
                        Json.createObjectBuilder().add("street", "Main Street")
                                .add("city", "New York")
                                .add("zipCode", "11111")
                                .build()
                )
                .add("phoneNumber",
                        Json.createArrayBuilder().add("00-000-0000")
                                .add("11-111-1111")
                                .add("11-111-1112")
                                .build()
                )
                .build();
        System.out.println("MyObject: " + personObject); // Looking good

        // Second TestObject
        NumbersToAdd = Json.createObjectBuilder()
                .add("Value1" , 5)
                .add("Value2", 7)
                .add("moreValues",
                        Json.createObjectBuilder()
                                .add("moreValues1", 10)
                                .add("moreValues2", 2)
                                .add("alotmoreValues",
                                        Json.createObjectBuilder()
                                                .add("alotmoreValues1", 3)
                                                .add("alotmoreValues2", 4)
                                                .add("anArrayOfValues", Json.createArrayBuilder()
                                                        .add(5)
                                                        .add(6)
                                                        .add(7)
                                                        .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();
        System.out.println("MyObject: " + NumbersToAdd); // Looking good

        // Third TestObject
        NumbersToAddEasy = Json.createObjectBuilder()
                .add("Value1" , 2)
                .add("Value2", 2)
                .add("moreValues",
                        Json.createObjectBuilder()
                                .add("moreValues1", 2)
                                .add("moreValues2", 2)
                                .add("alotmoreValues",
                                        Json.createObjectBuilder()
                                                .add("alotmoreValues1", 2)
                                                .add("alotmoreValues2", 2)
                                                .add("anArrayOfValues", Json.createArrayBuilder()
                                                        .add(2)
                                                        .add(2)
                                                        .add(2)
                                                        .add("Randomtext")
                                                        .add(2)
                                                        .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();
        System.out.println("MyObject: " + NumbersToAddEasy); // Looking good
    }

    @Test
    void addAllValues() {
        assertEquals(13, builder.addAllValues(personObject));
        assertEquals(20, builder.addAllValues(NumbersToAddEasy));
        assertEquals(49, builder.addAllValues(NumbersToAdd));
    }
}