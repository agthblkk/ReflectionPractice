//third task

import java.io.*;
import java.lang.reflect.Field;
import java.util.stream.Collectors;

class ThirdTask implements Serializable{
    @Save
    private String str = "I'm in the file!";
    private String s = "I'm in the code!";
    @Save
    private int a = 5;
}

public class SerAndDeser implements Serializable{
    public static void main(String[] args) throws IllegalAccessException{
        final Class<?> cls = ThirdTask.class;
        String check = "";
        int checkInt = 0;
        ThirdTask obj = new ThirdTask();
        Field[] fields = cls.getDeclaredFields();
        String toCompareInt = "int";
        for (Field field : fields){
            if (field.isAnnotationPresent(Save.class)){
                field.setAccessible(true);
                if (toCompareInt.equals(String.valueOf(field.getType()))){
                    checkInt = (Integer)field.get(obj);
                }
                else{
                    check = field.get(obj).toString();
                }
            }
        }
        int input = 0;
        String str1 = "";
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream((new FileOutputStream(("newfile.txt"))));
            objectOutputStream.write(checkInt);
            objectOutputStream.write(check.getBytes());
            objectOutputStream.close();

            System.out.println("serialization happened!!");

            System.out.println("deserialization started...");

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("newfile.txt"));
            input = objectInputStream.read();
            str1 = new BufferedReader(new InputStreamReader(objectInputStream))
                    .lines().parallel().collect(Collectors.joining("\n"));

            System.out.println("deserialization happened!!");
        } catch(IOException e){
            System.err.println("EXCEPTION!!");
        }
        System.out.println("this was deserialized: " + input + " " + str1);

    }
}
