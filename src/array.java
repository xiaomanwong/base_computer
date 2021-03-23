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


    // ��ȡ�������
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

    // �Ƴ�ָ��Ԫ��
    private static void removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (val != nums[i]) {
                swap(nums, i, index++);
            }
        }
        System.out.println("���� " + index);
    }

    /**
     * ����һ�����������һ��Ŀ��ֵ�����������ҵ�Ŀ��ֵ�������������������Ŀ��ֵ�������������У����������ᱻ��˳������λ��
     *
     * @param nums
     * @param target
     */
    public static void searchInsert(int[] nums, int target) {
        // ����������ң�ʹ�ö��ַ�
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
     * �����������
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
        System.out.println("���۸��: " + maxProfit);

        return maxProfit;
    }

    /**
     * �������
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> gen = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            // �����������
            List<Integer> columnIndex = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    // ��β,�Զ���� 1
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
     * �� B �������ݺϲ��� A ������
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
     * �� 1 ��λ�����涼�� 0 ���� 10 ȡ�������ж��Ƿ��н�λ���ұ�ֵ֤����
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
        // ��̬�滮
        int curSum = 0, maxSum = nums[0];
        for (int num : nums) {
            curSum = Math.max(num, curSum + num);
            maxSum = Math.max(curSum, maxSum);
        }
        System.out.println(maxSum);
    }

    // �Ƴ��ظ���
    private static void removeDumplicates(int[] nums) {
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            // �϶���һ��Ϊ�������ж�������ǰһ���Ƿ����
            if (nums[i] != nums[index]) {
                swap(nums, i, ++index);
            }
        }
        System.out.println("���� " + (index + 1));
    }


    /**
     * ����֮�͵����ֵ
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
        // ����
        // int minValue = numbers[0];
        // for(int i = 1; i < numbers.length; i++){
        //     minValue = Math.min(minValue, numbers[i]);
        //     if(numbers[i] < numbers[0]){
        //         break;
        //     }
        // }
        // return  minValue;


        // ���ֲ���
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
     * �ڽ��ĳ�������ʱ��ֻҪ��������һ�εĲ����������ڲ��������֮����Ҫ��ǰ���ҵ���ǰһ�εĲ�����
     *
     * @param nums
     */
    // ����ջ���ݽṹ������һ�����Լ�������ľ���
    public static void stack(int[] nums) {
        // ������ȵ�ǰ���ݴ�ļ��
        // ��ջ��������������ͨ�������Ĳ�ֵ���Լ�������
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
