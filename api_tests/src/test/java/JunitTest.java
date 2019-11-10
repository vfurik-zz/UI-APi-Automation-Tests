import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Log4j
public class JunitTest {


    @Test
    void test123(){
        log.info("test 123");
        Assertions.assertEquals(1,2);
    }




}
