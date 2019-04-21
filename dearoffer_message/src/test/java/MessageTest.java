import cn.javakk.MessageApplication;
import cn.javakk.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: javakk
 * @Date: 2019/4/21 13:25
 */

@SpringBootTest(classes = MessageApplication.class)
@RunWith(SpringRunner.class)
public class MessageTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void test(){
        messageService.sendRegisterMessage("18428303662", "1235");
    }
}
