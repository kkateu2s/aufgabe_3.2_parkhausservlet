import javax.json.*;
import javax.json.stream.JsonParser;
import java.io.StringReader;

public class jsonBuilder {

    public int addAllValues(JsonObject object) {
        int sum = 0;

        JsonParser parser = Json.createParser(new StringReader(object.toString()));
        while(parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case VALUE_NUMBER:
                    sum += Integer.valueOf(parser.getString());
                    break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // First TestObject
        JsonObject personObject = Json.createObjectBuilder()
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
        System.out.println("Object: " + personObject); // Looking good

        // Second TestObject
        JsonObject NumbersToAdd = Json.createObjectBuilder()
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
        JsonObject NumbersToAddEasy = Json.createObjectBuilder()
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

        jsonBuilder builder = new jsonBuilder();
        System.out.println(builder.addAllValues(NumbersToAddEasy));
    }
}
