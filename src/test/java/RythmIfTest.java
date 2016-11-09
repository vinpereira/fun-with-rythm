import org.junit.Before;
import org.junit.Test;
import org.rythmengine.Rythm;
import org.rythmengine.utils.JSONWrapper;

import static org.junit.Assert.assertEquals;


public class RythmIfTest {

    private String ifParams;

    private String ifQuery;

    @Before
    public void loadIfJsonArguments() {
        ifParams = "{\"name\": \"Mike\", " +
                "\"blankName\": \"  \", " +
                "\"age\": 10, " +
                "\"money\": 0.0, " +
                "\"names\": ['Jeff', 'John', 'Ross'], " +
                "\"emptyList\": []}";

        ifQuery = "@args String name, " +
                "String blankName, " +
                "int age, " +
                "int money, " +
                "List<String> names, " +
                "List<String> emptyList;\n";
    }

    @Test
    public void shouldReturnName() {
        String query = ifQuery +
                "Hi, @name!";

        assertEquals("IF Statement should return Name", Rythm.render(query, JSONWrapper.wrap(ifParams)), "Hi, Mike!");
    }

    @Test
    public void shouldReturnAge() {
        String query = ifQuery +
                "@if (age < 8) {\n" +
                "@age < 8\n" +
                "} else if (age < 16) {\n" +
                "@age < 16\n" +
                "} else {\n" +
                "@age >= 16" +
                "}";
        System.out.println(ifParams);
        assertEquals("IF Statement should return Age", Rythm.render(query, JSONWrapper.wrap(ifParams)), "10 < 16");
    }

    @Test
    public void shouldHaveSmartEvaluateString() {
        String query = ifQuery +
                "@if (name) {\n" +
                "name is true\n" +
                "}";

        assertEquals("IF Statement should have Smart Evaluate a String", Rythm.render(query, JSONWrapper.wrap(ifParams)), "name is true");
    }

    @Test
    public void shouldHaveSmartEvaluateEmptyString() {
        String query = ifQuery +
                "@if (blankName) {\n" +
                "blankName is true\n" +
                "} else {\n" +
                "blankName is false\n" +
                "}";

        assertEquals("IF Statement should have Smart Evaluate an empty String", Rythm.render(query, JSONWrapper.wrap(ifParams)), "blankName is false");
    }

    @Test
    public void shouldHaveSmartEvaluateFalseOrNoStrings() {
        // @{String FALSE = "false", NO = "no";} did not work

        String params = "{\"FALSE\": \"false\", \"NO\": \"no\"}";

        String query = "@args String FALSE, String NO;\n" +
                "@if (FALSE) {\n" +
                "you should not reach here\n" +
                "} else {\n" +
                "FALSE is false\n" +
                "}\n\n" +
                "@if (NO) {\n" +
                "you should not reach here\n" +
                "} else {\n" +
                "NO is false\n" +
                "}";

        assertEquals("IF Statement should have Smart Evaluate a 'false' or 'no' Strings", Rythm.render(query, params), "FALSE is false\n" +
                "NO is false");
    }

    @Test
    public void shouldHaveSmartEvaluateNumbers() {
        String query = ifQuery +
                "@if (age) {\n" +
                    "age is non-zero\n" +
                "} else {\n" +
                    "@age is zero\n" +
                "}\n\n" +
                "@if (money) {\n" +
                    "money is non-zero\n" +
                "} else {\n" +
                    "money is zero\n" +
                "}";

        System.out.println(ifParams);
        assertEquals("IF Statement should have Smart Evaluate a Number", Rythm.render(query, ifParams), "age is non-zero\n" +
                "money is zero");
    }

    @Test
    public void shouldHaveSmartEvaluateCollections() {
        String query = ifQuery +
                "@if (names) {\n" +
                "names is not empty\n" +
                "} else {\n" +
                "@names is empty\n" +
                "}\n\n" +
                "@if (emptyList) {\n" +
                "emptyList is not empty\n" +
                "} else {\n" +
                "emptyList is empty\n" +
                "}";

        assertEquals("IF Statement should have Smart Evaluate a Collection", Rythm.render(query, ifParams), "name is not empty\n" +
                "emptyList is empty");
    }
}
