package dsa.tree.binary

import dsa.common.collection.SelfTypedPersistentCollection
import dsa.tree.binary.avl.AvlEmptyNode
import dsa.tree.binary.unbalanced.UnbalancedEmptyNode

interface BinarySearchTree<T> : SelfTypedPersistentCollection<BinarySearchTree<T>, T> {
    val left: BinarySearchTree<T>
    val right: BinarySearchTree<T>
    val height: Int
    val value: T

    fun isLeftWeighted(): Boolean = left.height > right.height

    fun isRightWeighted(): Boolean = right.height > left.height

    companion object {

        fun <E : Comparable<E>> unbalanced(): BinarySearchTree<E> = unbalanced(Comparator.naturalOrder())
        fun <E> unbalanced(comparator: Comparator<E>): BinarySearchTree<E> = UnbalancedEmptyNode(comparator)

        fun <E : Comparable<E>> unbalancedOf(vararg elements: E): BinarySearchTree<E> =
            elements.fold(unbalanced()) { tree, element -> tree.add(element) }

        fun <E> unbalancedOf(comparator: Comparator<E>, vararg elements: E): BinarySearchTree<E> =
            elements.fold(unbalanced(comparator)) { tree, element -> tree.add(element) }

        fun <E : Comparable<E>> unbalancedFrom(elements: Collection<E>): BinarySearchTree<E> =
            unbalancedFrom(naturalOrder(), elements)

        fun <E> unbalancedFrom(comparator: Comparator<E>, elements: Collection<E>): BinarySearchTree<E> =
            unbalanced(comparator).addAll(elements)

        fun <E : Comparable<E>> avl(): BinarySearchTree<E> = avl(Comparator.naturalOrder())

        fun <E> avl(comparator: Comparator<E>): BinarySearchTree<E> = AvlEmptyNode(comparator)

        fun <E : Comparable<E>> avlOf(vararg elements: E): BinarySearchTree<E> =
            elements.fold(avl()) { tree, element -> tree.add(element) }

        fun <E> avlOf(comparator: Comparator<E>, vararg elements: E): BinarySearchTree<E> =
            elements.fold(avl(comparator)) { tree, element -> tree.add(element) }

        fun <E : Comparable<E>> avlFrom(elements: Collection<E>): BinarySearchTree<E> =
            avlFrom(Comparator.naturalOrder(), elements)

        fun <E> avlFrom(comparator: Comparator<E>, elements: Collection<E>): BinarySearchTree<E> =
            avl(comparator).addAll(elements)
    }
}