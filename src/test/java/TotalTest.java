import org.junit.Test;

/**
 * @author zmj
 * @date 2020/6/17 19:20
 * @Description
 */
public class TotalTest {
    @Test
    public void maxNumTest(){
        int maxNum = 458;
        System.out.println((String.valueOf(maxNum)).length());
        System.out.println(maxNum % 10);
        System.out.println(maxNum / 10 % 10);
        System.out.println(maxNum / 100 % 10);
    }
}
