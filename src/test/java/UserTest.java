import static org.junit.Assert.assertEquals;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.rythmengine.Rythm;
import org.rythmengine.utils.JSONWrapper;

public class UserTest {

    @Test
    public void usingUserClassDirectlyShouldReturnUserName() {
        String json = "{\"username\": \"greenlaw110\",\"firstName\": \"Gelin\", \"lastName\": \"Luo\"}";
        User user = JSON.parseObject(json, User.class);

        assertEquals("User should have a Name", Rythm.render("@args User user;@user.getName()", user), "Gelin Luo");
    }

    @Test
    public void usingUserClassIndirectlyShouldReturnUserName() {
        String json = "{\"username\": \"greenlaw110\",\"firstName\": \"Gelin\", \"lastName\": \"Luo\"}";

        String s = Rythm.substitute("{\"user\": @user}", json);

        assertEquals("User should have a Name", Rythm.render("@args User user;@user.getName()", JSONWrapper.wrap(s)), "Gelin Luo");
    }
}
