package tw.com.wd.servlet.servlet;

import com.google.gson.Gson;
import tw.com.wd.servlet.dao.FakeNumberDao;
import tw.com.wd.servlet.model.NumberModel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddNumberServlet extends HttpServlet {
    private FakeNumberDao fakeNumberDao;


    public AddNumberServlet() {
        super();
        this.fakeNumberDao = new FakeNumberDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // Get parameters from client
        String textNumA = req.getParameter("num_a");
        String textNumB = req.getParameter("num_b");

        // Do Add service
        int numA = Integer.parseInt(textNumA);
        int numB = Integer.parseInt(textNumB);
        int sum  = doAdd(numA, numB);
        System.out.println("Sum = " + sum);

        // Convert to JSON
        AddResult addResult = new AddResult();
        addResult.setSum(sum);
        String respJson = new Gson().toJson(addResult);

        try {
            resp.getWriter().println(respJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int doAdd(int numA, int numB) {
        // Write to database by dao
        NumberModel numberModel = new NumberModel(numA, numB);
        this.fakeNumberDao.create(numberModel);

        return numA + numB;
    }

    public static final class AddResult {
        private int sum;

        public int getSum() {
            return sum;
        }

        public AddResult setSum(int sum) {
            this.sum = sum;
            return this;
        }
    }
}
