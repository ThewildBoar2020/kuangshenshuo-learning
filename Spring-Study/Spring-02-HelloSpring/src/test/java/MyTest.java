import com.boar.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //获取Spring上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //我们的
        Hello hello = (Hello) context.getBean("Hello");
        System.out.println(hello.toString());

    }
}
