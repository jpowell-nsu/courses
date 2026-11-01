import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CheckedInLambda {
    public static void main(String[] args) {
        List<String> names = List.of("files/a.txt", "files/b.txt");
        List<String> contents = names.stream()
                .map(name -> Files.readString(Path.of(name)))
                .toList();
    }
}
