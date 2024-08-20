import java.util.Date;

public class Customerr {
    private String name;
    private String surname;
    private Date brithday;
    private String position;

    public Customerr(String name, String surname, Date brithday, String position) {
        this.name = name;
        this.surname = surname;
        this.brithday = brithday;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", brithday=" + brithday +
                ", position='" + position + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
