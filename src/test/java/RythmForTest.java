import org.junit.Before;
import org.junit.Test;
import org.rythmengine.Rythm;
import org.rythmengine.utils.JSONWrapper;

import static org.junit.Assert.assertEquals;

public class RythmForTest {
    private String forParams;

    @Before
    public void loadForJsonArguments() {
        forParams = "\"list\": ['foo', 'bar', 'zee']";
    }

    @Test
    public void shouldReturnSomething() {
        String query = "@for (int i = 0; i < 5; ++i) {@i}";

        assertEquals("Bla", Rythm.render(query), "01234");
    }
}
