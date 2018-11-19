package intentcompany.tanrong.com.knowledgepointset.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2018/7/30.
 */

public class StringArrayToListArray {

//.List转String数组
//
//    方法一://先准备一个List

    public static String[] listToStringArray(ArrayList<String> testList) {
        //List转String
        String[] strs1 = testList.toArray(new String[testList.size()]);
        return strs1;
    }

    //    方法二：
    public static String[] listToStringArray2(ArrayList<String> testList) {
        //List转String
        String[] strs2 = new String[testList.size()];
        for (int i = 0; i < testList.size(); i++) {
            strs2[i] = testList.get(i);
        }
        return strs2;
    }

//    二：String数据转List
//    方法一：


    public static List<String> stringArrayToListArray(String[] strs) {
        //String数组转List
        List<String> strsToList1 = Arrays.asList(strs);
        return strsToList1;
    }


    //    方法二：
    public static List<String> stringArrayToListArray2(String[] strs) {
        //准备一个String数组
        //String数组转List
        List<String> strsToList2 = new ArrayList<>();
        Collections.addAll(strsToList2, strs);
        return strsToList2;
    }


    //    方法三：
    public static List<String> stringArrayToListArray3(String[] strs) {
        //准备一个String数组
        //String数组转List
        List<String> strsToList3 = new ArrayList<>();
        for (String s : strs) {
            strsToList3.add(s);
        }
        return strsToList3;
    }


}
