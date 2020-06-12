import javax.json.*;

public class statistik {

    public static String[][] statDataPieStream() {
        String[] kategorienamen = {"female", "any", "family"};
        int[] kategoriezahlen = {0,0,0};

        car.cars.stream()
                .filter(a -> a.type.equals("female"))
                .forEach(a -> kategoriezahlen[0]++);

        car.cars.stream()
                .filter(a -> a.type.equals("any"))
                .forEach(a -> kategoriezahlen[1]++);

        car.cars.stream()
                .filter(a -> a.type.equals("family"))
                .forEach(a -> kategoriezahlen[2]++);

        String[] zahlen = {Integer.toString(kategoriezahlen[0]), Integer.toString(kategoriezahlen[1]), Integer.toString(kategoriezahlen[2])};

        String[][] data = {kategorienamen, zahlen};
        return data;
    }
/*
    public static String statDataPieStreamJson() {
        JsonObject root = Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                        .add()
                )

        return "JSON Object String";
    }*/

    public static String[][] statDataBarStream() {
        String[] kategorienamen = {"female", "any", "family"};
        float[] kategoriezahlen = {0f,0f,0f};

        car.cars.stream()
                .filter(a -> a.status == false)
                .filter(a -> a.type.equals("female"))
                .forEach(a -> kategoriezahlen[0] += a.preis);

        car.cars.stream()
                .filter(a -> a.status == false)
                .filter(a -> a.type.equals("any"))
                .forEach(a -> kategoriezahlen[1] += a.preis);

        car.cars.stream()
                .filter(a -> a.status == false)
                .filter(a -> a.type.equals("family"))
                .forEach(a -> kategoriezahlen[2] += a.preis);

        String[] zahlen = {Float.toString(kategoriezahlen[0]), Float.toString(kategoriezahlen[1]), Float.toString(kategoriezahlen[2])};

        String[][] data = {kategorienamen, zahlen};
        return data;
    }
}
