package reviso_oop.models;

public class Review {
    public final String title;
    public final String organization;
    public final String volume;
    public final String number;
    public final String year;

    public Review(String title, String organization, String volume, String number, String year) {
        this.title = title;
        this.organization = organization;
        this.volume = volume;
        this.number = number;
        this.year = year;
    }
}
