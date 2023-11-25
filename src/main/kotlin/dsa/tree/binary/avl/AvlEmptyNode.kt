package dsa.tree.binary.avl

import dsa.tree.binary.BinarySearchTree
import dsa.tree.binary.EmptyNode

class AvlEmptyNode<T>(comparator: Comparator<T>) : EmptyNode<T>(comparator) {

    override fun createLeafNode(comparator: Comparator<T>, value: T): BinarySearchTree<T> =
        AvlTreeNode(comparator, value, this, this)
}