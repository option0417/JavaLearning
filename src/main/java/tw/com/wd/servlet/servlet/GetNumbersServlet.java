package tw.com.wd.servlet.servlet;

import com.google.gson.Gson;
import tw.com.wd.servlet.dao.FakeNumberDao;
import tw.com.wd.servlet.model.NumberModel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GetNumbersServlet extends HttpServlet {
    private FakeNumberDao fakeNumberDao;

    public GetNumbersServlet() {
        super();
        this.fakeNumberDao = new FakeNumberDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<NumberModel> numberModelList = this.fakeNumberDao.findAll();
        RespContent respContent = new RespContent();
        respContent.setNumberModelList(numberModelList);
        String respJson = new Gson().toJson(respContent);

        try {
            resp.getWriter().println(respJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final class RespContent {
        List<NumberModel> numberModelList;

        public List<NumberModel> getNumberModelList() {
            return numberModelList;
        }

        public RespContent setNumberModelList(List<NumberModel> numberModelList) {
            this.numberModelList = numberModelList;
            return this;
        }
    }
}
