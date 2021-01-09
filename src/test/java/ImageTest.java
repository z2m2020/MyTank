import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {
    @Test
    void test(){
        try{
//            BufferedImage image = ImageIO.read(new File("D:\\work_place\\java_work_place\\idea\\tank\\src\\images\\1.gif"));
//            assertNotNull(image);
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/1.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
