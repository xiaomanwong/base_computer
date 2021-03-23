import java.util.Arrays;
import java.util.Random;

class sort {

    public static void main(String[] args) {
        int[] num = new int[]{9,9,9,9,9};
        int[] num2 = new int[]{1};
        int[] prices = new int[]{7,1,5,3,6,4};
//        maopao(num);
//        maopao2(num);

//        charu(num);
//        xuanze(num);
//        guibing(num, 0, num.length - 1);
        kuaisu(prices, 0, prices.length - 1);
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
        if (low >= high) {
            return;
        }
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
     * @param numszuobian
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
        if (low >= high) {
            return;
        }
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
