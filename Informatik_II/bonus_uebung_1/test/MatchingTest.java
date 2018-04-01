import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Helper functions for testing
 */
public class MatchingTest {

    /**
     *  Current Project Directory.
     */
    public final static String curDir = Paths.get("").toAbsolutePath().toString() + "/Root/";


    static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    static ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    /**
     * Copies the test files to the files directory, overwriting existing files
     *
     * @param  dir Directory in which test files are stored
     * @param test_prefix E.g. "00", "01", etc
     */
    public static void copy_testfiles(String dir, String test_prefix) {

        try {


            // Paths to test files
            Path test_echo = Paths.get(curDir + dir + test_prefix + "_echo.csv");
            Path test_codeexpert = Paths.get(curDir + dir + test_prefix + "_codeexpert.csv");

            // Target path
            Path target_echo = Paths.get(curDir + "files/echo.csv");
            Path target_codeexpert = Paths.get(curDir + "files/codeexpert.csv");

            Files.copy(test_echo, target_echo, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(test_codeexpert, target_codeexpert, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException("Could not copy test files", e);
        }
    }

    /**
     * Reads the file containing the expected output and returns it as a String
     * @param dir Directory in which test files are stored
     * @param test_number
     * @return Contents of  ./dir/[test_number]_output.txt
     */
    static String output(String dir, String test_number) {
        try {
            Path test_output = Paths.get(curDir + dir + test_number + "_output.txt");
            return new String(Files.readAllBytes(test_output));
        } catch (IOException e) {
            throw new RuntimeException("Could not open test solution file", e);
        }
    }

    /**
     * This method compares the output to the correct solution.
     * It should be able to differentiate between lines that don't match the formating,
     * lines that are misordered and lines that shouldn't be in the oputput at all
     *
     * @param expected Answer that we expect
     * @param actual   Submitted answer
     */
    static void compare(String expected, String actual) {
        // Split strings into lines and save in a collection
        List<String> expected_lines = Arrays.asList(expected.split("(\r\n|\r|\n)", -1));
        List<String> actual_lines = Arrays.asList(actual.split("(\r\n|\r|\n)", -1));


        // Ugly Regular expresion to support unicode in student names
        String utf8name = "[\\p{L}\\p{N}*-]";
        String pattern1 = utf8name + " " + utf8name + " belongs to " + utf8name + " in Echo, but registered with " + utf8name + " in CodeExpert";
        String pattern2 = utf8name + " " + utf8name + " belongs to " + utf8name + " in CodeExpert, but registered with " + utf8name + " in Echo";
        String pattern3 = utf8name + " " + utf8name + " is in CodeExpert but not in Echo";
        String pattern4 = utf8name + " " + utf8name + " is in Echo but not in CodeExpert";
        String pattern = "((" + pattern1 + ")|(" + pattern2 + ")|(" + pattern3 + ")|(" + pattern4 + "))";


        // Special case: A case with no misassigned students should produce no output
        if (expected.equals("")) {
            assertEquals("There should be no output if there are no misassigned students.", expected, actual);
        }

        // Check all lines
        for (int i = 0; i < actual_lines.size(); ++i) {

            if (expected_lines.get(i) != actual_lines.get(i)) {

                if (!actual_lines.get(i).matches(pattern)) {
                    // Line is malformed
                    assertEquals("Line " + i + " is malformed", expected_lines.get(i), actual_lines.get(i));
                }

                if (expected_lines.contains(actual_lines.get(i))) {
                    //Line is correct, but at wrong position
                    int expected_line = expected_lines.indexOf(actual_lines.get(i));
                    if (actual_lines.contains(expected_lines.get(i))) {
                        // Expected line for this position is there but at wrong position
                        assertEquals("Wrong order in output, " +
                                        "line " + i + " should be at position " + expected_line,
                                expected_lines.get(i), actual_lines.get(i));
                    } else {
                        // Expected line is missing
                        assertEquals("Missing output item on line " + i, expected_lines.get(i), actual_lines.get(i));
                    }
                } else {
                    // Line should not appear in output
                    assertEquals("Line " + i + " should not be part of output", expected_lines.get(i), actual_lines.get(i));
                }
            }

        }

    }

    /**
     * Verify that there was no output to System.err
     */
    static void checkErr() {
        assertEquals("There should be no output on err", "", errContent.toString());
    }

    /**
     * Redirect the output from the console to a Stream we can save
     */
    @Before
    public void setUpStreams() {
        outContent.reset();
        errContent.reset();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Restore routing of output to the console
     */
    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    /**
     * This test is simply here to workaround a Codeboard.io issue
     */
    @Test
    public void dummyTest() {
        // empty test passes
    }
}
