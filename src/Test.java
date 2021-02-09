import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class Test {


    public static void main(String[] args) {

//        int[] num = new int[]{9,9,9,9,9};
        int[] num = new int[]{2, 0};
        int[] num2 = new int[]{1};
        int[] prices = new int[]{7,1,5,3,6,4};
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
        maxProfit(prices);
        System.out.println(Arrays.toString(num));
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
        System.out.println("���۸��: "+ maxProfit);

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

    /**
     * ��������
     * <p>
     * �����ѡ��һ�����֣�Ȼ���ñ���С�ģ�������ߣ�������ķ����ұ�
     * Ȼ���ٴ������/�ұ����ѡ��һ�����֣�����С�ķ���ߣ���ķ��ұߣ����εݹ�ֱ�����
     *
     * @param nums
     * @param low
     * @param high
     */
    public static void kuaisu(int[] nums, int low, int high) {
        // �ҵ�һ����׼ֵ,�Ȼ�׼ֵС�����������,�Ȼ�׼ֵ����������ұ�
        if (low >= high) return;
        int p = partition(nums, low, high);
        kuaisu(nums, low, p - 1);
        kuaisu(nums, p + 1, high);
    }


    /**
     * �Ƚ��������������������棬
     * Ȼ��������ָ��
     * i: ������¼�����ֵС�����ĸ�����Ҳ�����������ֵҪ����ԭ��λ��
     * j���ǵ�ǰ��һ��ֵ
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] nums, int low, int high) {
        // �Ƚ���Сֵ,copy �����һ��λ��
        int random = randRang(low, high);
        System.out.println("random: " + random + " value: " + nums[random]);
        swap(nums, random, high);
        // i ������¼���м�ֵС�����ĸ���,Ҳ�������� �м�ֵҪ�����λ��
        int i, j;
        // ��������ÿ�����ͻ�׼ֵ�Ƚ�(num[high]),���Ȼ�׼ֵС,��ŵ�ָ�� i ��ָ���λ��,ѭ����Ϻ�, i ָ��֮ǰ�������Ȼ�׼ֵС
        for (i = low, j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(nums, i++, j);
            }
        }
        // ����׼�㻹ԭ����Ӧλ��
        swap(nums, i, j);
        return i;
    }

    private static int randRang(int low, int high) {
        return new Random().nextInt(high) % (high - low + 1) + low;
    }

    /**
     * �ݹ�����ݣ�
     * �ݹ��˼���ǽ�һ��������⣬���ֳ�����С�ĵȼ����飬�����С�ȼ�����Ľ�����ϲ�Ϊ���ս����
     * <p>
     * ���ö���������������ͬʱ������󣬽����ֱ��ںϲ�һ��
     * <p>
     * �ݹ���㷨��Ҫע���ٽ�ֵ���жϣ����������ڵ�һ�У�������뽫������ѭ�����򱨴�
     *
     * @param nums
     * @param low
     * @param high
     */
    public static void guibing(int[] nums, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        guibing(nums, low, mid);
        guibing(nums, mid + 1, high);
        merge(nums, low, high, mid);
    }

    private static void merge(int[] num, int start, int length, int mid) {
        int[] copy = num.clone();
        int index = start, left = start, right = mid + 1;
        while (index <= length) {
            if (left > mid) {
                num[index++] = copy[right++];
            } else if (right > length) {
                num[index++] = copy[left++];
            } else if (copy[right] < copy[left]) {
                num[index++] = copy[right++];
            } else {
                num[index++] = copy[left++];
            }
        }
        System.out.println("nums: " + Arrays.toString(num));
        System.out.println("copy: " + Arrays.toString(copy));

    }

    /**
     * ��δ����Ĳ��֣�ѡ��һ����Сֵ������¼�±꣬�����������ź�����������ֵ���бȽ�
     *
     * @param num
     */
    public static void xuanze(int[] num) {
        for (int i = 0; i < num.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[j] < num[minIndex]) {
                    minIndex = j;
                }
            }
            if (num[minIndex] < num[i]) {
                swap(num, minIndex, i);
            }
        }
    }

    /**
     * ��һ�����飬���뵽�Ѿ��źõ�������
     * 1��2��5��3��6
     * 1��2��3��5��6
     *
     * @param num
     */
    public static void charu(int[] num) {
        for (int i = 1; i < num.length; i++) {
            int k = i;
            for (int j = (i - 1); j >= 0; j--) {
                if (num[k] < num[j]) {
                    swap(num, k, j);
                    k = j;
                }
            }
        }
    }

    /**
     * ð������ʹ��С�����֣�������һ��һ��һ���ӵײ�ð����
     *
     * @param num
     */
    public static void maopao2(int[] num) {
        // ��С��������
        for (int i = num.length; i > 1; i--) {
            for (int j = (i - 1); j < num.length; j++) {
                if (num[j] < num[j - 1]) {
                    swap(num, j, j - 1);
                }
            }
        }
    }

    public static void maopao(int[] num) {
        // ���������³�
        int count = 0;
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length - 1 - i; j++) {
                if (num[j] > num[j + 1]) {
                    count++;
                    swap(num, j, j + 1);
                }
            }
        }
        System.out.println(count);
    }

    public static void swap(int[] num, int src, int des) {
        int temp = num[src];
        num[src] = num[des];
        num[des] = temp;
        System.out.println(Arrays.toString(num));

    }
}
