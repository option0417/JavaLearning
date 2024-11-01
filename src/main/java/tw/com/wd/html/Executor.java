package tw.com.wd.html;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

public class Executor {
    public static void main(String[] args) throws Exception {
        String html = "<!DOCTYPE html><html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\"><body style=\"margin: 0; background-color: #000000; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\"><p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p><br>Just For Test<br>Option1<br>Option2<br>Option3<br><span><em>Please note this &nbsp;&nbsp;email cannot be used for entry.</em></span><!-- End --></body></html>";

        Document doc = Jsoup.parse(html);
        System.out.println(doc.wholeText());
        //showStringBytes(doc.text().getBytes(StandardCharsets.UTF_8));
        //System.out.println();
        doc.select("p,br").before("\\n");
        //doc.outputSettings().prettyPrint(false);
        //System.out.println(doc.html());
        //System.out.println(doc.text());
        //showStringBytes(doc.text().getBytes(StandardCharsets.UTF_8));
        //System.out.println();
        System.out.println(doc.text().replaceAll("\\\\n", "\n"));
        //showStringBytes(doc.text().replaceAll("\\\\n", "\n").getBytes(StandardCharsets.UTF_8));

        String str = doc.html().replaceAll("\\\\n", "\n");
        //System.out.println(str);

        Document.OutputSettings o = new Document.OutputSettings();
        o.prettyPrint(false);
        String c = Jsoup.clean(str, Safelist.none());
        //System.out.println(c);

    }

    public static void showStringBytes(byte[] strBytes) {
        System.out.println();
        for (byte b : strBytes) {
            System.out.printf("%d ", (int)b);
        }
        System.out.println();
    }
}