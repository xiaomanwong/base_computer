import javax.swing.ListModel

/**
 * @author wangxu
 * @date 2021/2/24
 * @Description
 *
 */
fun main() {
    val node = ListNode(1, ListNode(4, ListNode(3, ListNode(2, ListNode(7, ListNode(90, ListNode(54)))))))
    insertSortListNode(node)
}

private fun insertSortListNode(head: ListNode): ListNode? {
    if (head == null) {
        return null
    }
    val dumpHead = ListNode(0)
    dumpHead.next = head
    var lastSort: ListNode? = head
    var cur = head.next

    while (cur != null) {
        if (cur.value >= lastSort?.value!!) {
            lastSort = lastSort.next
        } else {
            var pre: ListNode? = dumpHead
            while (pre?.next?.value!! <= cur.value) {
                pre = pre.next
            }
            lastSort.next = cur.next
            cur.next = pre.next
            pre.next = cur
        }

        cur = lastSort?.next
    }
    return head
}