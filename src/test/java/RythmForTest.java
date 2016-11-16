import org.junit.Before;
import org.junit.Test;
import org.rythmengine.Rythm;
import org.rythmengine.utils.JSONWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RythmForTest {
    private String forParams;

    @Before
    public void loadForJsonArguments() {
        forParams = "{\"list\": ['foo', 'bar', 'zee']}";
    }

    @Test
    public void shouldReturnNumberSequence() {
        String query = "@for (int i = 0; i < 5; ++i) {@i}";

        assertEquals("Should return a sequence of numbers", "01234", Rythm.render(query));
    }

    @Test
    public void shouldReturnNumberSequenceWithCommas() {
        String query = "@for (int i = 0; i < 5; ++i).join() {@i}";

        assertEquals("Should return a sequence of numbers", "0,1,2,3,4", Rythm.render(query));
    }

    @Test
    public void shouldReturnNumberSequenceShortNotation() {
        String query = "@for (int i = 0; i < 5; ++i) @i @";

        assertEquals("Should return a sequence of numbers with short notation", "01234", Rythm.render(query));
    }

    @Test
    public void shouldReturnListShortNotation() {
        String query = "@args List<String> list;\n" + "@for (item: list).join() @item @";

        assertEquals("Should return a list with short notation", "foo,bar,zee", Rythm.render(query, JSONWrapper.wrap(forParams)));
    }

    @Test
    public void shouldReturnSequenceWithDotDotNotation() {
        String query = "@for ([1..5]) {@_}";

        assertEquals("Should return a sequence with .. notation", "12345", Rythm.render(query));

        query = "@for (1 .. 5) {@_}";

        assertEquals("Should return a sequence with .. notation", "1234", Rythm.render(query));

        query = "@for (1 .. 5).join() {@_}";

        assertEquals("Should return a sequence with .. notation", "1,2,3,4", Rythm.render(query));

        query = "@for (1 .. 5).join(\":\") {@_}";

        assertEquals("Should return a sequence with .. notation", "1:2:3:4", Rythm.render(query));
    }

    @Test
    public void shouldReturnString() {
        String query = "@for (\"1, 2, 3, 4\") {@_}";

        assertEquals("Should return a string", "1234", Rythm.render(query));

        query = "@for (\"foo bar zee\").join() {@_}";

        assertEquals("Should return a string", "foo bar zee", Rythm.render(query));
    }

    @Test
    public void shouldReturnList() {
        String query = "@args List<String> list;\n" + "@for (list) {@_}";

        List<String> list = Arrays.asList("foo", "bar", "zee");

        HashMap<String, Object> params = new HashMap<>();

        params.put("list", list);

        assertEquals("Should return a list of strings", "foobarzee", Rythm.render(query, params));
    }
}
