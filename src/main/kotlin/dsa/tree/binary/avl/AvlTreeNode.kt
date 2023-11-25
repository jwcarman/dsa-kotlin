package dsa.tree.binary.avl

import dsa.tree.binary.BinarySearchTree
import dsa.tree.binary.TreeNode

private const val RIGHT_UNBALANCED = 2
private const val LEFT_UNBALANCED = -2


class AvlTreeNode<T>(
    comparator: Comparator<T>,
    value: T,
    left: BinarySearchTree<T>,
    right: BinarySearchTree<T>
) : TreeNode<T>(comparator, value, left, right) {
    override fun createSubtree(
        comparator: Comparator<T>,
        value: T,
        left: BinarySearchTree<T>,
        right: BinarySearchTree<T>
    ): BinarySearchTree<T> {
        val balance = right.height - left.height
        return when (balance) {
            RIGHT_UNBALANCED -> rotateLeft(comparator, value, left, right)
            LEFT_UNBALANCED -> rotateRight(comparator, value, left, right)
            else -> AvlTreeNode(comparator, value, left, right)
        }
    }

    private fun <T> rotateLeft(
        comparator: Comparator<T>,
        value: T,
        left: BinarySearchTree<T>,
        right: BinarySearchTree<T>
    ): BinarySearchTree<T> {
        return if (right.isRightWeighted()) {
            AvlTreeNode(
                comparator,
                right.value,
                AvlTreeNode(comparator, value, left, right.left),
                right.right
            )
        } else {
            AvlTreeNode(
                comparator,
                right.left.value,
                AvlTreeNode(comparator, value, left, right.left.left),
                AvlTreeNode(comparator, right.value, right.left.right, right.right)
            )
        }
    }

    private fun <T> rotateRight(
        comparator: Comparator<T>,
        value: T,
        left: BinarySearchTree<T>,
        right: BinarySearchTree<T>
    ): BinarySearchTree<T> {
        return if (left.isLeftWeighted()) {
            AvlTreeNode(
                comparator,
                left.value,
                left.left,
                AvlTreeNode(comparator, value, left.right, right)
            )
        } else {
            AvlTreeNode(
                comparator,
                left.right.value,
                AvlTreeNode(comparator, left.value, left.left, left.right.left),
                AvlTreeNode(comparator, value, left.right.right, right)
            )
        }
    }
    override fun emptyTree(comparator: Comparator<T>): BinarySearchTree<T> = AvlEmptyNode(comparator)
}