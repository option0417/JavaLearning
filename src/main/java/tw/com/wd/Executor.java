package tw.com.wd;


public class Executor {
    public static void main(String[] args) throws Exception {
        String source = "abcde\nfghij\nklmno\npqrst\nuvwxyz\n";
        System.out.printf("%s", source);
        System.out.printf("Length: %d\n", source.length());

        source = source.replaceAll("\\r\\n", " ").replaceAll("\\n", " ");
        source = source.replace("klmno", "1");

        System.out.printf("%s\n", source);
        System.out.printf("Length: %d\n", source.length());
    }
}