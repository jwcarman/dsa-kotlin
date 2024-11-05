package dsa.tree.binary.rbt

import dsa.common.collection.SelfTypedPersistentCollection

class RedBlackTree<E> private constructor(internal val root: Node<E>, internal val comparator: Comparator<E>) :
    SelfTypedPersistentCollection<RedBlackTree<E>, E> {

    private constructor(comparator: Comparator<E>) : this(NilNode(), comparator)

    companion object {
        fun <E : Comparable<E>> create() = RedBlackTree(Comparator.naturalOrder<E>())
        fun <E> create(comparator: Comparator<E>) = RedBlackTree(comparator)
    }

    override val size: Int
        get() = root.size

    override fun add(element: E): RedBlackTree<E> = RedBlackTree(root.insert(element, comparator).toBlack(), comparator)

    override fun clear(): RedBlackTree<E> = RedBlackTree(comparator)

    override fun remove(element: E): RedBlackTree<E> {
        TODO("Not yet implemented")
    }

    override fun contains(element: E): Boolean = root.contains(element, comparator)

    override fun isEmpty(): Boolean = root.size == 0

    override fun iterator(): Iterator<E> = root.iterator()
}