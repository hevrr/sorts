import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class California {

    /* candidate list */
    private static String[] candidates;

    public static void main(String[] args) {
        StdOut.println("- Sorts strings through insertion according to this ordering for last name, then first name if last names are same");
        StdOut.println("- Sorts the Californian gubernational election candidates and prints\n");

        loadCandidates();
        sort();
        printArray();

        StdOut.println("\nHappy voting!");
    }

    /* sorts through java */
    private static void sort() {
        Arrays.sort(candidates, new CaliforniaComparison());
    }

    private static class CaliforniaComparison implements Comparator<String> {

        /* given order of sort order */
        private static final String ORDER = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

        /* post-condition: returns comparison for names a and b */
        @Override
        public int compare(String o1, String o2) {

            StringTokenizer tokenizer = new StringTokenizer(o1, ", ");
            StringTokenizer tokenizer2 = new StringTokenizer(o2, ", ");

            /* if both first name and last name is equal */
            if (!tokenizer.hasMoreTokens() && !tokenizer2.hasMoreTokens())
                return 0;
                /* if last name is same, compare first name */
            else if (tokenizer.hasMoreTokens() && tokenizer2.hasMoreTokens() && tokenizer.nextToken().equals(tokenizer2.nextToken()))
                return compare(tokenizer.nextToken(), tokenizer2.nextToken());

            /* goes through min length and compares each character of strings */
            int length = Math.min(o1.length(), o2.length());
            for (int i = 0; i < length; i++)
                if (!o1.substring(i, i + 1).equals(o2.substring(i, i + 1)))
                    return ORDER.indexOf(o1.substring(i, i + 1)) - ORDER.indexOf(o2.substring(i, i + 1));

            /* returns difference in length if letters up to min length match */
            return o1.length() - o2.length();
        }
    }

    /* loads California gubernational 2003 election candidates */
    private static void loadCandidates() {
        int size = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Candidates.txt"));
            String line = reader.readLine();
            while (line != null) {
                size++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
        }

        candidates = new String[size];

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Candidates.txt"));
            String line = reader.readLine();
            int count = 0;
            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ");

                String lastName = "";
                while (tokenizer.hasMoreTokens())
                    lastName = tokenizer.nextToken();

                line = line.substring(0, line.indexOf(lastName));
                line = lastName + ", " + line;

                candidates[count] = line.toUpperCase();
                count++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
        }
    }

    /* prints candidate list */
    public static void printArray() {
        for (String candidate : candidates) StdOut.println(candidate);
    }
}
