package tw.com.wd.text;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

public class Executor {
    public static void main(String[] args) throws Exception {
        String isoText = getISO_8859_1Text();
        String big5Text = getBig5Text();
        String utf8Text = "utf8_測試_我是中文_END";

        System.out.printf("isoText: %s\n", isoText);
        System.out.printf("big5Text: %s\n", big5Text);
        System.out.printf("utf8Text: %s\n", utf8Text);
        System.out.printf("Big5 To UTF81: %s\n", new String(big5Text.getBytes(Charset.forName("Big5"))));
        System.out.printf("Big5 To UTF82: %s\n", new String(big5Text.getBytes(StandardCharsets.ISO_8859_1)));
        System.out.printf("Big5 To UTF83: %s\n", new String(big5Text.getBytes(StandardCharsets.UTF_8)));
        System.out.printf("ISO To UTF81: %s\n", new String(isoText.getBytes(Charset.forName("Big5"))));
        System.out.printf("ISO To UTF82: %s\n", new String(isoText.getBytes(StandardCharsets.ISO_8859_1)));
        System.out.printf("ISO To UTF83: %s\n", new String(isoText.getBytes(StandardCharsets.UTF_8)));

        ByteBuffer isoBuffer = ByteBuffer.allocate(isoText.length());
        for (int idx = 0; idx  < isoText.length(); idx++) {
            isoBuffer.put((byte)isoText.charAt(idx));
        }

        System.out.printf("isoBuffer - Limit: %d, Position: %d\n", isoBuffer.limit(), isoBuffer.position());
        isoBuffer.flip();
        System.out.printf("isoBuffer After Flip - Limit: %d, Position: %d\n", isoBuffer.limit(), isoBuffer.position());

        try {
            CharsetDecoder utf8Decoder = StandardCharsets.UTF_8.newDecoder();
            utf8Decoder.onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
            System.out.printf("Result1: %s\n", utf8Decoder.decode(isoBuffer).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }



        ByteBuffer bigBuffer = ByteBuffer.allocate(big5Text.length());
        for (int idx = 0; idx  < big5Text.length(); idx++) {
            bigBuffer.put((byte)big5Text.charAt(idx));
        }
        showBytes(bigBuffer);
        System.out.printf("bigBuffer1 - Limit: %d, Position: %d\n", bigBuffer.limit(), bigBuffer.position());
        bigBuffer.flip();
        showBytes(bigBuffer);
        System.out.printf("bigBuffer1 After Flip - Limit: %d, Position: %d\n", bigBuffer.limit(), bigBuffer.position());

        try {
            CharsetDecoder utf8Decoder2 = StandardCharsets.UTF_8.newDecoder();
            utf8Decoder2.onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
            System.out.printf("Result2: %s\n", utf8Decoder2.decode(bigBuffer).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            showBytes(bigBuffer);
            bigBuffer.position(0);
            //bigBuffer.flip();
            showBytes(bigBuffer);

            System.out.printf("bigBuffer2 - Limit: %d, Position: %d\n", bigBuffer.limit(), bigBuffer.position());
            System.out.printf("bigBuffer2 After Flip - Limit: %d, Position: %d\n", bigBuffer.limit(), bigBuffer.position());

            CharsetDecoder big5Decoder = Charset.forName("Big5").newDecoder();
            big5Decoder.onMalformedInput(CodingErrorAction.IGNORE).onUnmappableCharacter(CodingErrorAction.IGNORE);
            System.out.printf("Result3: %s\n", big5Decoder.decode(bigBuffer).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showBytes(ByteBuffer buf) {
        byte[] bufBytes = buf.array();
        System.out.println();
        for (int idx = 0; idx < bufBytes.length; idx++) {
            System.out.printf(" %d", bufBytes[idx]);
        }
        System.out.println();
    }

    private static String getISO_8859_1Text() {
        String s = "ISO_測試_我是中文_END";
        return new String(s.getBytes(), StandardCharsets.ISO_8859_1);
    }

    private static String getBig5Text() throws Exception {
        File file = new File("/Users/weide_su/Dev/tmp/big_text");
        FileInputStream fis = new FileInputStream(file);

        byte[] big5Bytes = new byte[fis.available()];
        fis.read(big5Bytes);
        fis.close();

        File file2 = new File("/Users/weide_su/Dev/tmp/big_text2");
        file2.createNewFile();
        FileOutputStream fos = new FileOutputStream(file2);
        fos.write(big5Bytes);
        fos.close();

        CharsetDecoder big5Decoder = Charset.forName("Big5").newDecoder();
        big5Decoder.onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        System.out.printf("getBig5Text: %s\n", big5Decoder.decode(ByteBuffer.wrap(big5Bytes)).toString());

        return big5Decoder.decode(ByteBuffer.wrap(big5Bytes)).toString();
    }

    public static String reduceEmptyLine(String source) {
        int idx = source.length() - 1;
        int spaceVal = ' ';
        int newlineVal = '\n';

        while (idx >= 0) {
            char ch = source.charAt(idx);

            if (ch == spaceVal || ch == newlineVal) {
                idx--;
            } else {
                break;
            }
        }

        return source.substring(0, idx + 1);
    }
}