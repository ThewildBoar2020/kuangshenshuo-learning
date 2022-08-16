import com.boar.pojo.User;
import com.boar.pojo.UserT;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {

         ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
          UserT user = (UserT) context.getBean("userT2");
//          user.getName();
          user.show();
    }
}
