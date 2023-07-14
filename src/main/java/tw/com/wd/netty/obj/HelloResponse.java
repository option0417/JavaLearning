package tw.com.wd.netty.obj;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HelloResponse {
    @SerializedName("m")
    private String meta;
    @SerializedName("c")
    private String content;
    @SerializedName("bl")
    private List<String> branchList;

    public List<String> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<String> branchList) {
        this.branchList = branchList;
    }

    public String getMeta() {
        return meta;
    }

    public HelloResponse setMeta(String meta) {
        this.meta = meta;
        return this;
    }

    public String getContent() {
        return content;
    }

    public HelloResponse setContent(String content) {
        this.content = content;
        return this;
    }
}
