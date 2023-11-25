package dsa.tree.binary.unbalanced

import dsa.tree.binary.BinarySearchTree
import dsa.tree.binary.EmptyNode

internal class UnbalancedEmptyNode<E>(comparator: Comparator<E>) : EmptyNode<E>(comparator) {

    override fun createLeafNode(comparator: Comparator<E>, value: E): BinarySearchTree<E> = UnbalancedTreeNode(comparator, value, this, this)

}