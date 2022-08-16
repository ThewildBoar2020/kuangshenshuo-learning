import com.boar.pojo.Student;
import com.boar.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.SocketUtils;

public class Mytest {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        Student student = (Student) context.getBean("student");
//        System.out.println(student.toString());
        //Student{name='野猪', address=Address{address='西安'},
        // books=[红楼梦, 西游记, 三国演义, 水浒传], hobbys=[听歌, 爬山, 看电影],
        // card={身份证=199745452336, 银行卡=15645642314512}, games=[loc, glorty of king],
        // wife='null', info={password=123456, drive=15662, url=男, username=李健}}
    }
        @Test
        public void test2(){
            ApplicationContext context = new ClassPathXmlApplicationContext("userbean.xml");
              User user = context.getBean("user2", User.class);
            System.out.println(user);
        }

}
