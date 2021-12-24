package xyz.xesacat.rpg.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Suite
@SuiteDisplayName("Test for CmdLineParser")
public class CmdLineParserTest {
    // S: single, M: multiple, A: argument, T: target

    @Test
    @DisplayName("Test one argument with one target")
    void parseSAST() throws MalformedArgumentException {
        String[] args = { "-port", "1234", "-interface", "0.0.0.0" };
        CmdLineParser parser = new CmdLineParser(args);
        String[] actual = parser.parseArgument("port", 1);
        String[] expected = { args[1] };
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test one argument with two targets")
    void parseSAMT() throws MalformedArgumentException {
        String[] args = { "-override", "file1.txt", "file2.txt" };
        CmdLineParser parser = new CmdLineParser(args);
        String[] actual = parser.parseArgument("override", 2);
        String[] expected = { args[1], args[2] };
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Parse two arguments with one target, test first")
    void parseFirstMAST() throws MalformedArgumentException {
        String[] args = { "-port", "1234", "-interface", "0.0.0.0" };
        CmdLineParser parser = new CmdLineParser(args);
        String[] actual = parser.parseArgument("port", 1);
        String[] expected = { args[1] };
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Parse two arguments with one target, test second")
    void parseSecondMAST() throws MalformedArgumentException {
        String[] args = { "-port", "1234", "-interface", "0.0.0.0" };
        CmdLineParser parser = new CmdLineParser(args);
        String[] actual = parser.parseArgument("interface", 1);
        String[] expected = { args[3] };
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Parse two arguments and two targets, test first")
    void parseFirstMAMT() throws MalformedArgumentException {
        String[] args = { "-override", "file1.txt", "file2.txt", "-move", "file3.txt", "file4.txt" };
        CmdLineParser parser = new CmdLineParser(args);
        String[] expected = { args[1], args[2] };
        String[] actual = parser.parseArgument("override", 2);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Parse two arguments and two targets, test second")
    void parseSecondMAMT() throws MalformedArgumentException {
        String[] args = { "-override", "file1.txt", "file2.txt", "-move", "file3.txt", "file4.txt" };
        CmdLineParser parser = new CmdLineParser(args);
        String[] expected = { args[4], args[5] };
        String[] actual = parser.parseArgument("move", 2);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Parse argument with zero targets")
    void parseArgumentWithZeroTargets() {
        String[] args = { "port", "1234" };
        CmdLineParser parser = new CmdLineParser(args);

        assertThrows(Exception.class, () -> parser.parseArgument("port", 0));
    }

    @Test
    @DisplayName("parse no arguments")
    void parseNoArguments() {
        String[] args = { "port", "1234" };
        CmdLineParser parser = new CmdLineParser(args);

        Executable executable = () -> parser.parseArgument("", 1);

        assertThrows(MalformedArgumentException.class, executable);
    }

    @Test
    @DisplayName("Parse empty argument")
    void parseNoArgumentsGiven() {
        String[] args = {};
        CmdLineParser parser = new CmdLineParser(args);

        assertThrows(MalformedArgumentException.class, () -> parser.parseArgument("port", 1));
    }

}
