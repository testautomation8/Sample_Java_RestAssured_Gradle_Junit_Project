package Models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Pets {

    public int id;
    public Category category;
    public String name;
    public ArrayList<String> photoUrls;
    public ArrayList<Tag> tags;
    public String status;

    @Data
    @Builder
    public static class Category {
        public int id;
        public String name;
    }

    @Data
    @Builder
    public static class Tag{
        public int id;
        public String name;
    }
}
