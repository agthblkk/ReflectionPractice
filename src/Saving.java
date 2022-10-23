//second task

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SaveTo(path="C:\\Users\\agath\\IdeaProjects\\testers\\src\\hello.txt")
class TextContainer {
    private String catchMe = "okay, you got me!";
    @Saver
    public void save(String s, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.printf(s);
        printWriter.close();
    }
}
public class Saving {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        final Class<?> cls = TextContainer.class;
        String pathFounder = "";
        String name;
        if (cls.isAnnotationPresent(SaveTo.class)){
            pathFounder = cls.getAnnotation(SaveTo.class).path();
        }
        TextContainer obj = new TextContainer();
        Field field = cls.getDeclaredField("catchMe");
        field.setAccessible(true);
        name = field.get(obj).toString();
        obj.save(name, pathFounder);
    }
}