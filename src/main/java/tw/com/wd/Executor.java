package tw.com.wd;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.annotations.SerializedName;
import tw.com.wd.proto.EventValue;
import tw.com.wd.proto.EventValue.EventSource;
import tw.com.wd.proto.EventValue.EventType;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;


public class Executor {
    public static final class Obj {
        public Object getExif() {
            return exif;
        }

        public Obj setExif(Object exif) {
            this.exif = exif;
            return this;
        }

        @SerializedName("exif")
        private Object exif;
    }


    public static void main(String[] args) throws Exception {
        EventValue.Builder eventValueBuilder = EventValue.newBuilder();

        EventValue eventValue = eventValueBuilder
            .setReceiverId("R000A1")
            .setGiverId("G000Z1")
            .setEventSource(EventSource.Post)
            .setEventType(EventType.Do_Like)
            .setValue(123)
            .setEventTimestamp(System.currentTimeMillis())
            .build();

        byte[] eventValueBytes = eventValue.toByteArray();

        System.out.printf("Length: %d\n", eventValueBytes.length);

        EventValue newEventValue = EventValue.parseFrom(eventValueBytes);

        System.out.printf("ReveiverId: %s\n", newEventValue.getReceiverId());
        System.out.printf("GiverId: %s\n", newEventValue.getGiverId());
        System.out.printf("EventSource: %s\n", newEventValue.getEventSource());
        System.out.printf("EventType: %s\n", newEventValue.getEventType());
        System.out.printf("Value: %s\n", newEventValue.getValue());
        System.out.printf("EventTimestamp: %s\n", newEventValue.getEventTimestamp());

        Gson g  = new GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.STRING).disableHtmlEscaping().create();

        String s = "{\"exif\":{\"Latitude\":\"0.0\",\"Longitude\":\"0.0\"}}";
        String s2 = "\"{\\\"exif\\\":{\\\"Latitude\\\":\\\"0.0\\\",\\\"Longitude\\\":\\\"0.0\\\"}}\"";

        Obj obj = g.fromJson(s, Obj.class);

        System.out.printf("GSON: %s\n", new Gson().toJson(obj));

        String exifText = g.toJson(s);
        System.out.printf("EXIF: %s\n", exifText);
        return;
    }

    public static void encodeByAES() throws Exception {
        // AES encode
        String key = "mitake_86136982";
        byte[] keyBytes = Arrays.copyOf(key.getBytes(Charset.forName("UTF-8")), 16);
        String srcData = "GxxCby05Fcbkd+F0HYWYY7oiAIGCz6+e6nI4Yp0lC8o=";
        byte[] srcDataBytes = srcData.getBytes(Charset.forName("UTF-8"));
        byte[] encodedBytes = null;

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        encodedBytes = cipher.doFinal(srcDataBytes);


        byte[] base64EncodedByte = Base64.getEncoder().encode(encodedBytes);

        String base64EncodedString = new String(base64EncodedByte);

        System.out.println(base64EncodedString);
        System.out.println(base64EncodedString.equals("tDiqktzSoXTkmfejIM9sdjEdREQZBpNWbFcad3Tx+6TTBASYwc8bpf2I85H/8y7j"));
    }

    public static void decodeByAES() throws Exception {
        // AES encode
        String key = "mitake_86136982";
        byte[] keyBytes = Arrays.copyOf(key.getBytes(Charset.forName("UTF-8")), 16);
        String base64EncodedString = "tDiqktzSoXTkmfejIM9sdjEdREQZBpNWbFcad3Tx+6TTBASYwc8bpf2I85H/8y7j";
        byte[] base64EncodedBytes = base64EncodedString.getBytes(Charset.forName("UTF-8"));
        byte[] encodedBytes = null;
        byte[] decodedBytes = null;

        encodedBytes = Base64.getDecoder().decode(base64EncodedString);

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        decodedBytes = cipher.doFinal(encodedBytes);
        String decodeData = new String(decodedBytes);

        System.out.println(decodeData);
        System.out.printf("%s\n", decodeData.equals("GxxCby05Fcbkd+F0HYWYY7oiAIGCz6+e6nI4Yp0lC8o="));
    }
}