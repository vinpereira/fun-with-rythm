import org.junit.Before;
import org.junit.Test;
import org.rythmengine.Rythm;
import org.rythmengine.utils.JSONWrapper;

import static org.junit.Assert.assertEquals;


public class RythmTest {

    @Test
    public void shouldReturnHelloWord() {
        String params = "{\"who\": \"World\"}";

        String query = "@args String who; " +
                       "Hello @who!";

        assertEquals("Should return Hello World", "Hello World!", Rythm.render(query, JSONWrapper.wrap(params)));
    }

    @Test
    public void shouldReturnHelloWordWithCapFirst() {
        String params = "{\"who\": \"world\"}";

        String query = "@args String who; " +
                       "Hello @who.capFirst()!";

        assertEquals("Should return Hello World", "Hello World!", Rythm.render(query, JSONWrapper.wrap(params)));
    }
}
