### Presenter
```
@propertyWrapper
struct ProvidePresenter<T:AnyObject> {
    private var service: T?
    public  var container = ConfigFactory.companion.shared
    public weak var view: IView?
    
    public init(){}
    
    public var wrappedValue: T? {
        mutating get {
            if self.service == nil {
              
                guard let view = self.view else {return service}
                self.service = container.createPresenter(view: view) as? T
            }
            return service
        }
        mutating set { service = newValue  }
    }
    public var projectedValue: ProvidePresenter<T> {
        get { return self }
        mutating set { self = newValue }
    }
}

```

### Injected
```
@propertyWrapper
struct Injected<T:AnyObject> {
    private var service: T?
    public  var container = KoinDIFactory.companion.instance
    
    public init(){}
    
    public var wrappedValue: T? {
        mutating get {
            if self.service == nil {
                self.service = container.resolve(type: T.self) as? T
            }
            return service
        }
        mutating set { service = newValue  }
    }
    public var projectedValue: Injected<T> {
        get { return self }
        mutating set { self = newValue }
    }
}

```