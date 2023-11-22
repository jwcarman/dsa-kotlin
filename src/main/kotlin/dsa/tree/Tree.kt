package dsa.tree

import kotlinx.collections.immutable.PersistentCollection

interface Tree<T> : PersistentCollection<T> {
    override fun add(element: T): Tree<T>
    override fun remove(element: T): Tree<T>
    override fun containsAll(elements: Collection<T>): Boolean {
        return elements.all { contains(it) }
    }

    override fun builder(): PersistentCollection.Builder<T> {
        throw UnsupportedOperationException("Builder not supported")
    }

    override fun addAll(elements: Collection<T>): Tree<T> = elements.fold(this) { acc, e -> acc.add(e) }

    override fun clear(): Tree<T>

    override fun removeAll(predicate: (T) -> Boolean): Tree<T> = removeAll(filter { e -> predicate.invoke(e) }.toList())

    override fun removeAll(elements: Collection<T>): Tree<T> = elements.fold(this) { acc, e -> acc.remove(e) }

    override fun retainAll(elements: Collection<T>): Tree<T> = removeAll { !elements.contains(it) }
}