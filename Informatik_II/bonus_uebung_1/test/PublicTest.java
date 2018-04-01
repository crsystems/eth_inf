import org.junit.Test;


public class PublicTest extends MatchingTest {

    private void copy_testfiles(String test_prefix) {
        copy_testfiles("test_files/", test_prefix);
    }

    private String output(String test_prefix) {
        return output("test_files/", test_prefix);
    }

    @Test
    public void empty_list() {
        String test_prefix = "00";
        copy_testfiles(test_prefix);
        Main.main(null);
        compare(output(test_prefix), outContent.toString());
        checkErr();
    }

    @Test
    public void single_entry() {
        String test_prefix = "01";
        copy_testfiles(test_prefix);
        Main.main(null);
        compare(output(test_prefix), outContent.toString());
        checkErr();
    }

    @Test
    public void single_entry_misassigned() {
        String test_prefix = "02";
        copy_testfiles(test_prefix);
        Main.main(null);
        compare(output(test_prefix), outContent.toString());
        checkErr();
    }
    @Test
    public void multiple_misassigned_only() {
        String test_prefix = "03";
        copy_testfiles(test_prefix);
        Main.main(null);
        compare(output(test_prefix), outContent.toString());
        checkErr();
    }
    @Test
    public void multiple_echo_missing_only() {
        String test_prefix = "04";
        copy_testfiles(test_prefix);
        Main.main(null);
        compare(output(test_prefix), outContent.toString());
        checkErr();
    }
    @Test
    public void multiple_codeexpert_missing_only() {
        String test_prefix = "05";
        copy_testfiles(test_prefix);
        Main.main(null);
        compare(output(test_prefix), outContent.toString());
        checkErr();
    }

    @Test
    public void multiple_mixed() {
        String test_prefix = "06";
        copy_testfiles(test_prefix);
        Main.main(null);
        compare(output(test_prefix), outContent.toString());
        checkErr();
    }

}