import java.lang.reflect.*;
class Testers {
    @Test(a=1, b=5)
    public void test(int a, int b){
        System.out.println(a);
        System.out.println(b);
    }
}

public class Reflection{
    public static void main(String[] args){
        final Class<?> cls = Testers.class;
        Method[] methods = cls.getMethods();
        int a1 = 0;
        int b1 = 0;
        for (Method method : methods){
            if (method.isAnnotationPresent(Test.class)){
                a1 = method.getAnnotation(Test.class).a();
                b1 = method.getAnnotation(Test.class).b();
            }
        }
        Testers testing = new Testers();
        testing.test(a1, b1);
    }
}

