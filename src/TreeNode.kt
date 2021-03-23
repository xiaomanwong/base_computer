

class TreeNode {
    var value: Int = 0
    var left: TreeNode? = null
    var right: TreeNode? = null

    constructor()

    constructor(value: Int) {
        this.value =value;
    }
    constructor(value: Int, left: TreeNode, right: TreeNode) {
        this.value = value
        this.left = left
        this.right = right
    }
}
