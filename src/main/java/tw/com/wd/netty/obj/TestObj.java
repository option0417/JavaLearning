package tw.com.wd.netty.obj;

import com.google.gson.annotations.SerializedName;


public class TestObj {
    @SerializedName("resp")
    private HelloResponse resp;


    public HelloResponse getResp() {
        return resp;
    }

    public void setResp(HelloResponse resp) {
        this.resp = resp;
    }
}
