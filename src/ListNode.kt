/**
 * @author wangxu
 * @date 2021/2/24
 * @Description
 *
 */
class ListNode {
    var value: Int = 0
    var next: ListNode? = null

    constructor()
    constructor(value: Int) {
        this.value = value
    }

    constructor(value: Int, next: ListNode) {
        this.value = value
        this.next = next
    }
}