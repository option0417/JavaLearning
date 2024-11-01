package tw.com.wd.time;


import org.joda.time.DateTime;

public class Executor {
    public static void main(String[] args) throws Exception {
        DateTime dateTime = new DateTime(System.currentTimeMillis());

        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(dateTime.getYear());
        keyBuilder.append("_");
        keyBuilder.append(dateTime.getMonthOfYear());
        keyBuilder.append("_");
        keyBuilder.append(dateTime.getDayOfMonth());
        keyBuilder.append("_");
        keyBuilder.append(dateTime.getHourOfDay());
        keyBuilder.append("_");
        keyBuilder.append(dateTime.getMinuteOfHour());
        keyBuilder.append("_");
        keyBuilder.append(dateTime.getSecondOfMinute());
        keyBuilder.append("_");
        keyBuilder.append(dateTime.getMillisOfSecond());
        System.out.println(keyBuilder.toString());
    }
}