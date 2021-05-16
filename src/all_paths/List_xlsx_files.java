package all_paths;

import java.util.ArrayList;
import java.util.Objects;

public class List_xlsx_files {

    public static ArrayList<List_xlsx_files> list_paths_xlsx = new ArrayList<>();

    private String path;

    private String name_file;


    public List_xlsx_files(String path, String name_file) {
        this.path = path;
        this.name_file = name_file;
    }




    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        List_xlsx_files that = (List_xlsx_files) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(name_file, that.name_file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name_file);
    }

    public String getName_file() {
        return name_file;
    }

    public void setName_file(String name_file) {
        this.name_file = name_file;
    }
}
