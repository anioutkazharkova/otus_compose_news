//
//  Inject.swift
//  iosApp
//
//  Created by Anna Zharkova on 02.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@propertyWrapper
struct Injected<T:AnyObject> {
    private var service: T?
    public weak var container = KoinDIFactory.shared
    
    public init(){}
    
    public var wrappedValue: T? {
        mutating get {
            if self.service == nil {
                self.service = container?.resolve(clazz_: T.self) as? T
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
