import sun.misc.Version.println
import sun.reflect.generics.tree.Tree
import java.util.*
import javax.xml.soap.Node

/**
 * @author wangxu
 * @date 2021/2/24
 * @Description
 *
 */
fun main() {
    val root = TreeNode()
}

fun preorderTraversal(root: TreeNode) {
    // 非递归写法， 需要一个栈来缓存节点
    val stack = Stack<TreeNode>()
    var node: TreeNode? = root
    while (node != null || !stack.isEmpty()) {

        while (node != null) {
            println(node.value)
            stack.push(node)
            node = node.left
        }
        // 左节点为空，寻找右节点
        if (!stack.isEmpty()) {
            node = stack.pop().right
        }
    }
}

fun preorderTraversal2(root: TreeNode?) {
    // 递归写法
    if (root != null) {
        println(root.value)
        preorderTraversal2(root.left)
        preorderTraversal2(root.right)
    }
}

fun middleorderTraversal(root: TreeNode?) {
    //    递归写法
    if (root != null) {
        middleorderTraversal(root.left)
        println(root.value)
        middleorderTraversal(root.right)
    }
}

fun middleorderTraversal2(root: TreeNode?) {
    //    非递归写法
    val stack = Stack<TreeNode>()
    var node: TreeNode? = root

    while (node != null || !stack.isEmpty()) {
        while (node != null) {
            stack.push(node)
            node = node.left
        }

        if (!stack.isEmpty()) {
            node = stack.pop()
            println(node.value)
            node = node.right
        }
    }
}

fun postOrderTraversal(root: TreeNode?): Unit {
    // 递归写法
    if (root != null) {
        postOrderTraversal(root.left)
        postOrderTraversal(root.right)
        println(root.value)
    }
}

fun postOrderTraversal2(root: TreeNode?): Unit {
    // 非递归写法
    val stack = Stack<TreeNode>()
    var node: TreeNode? = root
    var lastVisit:TreeNode? = root
    while (node != null || !stack.isEmpty()) {
        while (node != null) {
            stack.push(node)
            node = node.left
        }
        // 查看当前栈顶元素
        node = stack.peek()
        // 如果它的右子树也为空，或者右子树已经访问
        // 则可以直接输出当前节点值

        if (node.right == null || node.right == lastVisit) {
            println(node.value)
            stack.pop()
            lastVisit = node
            node = null
        } else {
            //否则遍历右子树
            node = node.right
        }
    }
}
