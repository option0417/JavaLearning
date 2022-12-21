package tw.com.wd;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;


public class FamilyDecoder {
    private static final String SOURCE_KEY_TEST = "54c7a7382ff84b57a84f5a17943d53ef";
    private static final String SOURCE_KEY_TEST2 = "eb7a23c2-08d1-4644-a448-5628fd26dba7";
    private static final String PLAIN_IV = "1baf60d1dfa15dbb285d1a210bfa69dd";
    private static final String ENCODED_DEFAULT = "3OIrplHB0j%2FhJqDTXFJ2KtpN1o6d3daR4VQxN24u0c0gLqLySOWGLX1Dro4OW2ILHKeP%2Ft%2FD%2Fkqb0fPI4O%2B%2BSOjYz84MuYKusKDvOOJbHxw%2Bm9RdFsfPpFrJaTHHzQ9Q";
    private static final String ENCODED_01 = "WGYZmBfWd6D72/Mg73hnDBnSkJPm9gSreIUROOGME+HIasTpwr2DFtbKzWXQb93IE7eBG0RCg2Chmxw7sKHYYA==";
    private static final String ENCODED_02 = "Z+qgPij9gvZgRzVUvJdRJDQNljQZOwb5FQu0qNB0gV/COrkMubZtQYFWi3JFGjJsxNIyY0kMJKdo5eLRH68+XA==";

    public static void main(String[] args) throws Exception {
//        String reqContent = "{\"PUSH_URL\":\"familymart://action.go/stamp\",\"APP_ID\":\"P0000\",\"MEMBER_ID\":\"1451870360\",\"PUSH_MSG\":\"JUST_FOR_TEST\",\"RETRY\":\"N\"}";
//        String encodedReq = encodePushRequest(reqContent);
//        System.out.println("REQ: " + encodedReq);
//
//        String result = decodePushResponse("eyJBUFBfSUQiOm51bGwsIk1FTUJFUl9JRCI6bnVsbCwiUkVTVUxUIjoiRVJST1ItMDEuRT1TeXN0ZW0uTnVsbFJlZmVyZW5jZUV4Y2VwdGlvbjogT2JqZWN0IHJlZmVyZW5jZSBub3Qgc2V0IHRvIGFuIGluc3RhbmNlIG9mIGFuIG9iamVjdC5cclxuICAgYXQgRmFtaWx5TWFydC5XZWJBUElzLlB1c2hDb250cm9sbGVyLkZhbWlseVB1c2goRmFtaWx5UHVzaE1vZGVsIG1vZGVsKSBpbiBEOlxcMTdMaWZlXFxGYW1pbHlNYXJ0MlxcRmFtaWx5TWFydFxcRmFtaWx5TWFydFxcV2ViQVBJc1xcUHVzaENvbnRyb2xsZXIuY3M6bGluZSAzMDkifQ%3d%3d");
//        System.out.println(result);

        decodeFromFamilyMember();
    }

    public static void decodeFromFamilyMember() throws Exception {
        byte[] srcKey = SOURCE_KEY_TEST2.getBytes(StandardCharsets.UTF_8);
        MessageDigest mdSha256 = MessageDigest.getInstance("SHA-256");
        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");

        byte[] srcKeyShaBytes = mdSha256.digest(srcKey);
        byte[] md5IV = mdMD5.digest(srcKey);
        IvParameterSpec iv = new IvParameterSpec(md5IV);

        byte[] base64Decodedbytes = Base64
            .getDecoder().decode(ENCODED_01);

        // AES Decryption with 256bit CBC
        SecretKeySpec secretKeySpec = new SecretKeySpec(srcKeyShaBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] decodeBytes = cipher.doFinal(base64Decodedbytes);

        String plainJSON = new String(decodeBytes);
        System.out.println("Result: " + plainJSON);
    }

    public static String encodePushRequest(String originContent) throws UnsupportedEncodingException {
        String base64Text = new String(Base64.getEncoder().encode(originContent.getBytes(StandardCharsets.UTF_8)));
        String urlEncodedText = URLEncoder.encode(base64Text, "UTF-8");

        return urlEncodedText;
    }

    public static String decodePushResponse(String encodedContent) throws UnsupportedEncodingException {
        String urlDecodedContent = URLDecoder.decode(encodedContent, "UTF-8");
        String originContent = new String(Base64.getDecoder().decode(urlDecodedContent), StandardCharsets.UTF_8);
        return originContent;
    }
}
