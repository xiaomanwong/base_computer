import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Test {


    public static void main(String[] args) {

        int[] num = new int[]{1, 4, 7, 2, 5, 6, 7};
//        maopao(num);
//        maopao2(num);

//        charu(num);
//        xuanze(num);
//        guibing(num, 0, num.length - 1);
//        stack(num);
//        kuaisu(num, 0, num.length - 1);
        System.out.println(linea(num));
        System.out.println(Arrays.toString(num));
    }


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

    public static void kuaisu(int[] nums, int low, int high) {
        // �ҵ�һ����׼ֵ,�Ȼ�׼ֵС�����������,�Ȼ�׼ֵ����������ұ�
        if (low >= high) return;
        int p = partition(nums, low, high);
        kuaisu(nums, low, p - 1);
        kuaisu(nums, p + 1, high);
    }


    private static int partition(int[] nums, int low, int high) {
        // �Ƚ���Сֵ,copy �����һ��λ��
        int random = randRang(low, high);
        System.out.println("random: " + random);
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
