package revisao_oop.models;

public class Review implements Model {
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

    @Override
    public boolean mEquals(Model o) {
        if (o == null || getClass() != o.getClass()) return false;

        if (this == o) return true;

        Review r = (Review) o;

        return r.title.equals(title) &&
                r.organization.equals(organization) &&
                r.volume.equals(volume) &&
                r.number.equals(number) &&
                r.year.equals(year);
    }

    @Override
    public String toPrettyString() {
        return "Revista: " + title + " " + organization + " " + volume + " " + number + " " + year;
    }
}
