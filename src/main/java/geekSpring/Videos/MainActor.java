package geekSpring.Videos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonAutoDetect
public class MainActor {
    private String firstName;
    private String lastname;
    private List<Series> seriesTitles;

    private Serial serial;



    public MainActor(String name, String lastname, String s, Serial serial){
        this.firstName = name;
        this.lastname = lastname;
        this.seriesTitles = new ArrayList<>();
        this.serial = serial;

    }

    public void addSeries(Series series) { this.seriesTitles.add(series);}


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Имя: ").append(firstName).append("\n")
                .append("Фамилия: ").append(lastname).append("\n")
                .append(serial.toString()).append("\n");
        for (Series series : seriesTitles) {
            stringBuilder.append("    ").append(series.toString()).append("\n");
        }
        return stringBuilder.toString();
    }


}
