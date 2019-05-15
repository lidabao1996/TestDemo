package others;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Demo8 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2,2,2, 1,5,3};
        int[] nums2 = {2, 2,2,2,5,3,8,4,5};

        int[] nums3 = intersect(nums1, nums2);
        for (int i = 0; i < nums3.length; i++) {
            System.out.print(nums3[i]);
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {


        Map<Integer,Integer> vo1 = new HashMap<>();
        Map<Integer,Integer> vo2 = new LinkedHashMap<>();


        for (int i = 0; i < nums1.length; i++) {
            if (vo1.containsKey(nums1[i])){
                vo1.put(nums1[i],vo1.get(nums1[i])+1);
            }else {
                vo1.put(nums1[i], 1);
            }

        }

        for (int j = 0; j < nums2.length; j++) {
            if (vo2.containsKey(nums2[j])){
                vo2.put(nums2[j],vo2.get(nums2[j])+1);
            }else {
                vo2.put(nums2[j], 1);
            }
        }

        int length = 0;
        int setValue = 0;
        for (Integer vo : vo1.keySet()) {

            if (vo2.containsKey(vo)){
                int v1 = vo1.get(vo);
                int v2 = vo2.get(vo);
                setValue = vo;
                if (v1 == v2){
                    length = v1;
                }else if (v1 <v2){
                    length = v1;
                }
                length = v2;
            }


        }


        int[] nums3 = new int[length];
        for (int i=0;i<nums3.length;i++){
            nums3[i] = setValue;
        }
        return nums3;
    }
}
