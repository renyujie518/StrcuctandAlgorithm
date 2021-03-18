package Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName findDepth.java
 * @Description TODO
 * @createTime 2021年03月16日 00:00:00
 */
public class findDepth {

    public static  int findDepth(ArrayList array){
        List<Integer> depths = new ArrayList<>();
        for (int i = 0; i <array.size(); i++) {
            int depth = 0;
            if (array.getClass().isArray()) {

                depth = findDepth((ArrayList) array.get(i));
            }
            depths.add(depth);
           }

           depths.sort(new Comparator<Integer>() {
               @Override
               public int compare(Integer o1, Integer o2) {
                   return o1-o2;  //升序
               }
           });
        return depths.get(depths.size() - 1) + 1;
        }
}

