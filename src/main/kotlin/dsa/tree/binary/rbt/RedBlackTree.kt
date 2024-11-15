package dsa.tree.binary.rbt

import dsa.common.collection.SelfTypedPersistentSet
import dsa.tree.PersistentSetFactory
import kotlinx.collections.immutable.PersistentSet

class RedBlackTree<E> private constructor(private val root: RedBlackNode<E>, private val comparator: Comparator<E>) :
    SelfTypedPersistentSet<RedBlackTree<E>, E> {

    private constructor(comparator: Comparator<E>) : this(NilNode(), comparator)

    companion object : PersistentSetFactory {
        override fun <E> create(comparator: Comparator<E>): PersistentSet<E> = RedBlackTree(comparator)
        override fun <E : Comparable<E>> create(): PersistentSet<E> = RedBlackTree(Comparator.naturalOrder())
    }

    override val size: Int
        get() = root.size

    override fun add(element: E): RedBlackTree<E> = RedBlackTree(root.add(element, comparator).toBlack(), comparator)

    override fun clear(): RedBlackTree<E> = RedBlackTree(comparator)

    override fun remove(element: E): RedBlackTree<E> =
        RedBlackTree(root.remove(element, comparator).toBlack(), comparator)

    override fun contains(element: E): Boolean = root.contains(element, comparator)

    override fun isEmpty(): Boolean = root.size == 0

    override fun iterator(): Iterator<E> = root.iterator()
}