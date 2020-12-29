package geekSpring.Videos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect
public class Serial extends Series {
    private String title;
    private String releaseDate;
    private Integer quantity;


    public Serial() {}

    public Serial(String title, Integer quantity, String releaseDate) {
        this.title = title;
        this.quantity = quantity;
        this.releaseDate = releaseDate;

    }
    @Override
    public String toString() {
        return "Название сериала: " + title + " | " + "Колличество серий: " + quantity + " | " + "Дата выхода:" + releaseDate;
    }
}
