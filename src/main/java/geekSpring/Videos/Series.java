package geekSpring.Videos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect
public class Series {
    private String seriesTitle;
    private String time;

    public Series() {}

    public Series(String seriesTitle, String time) {
        this.seriesTitle = seriesTitle;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Название серии: " + seriesTitle + " | "+ " Продолжительность серии: " + time;
    }
}
