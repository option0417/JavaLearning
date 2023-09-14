package tw.com.wd.servlet.dao;

import tw.com.wd.servlet.db.FakeDB;
import tw.com.wd.servlet.model.NumberModel;

import java.util.List;


public class FakeNumberDao {
    private FakeDB fakeDB;

    public FakeNumberDao() {
        super();
        this.fakeDB = new FakeDB();
    }

    public void create(NumberModel numberModel) {
        this.fakeDB.insert(numberModel);
    }

    public List<NumberModel> findAll() {
        return this.fakeDB.select();
    }
}
