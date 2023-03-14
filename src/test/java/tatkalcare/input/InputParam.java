package tatkalcare.input;

import java.util.ArrayList;
import java.util.List;

public class InputParam {
    private static int count;
    private static String DEFAULT_MOBILE_NUM = "8884592118";

    private static List<String> mobileNums = new ArrayList<>();

    public static String getInputParam()
    {
        return "/customer/login?mobile="+ InputParam.getMobileNum();
    }

    public static String getMobileNum()
    {
        if(mobileNums.isEmpty()){
            initialize();
        }

        if(count < mobileNums.size()){
            return mobileNums.get(count++);
        }else {
            //reset count
            count = 0;
        }

        return DEFAULT_MOBILE_NUM;
    }

    private static void initialize()
    {
        mobileNums.add("8884592118");
        mobileNums.add("8073116488");
        mobileNums.add("6295490736");
        mobileNums.add("9333167446");
        mobileNums.add("7001449030");
        mobileNums.add("7676139621");
        mobileNums.add("8296887274");
        mobileNums.add("8588829759");
        mobileNums.add("8660411237");
        mobileNums.add("8759689412");
        //mobileNums.add("9555030075");
        //mobileNums.add("8101255878");
        //mobileNums.add("7304977669");
        //mobileNums.add("9964241437");
        //mobileNums.add("8147106125");

        //reset count
        count = 0;
    }
}
