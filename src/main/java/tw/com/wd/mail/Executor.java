package tw.com.wd.mail;

import javax.mail.internet.MimeUtility;

public class Executor {
    public static void main(String[] args) throws Exception {
        //new MailSender().send();

        //System.setProperty("mail.mime.decodetext.strict", "true");
        String result = MimeUtility.decodeText("=?utf-8?B?IkZyb20gU0NPTTIwMjIgIuitpuekujog5YGl5YWo54uA5rOB?==?utf-8?B?5pyN5YuZ5rS75YuV6KiK6Jmf5aSx5pWXIOino+axuueLgOaFizogQ2xv?==?utf-8?B?c2Vk4peP4peP44CQ5bey57aT5oGi5b6p6YCj57ea44CR4peP4peP?=");
        System.out.println(result);
    }
}
