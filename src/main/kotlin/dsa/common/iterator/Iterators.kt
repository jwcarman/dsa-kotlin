package dsa.common.iterator

class EmptyIterator<T> : Iterator<T> {
    override fun hasNext(): Boolean = false
    override fun next(): T = throw NoSuchElementException()
}

class SingletonIterator<T>(private val value: T) : Iterator<T> {
    private var exhausted = false

    override fun hasNext(): Boolean = !exhausted

    override fun next(): T {
        exhausted = true
        return value
    }
}

class ChainedIterator<T>(vararg iterators: Iterator<T>) : Iterator<T> {
    private var chain: List<Iterator<T>> = iterators.toList().filter { it.hasNext() }
    override fun hasNext(): Boolean = chain.isNotEmpty()
    override fun next(): T {
        val next = chain.first().next()

        if (!chain.first().hasNext()) {
            chain = chain.drop(1)
        }
        return next
    }
}