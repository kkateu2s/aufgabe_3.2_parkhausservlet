import java.util.ArrayList;
import java.util.List;

public class car {
    public static List<car> cars = new ArrayList<>();
    public static List<String> numberToIndex = new ArrayList<>();

    public boolean status;
    public String nr;
    public long einfahrtszeit;
    public long ausfahrtszeit;
    public float time;
    public float preis;
    public String ticket;
    public String type;
    public String color;
    public int parkplatz;


    // Constructor for entering cars
    public car(boolean status, String nr, Long einfahrtszeit, String ticket, String type, String color, int parkplatz) {
        this.status = status;
        this.nr = nr;
        this.einfahrtszeit = einfahrtszeit;
        this.ticket = ticket;
        this.type = type;
        this.color = color;
        this.parkplatz = parkplatz;
    }

    // Constructor for leaving cars
    public car(boolean status, String nr, long einfahrtszeit, long ausfahrtszeit, String ticket, String type, String color, int parkplatz, float preis, float time) {
        this.status = status;
        this.nr = nr;
        this.einfahrtszeit = einfahrtszeit;
        this.ausfahrtszeit = ausfahrtszeit;
        this.preis = preis;
        this.ticket = ticket;
        this.type = type;
        this.color = color;
        this.parkplatz = parkplatz;
        this.time = time;
    }

    void leaveParkhaus(long ausfahrtszeit, int parkplatz, float preis) {
        this.ausfahrtszeit = ausfahrtszeit;
        this.parkplatz = parkplatz;
        this.preis = preis;
    }

    void appendList(car car) {
        cars.add(car);
        numberToIndex.add(car.nr);
    }

    public static List returnCars() {
        return cars;
    }

    public static List returnTicket() {
        return numberToIndex;
    }

    public static int returnIndex(String number) {
        int index = -1;
        for(int i = 0; i<numberToIndex.size(); i++) {
            if(number.equals(numberToIndex.get(i))) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public String toString() {
        return "car{" +
                "status=" + status +
                ", nr=" + nr +
                ", einfahrtszeit=" + einfahrtszeit +
                ", ausfahrtszeit=" + ausfahrtszeit +
                ", time=" + time +
                ", preis=" + preis +
                ", ticket='" + ticket + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", parkplatz=" + parkplatz +
                '}';
    }
}
