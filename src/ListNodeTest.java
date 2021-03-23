import java.util.Stack;

/**
 * @author wangxu
 * @date 2021/2/24
 * @Description
 */
class ListNodeTest {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        ListNodeTest test = new ListNodeTest();
        TreeNode node1 = new TreeNode(1, new TreeNode(3, new TreeNode(5, null, null), null), new TreeNode(2, null, null));
        TreeNode node2 = new TreeNode(2, new TreeNode(1, null, new TreeNode(4, null, null)), new TreeNode(3, null, new TreeNode(7, null, null)));
//        test.mergeTrees(node1, node2);
//        test.maxDepth(node1);
        test.constructMaximumBinaryTree(new int[]{3,2,1,6,0,5});
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return findMax(nums, 0, nums.length-1);
    }


    public TreeNode findMax(int[] nums, int left, int right) {
        if (left > right){
            return null;
        }
        int maxIndex = left;
        for(int i = left + 1; i <= right; i++) {
            if(nums[i] > nums[maxIndex]){
                maxIndex = i;
            }
        }
        // 找到最大值后递归调用
        TreeNode leftNode = findMax(nums, left, maxIndex - 1);
        TreeNode rightNode = findMax(nums, maxIndex + 1,  right);
        return new TreeNode(nums[maxIndex], leftNode, rightNode);
    }

    public int maxDepth(TreeNode root) {
        // 后续遍历，自底向上
        // 记录层数
        int maxDepth = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode lastVisit = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.peek();
            if (node.right == null || node.right == lastVisit) {
                // 弹出，可以受用
                maxDepth += 1;
                lastVisit = stack.pop();
                node = null;
            } else {
                node = node.right;
            }
        }
        return maxDepth;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stack = new Stack();
        Stack<TreeNode> stack1 = new Stack();
        Stack<TreeNode> stack2 = new Stack();
        TreeNode merge = new TreeNode(root1.val + root2.val);
        stack.push(merge);
        stack1.push(root1);
        stack2.push(root2);

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode node1 = stack1.pop();
            TreeNode node2 = stack2.pop();
            TreeNode left1 = node1.left;
            TreeNode left2 = node2.left;
            TreeNode right1 = node1.right;
            TreeNode right2 = node2.right;
            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    node.left = new TreeNode(left1.val + left2.val);
                    stack.push(node.left);
                    stack1.push(left1);
                    stack2.push(left2);
                } else if (left1 != null) {
                    node.left = left1;
                } else {
                    node.left = left2;
                }
            }

            // 处理右侧
            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    node.right = new TreeNode(right1.val + right2.val);
                    stack.push(node.right);
                    stack1.push(right1);
                    stack2.push(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else {
                    node.right = right2;
                }
            }
        }


        return merge;
    }

}


