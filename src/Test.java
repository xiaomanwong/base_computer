import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Test {


    public static void main(String[] args) {

        int[] num = new int[]{1,1,2,2,3,3};
//        maopao(num);
//        maopao2(num);

//        charu(num);
//        xuanze(num);
//        guibing(num, 0, num.length - 1);
//        kuaisu(num, 0, num.length - 1);
//        stack(num);
//        System.out.println(linea(num));
        test(num);
        System.out.println(Arrays.toString(num));
    }

    private static void test(int[] nums){
        int index = 0;
        for (int j = 0; j < nums.length; j++) {
            if(nums[index] != nums[j]){
                nums[++index] = nums[j];
            }
        }
        System.out.println("index = " + index);
//        return index +1;
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

    /**
     * 快速排序
     * <p>
     * 先随机选出一个数字，然后让比它小的，放在左边，比它大的放在右边
     * 然后再次在左边/右边随机选择一个数字，比他小的放左边，大的放右边，依次递归直到完成
     *
     * @param nums
     * @param low
     * @param high
     */
    public static void kuaisu(int[] nums, int low, int high) {
        // 找到一个基准值,比基准值小的数放在左边,比基准值大的数放在右边
        if (low >= high) return;
        int p = partition(nums, low, high);
        kuaisu(nums, low, p - 1);
        kuaisu(nums, p + 1, high);
    }


    /**
     * 先将随机到的数，放在最后面，
     * 然后定义两个指针
     * i: 用来记录比随机值小的数的个数，也就是最终随机值要被还原的位置
     * j：是当前第一个值
     *
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] nums, int low, int high) {
        // 先将最小值,copy 到最后一个位置
        int random = randRang(low, high);
        System.out.println("random: " + random + " value: " + nums[random]);
        swap(nums, random, high);
        // i 用来记录比中间值小的数的个数,也就是最终 中间值要插入的位置
        int i, j;
        // 从左到右用每个数和基准值比较(num[high]),若比基准值小,则放到指针 i 所指向的位置,循环完毕后, i 指针之前的数都比基准值小
        for (i = low, j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(nums, i++, j);
            }
        }
        // 将基准点还原到对应位置
        swap(nums, i, j);
        return i;
    }

    private static int randRang(int low, int high) {
        return new Random().nextInt(high) % (high - low + 1) + low;
    }

    /**
     * 递归与回溯，
     * 递归的思想是将一个大的问题，产分成无限小的等价事情，最后将最小等价事情的结果集合并为最终结果集
     * <p>
     * 利用二叉树，左右两边同时排序，最后，将两分本在合并一起
     * <p>
     * 递归的算法需要注意临界值的判断（往往出现在第一行，否则代码将进入死循环，或报错）
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
     * 在未排序的部分，选择一个最小值，并记录下标，并让他和已排号序的索引最大值进行比较
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
     * 将一个数组，插入到已经排好的数组中
     * 1，2，5，3，6
     * 1，2，3，5，6
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
     * 冒泡排序，使较小的数字，像气泡一样一个一个从底部冒出来
     *
     * @param num
     */
    public static void maopao2(int[] num) {
        // 将小数向上抛
        for (int i = num.length; i > 1; i--) {
            for (int j = (i - 1); j < num.length; j++) {
                if (num[j] < num[j - 1]) {
                    swap(num, j, j - 1);
                }
            }
        }
    }

    public static void maopao(int[] num) {
        // 将大数向下沉
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
