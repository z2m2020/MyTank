import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.testng.AssertJUnit.assertNotNull;

//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertNotNull;



public class ImageTest2 {
    @Test
    public void test(){
        try{
//            BufferedImage image = ImageIO.read(new File("D:\\work_place\\java_work_place\\idea\\tank\\src\\images\\1.gif"));
//            assertNotNull(image);
            BufferedImage image = ImageIO.read(ImageTest2.class.getClassLoader().getResourceAsStream("images/1.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
