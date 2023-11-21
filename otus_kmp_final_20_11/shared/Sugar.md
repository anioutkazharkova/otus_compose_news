### presenters property 

```
class Presenters<T:Any> (val clazz: KClass<T>) : ReadOnlyProperty<Any?, T?> {
    private var weakRef: WeakReference<T>? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
       if (weakRef == null) {
           val item = KoinDIFactory.instance.resolve(clazz) as? T
           weakRef = WeakReference(item)
       }
        return weakRef?.get()
    }
}

inline fun<reified T: Any> presenters() = Presenters(T::class)
```