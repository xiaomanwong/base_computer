import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author Administrator
 */
public class array {


    public static void main(String[] args) {

//        int[] num = new int[]{9,9,9,9,9};
        int[] num = new int[]{2, 0};
        int[] num2 = new int[]{1};
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
//        int[] prices2 = new int[]{3,4,5,1,2};
        int[] prices2 = new int[]{3, 1};
//        maopao(num);
//        maopao2(num);

//        charu(num);
//        xuanze(num);
//        guibing(num, 0, num.length - 1);
//        kuaisu(num, 0, num.length - 1);
//        stack(num);
//        System.out.println(linea(num));
//        test(num);
//        removeDumplicates(num);
//        removeElement(num, 2);
//        twoSum(num, 4);
//        searchInsert(num, 7);
//        maxSubArray(num);
//        plusOne(num);
//        merge(num, 1, num2, 1);
//        generate(20);
//        maxProfit(prices);
//        maxProfit2(prices);
        minArray(prices2);
        System.out.println(Arrays.toString(prices));
    }


    // 获取最大利润
    private static void maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            for (int[] ints : dp) {
                System.out.println(Arrays.toString(ints));
            }
            System.out.println();
        }
        System.out.println(dp[n - 1][0]);
    }

    private static void test(int[] nums) {
        int index = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[index] != nums[j]) {
                nums[++index] = nums[j];
            }
        }
        System.out.println("index = " + index);
//        return index +1;
    }

    // 移除指定元素
    private static void removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (val != nums[i]) {
                swap(nums, i, index++);
            }
        }
        System.out.println("长度 " + index);
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置
     *
     * @param nums
     * @param target
     */
    public static void searchInsert(int[] nums, int target) {
        // 排序数组查找，使用二分法
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] >= target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }

    public static void twoSum(int[] nums, int target) {
        int[] value = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length - 1);
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                value = new int[]{map.get(another), i};
                break;
            }
            map.put(nums[i], i);
        }
        System.out.println(Arrays.toString(value));
    }

    /**
     * 计算最大收益
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int inPrices = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < inPrices) {
                inPrices = prices[i];
            } else {
                int interval = prices[i] - inPrices;
                if (interval > maxProfit) {
                    maxProfit = interval;
                }
            }
        }
        System.out.println("最大价格差: " + maxProfit);

        return maxProfit;
    }

    /**
     * 杨辉三角
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> gen = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            // 生成杨辉三角
            List<Integer> columnIndex = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    // 首尾,自动添加 1
                    columnIndex.add(1);
                } else {
                    columnIndex.add(gen.get(i - 1).get(j - 1) + gen.get(i - 1).get(j));
                }
            }
            gen.add(columnIndex);
        }

        for (List<Integer> integers : gen) {
            System.out.println(Arrays.toString(integers.toArray()));
        }
//        System.out.println(Arrays.toString(gen.toArray()));
        return gen;
    }

    /**
     * 将 B 数组内容合并到 A 数组中
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] copy = nums1.clone();
        int left = 0, right = 0, index = 0, len = m + n;
        while (index < len) {
            if (left >= m) {
                nums1[index++] = nums2[right++];
            } else if (right >= n) {
                nums1[index++] = copy[left++];
            } else if (copy[left] < nums2[right]) {
                nums1[index++] = copy[left++];
            } else {
                nums1[index++] = nums2[right++];
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 加 1 进位，后面都是 0 ，与 10 取余数，判断是否有进位，且保证值不变
     */
    static void plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        System.out.println(Arrays.toString(digits));
    }

    static void maxSubArray(int[] nums) {
        // 动态规划
        int curSum = 0, maxSum = nums[0];
        for (int num : nums) {
            curSum = Math.max(num, curSum + num);
            maxSum = Math.max(curSum, maxSum);
        }
        System.out.println(maxSum);
    }

    // 移除重复项
    private static void removeDumplicates(int[] nums) {
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            // 认定第一个为基础，判定后续和前一个是否相等
            if (nums[i] != nums[index]) {
                swap(nums, i, ++index);
            }
        }
        System.out.println("长度 " + (index + 1));
    }


    /**
     * 三数之和的最大值
     *
     * @param num
     * @return
     */
    public static int linea(int[] num) {
        int n = num.length;
        if (n == 0) return 0;
        if (n == 1) return num[1];
        int[] dp = new int[n];
        dp[0] = num[0];
        dp[1] = num[1];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(num[i] + dp[i - 2], dp[i - 1]);
            System.out.println(Arrays.toString(dp));
        }
        return dp[n - 1];
    }

    public static int minArray(int[] numbers) {
        // 暴力
        // int minValue = numbers[0];
        // for(int i = 1; i < numbers.length; i++){
        //     minValue = Math.min(minValue, numbers[i]);
        //     if(numbers[i] < numbers[0]){
        //         break;
        //     }
        // }
        // return  minValue;


        // 二分查找
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (numbers[mid] < numbers[high]) {
                high = mid;
            } else if (numbers[mid] > numbers[high]) {
                low = mid + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

    /**
     * 在解决某个问题的时候，只要求关心最近一次的操作，并且在操作完成了之后，需要向前查找到更前一次的操作。
     *
     * @param nums
     */
    // 利用栈数据结构查找下一个比自己大的数的距离
    public static void stack(int[] nums) {
        // 计算出比当前数据大的间距
        // 堆栈用来保存索引，通过索引的差值可以计算出间距
        Stack<Integer> stack = new Stack<>();
        int[] interval = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (!stack.empty()) {
                int size = stack.size();
                for (int i1 = 0; i1 < size; i1++) {
                    int topIndex = stack.peek();
                    if (nums[i] > nums[topIndex]) {
                        interval[topIndex] = (i - topIndex);
                        stack.pop();
                    }
                }
            }
            stack.push(i);
        }
        System.out.println("interval: " + Arrays.toString(interval));
    }


    public static void swap(int[] num, int src, int des) {
        int temp = num[src];
        num[src] = num[des];
        num[des] = temp;
        System.out.println(Arrays.toString(num));

    }
}
