package tw.com.wd.servlet.db;

import tw.com.wd.model.NumberModel;

import java.util.ArrayList;
import java.util.List;


public class FakeDB {
    private static final List<NumberModel> NUMBER_MODEL_LIST = new ArrayList<NumberModel>();

    public FakeDB() {
        super();
    }

    public void insert(NumberModel numberModel) {
        NUMBER_MODEL_LIST.add(numberModel);
    }

    public List<NumberModel> select() {
        List<NumberModel> newNumberModelList = new ArrayList<NumberModel>(NUMBER_MODEL_LIST.size());

        for (int idx = 0; idx < NUMBER_MODEL_LIST.size(); idx++) {
            NumberModel numberModel = NUMBER_MODEL_LIST.get(idx);
            NumberModel newNumberModel = new NumberModel(numberModel.getA(), numberModel.getB());
            newNumberModelList.add(newNumberModel);
        }

        return newNumberModelList;
    }
}
