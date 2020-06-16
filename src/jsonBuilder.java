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
}
