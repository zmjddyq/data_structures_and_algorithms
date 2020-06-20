import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zmj
 * @date 2020/6/17 19:20
 * @Description
 */
public class TotalTest {
    @Test
    public void maxNumTest() {
        int maxNum = 458;
        System.out.println((String.valueOf(maxNum)).length());
        System.out.println(maxNum % 10);
        System.out.println(maxNum / 10 % 10);
        System.out.println(maxNum / 100 % 10);
    }

    @Test
    public void ceilTest(){
        System.out.println((int) Math.ceil(9 / 8.0));
    }

    /**
     * [-119, -99, -23, -99, -23, -99, -19, -16, -28, 20, -38, -126, -103, -34, -26, -8, 14]
     */
    @Test
    public void toBinaryStringTest(){
        byte b = 14;
        int temp = b;
        temp |= 256;
        String s = Integer.toBinaryString(temp);
        System.out.println(s);
        System.out.println(s.substring(s.length() - 8));
    }
}
