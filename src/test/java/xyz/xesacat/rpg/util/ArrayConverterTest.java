package xyz.xesacat.rpg.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Test for ArrayConverter")
public class ArrayConverterTest {
    @Test
    @DisplayName("Test empty array")
    void StringToInt() {
        String[] args = {};
        int[] expected = {};

        int[] actual = new ArrayConverter(args).StringToInt();
        assertArrayEquals(expected, actual);
        assertEquals(args.length, actual.length);
    }
}
