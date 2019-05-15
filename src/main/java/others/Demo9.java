package others;

import java.util.HashMap;
import java.util.Map;

public class Demo9 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 2, 2, 1, 5, 3};
        int[] nums2 = {2, 2, 2, 2, 5, 3, 8, 4, 5};
        int[] nums3 = intersect(nums1, nums2);
        for (int i = 0; i < nums3.length; i++) {
            System.out.print(nums3[i]);
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[]{};
        }
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums1.length; i++) {
            Integer value = map1.get(nums1[i]);
            if (value == null) {
                map1.put(nums1[i], 1);
            } else {
                value++;
                map1.put(nums1[i], value);
            }

        }
        Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        for (int j = 0; j < nums2.length; j++) {
            Integer value = map2.get(nums2[j]);
            if (value == null) {
                map2.put(nums2[j], 1);
            } else {
                value++;
                map2.put(nums2[j], value);
            }
        }
        StringBuffer result = new StringBuffer();
        for (Map.Entry<Integer, Integer> entry1 : map1.entrySet()) {
            int k1 = entry1.getKey();
            int v1 = entry1.getValue();
            if (map2.get(k1) != null) {
                for (int i = 0; i < Math.min(v1, map2.get(k1)); i++) {
                    result.append(k1);
                    result.append(",");
                }
            }
        }
        if ("".equals(result.toString())) {
            return new int[]{};
        }
        String[] res = result.toString().split(",");
        int[] r = new int[res.length];
        for (int i = 0; i < res.length; i++) {
            r[i] = Integer.parseInt(res[i]);
        }

        return r.length == 0 ? new int[]{} : r;
    }
}
