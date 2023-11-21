//
//  Collector.swift
//  iosApp
//
//  Created by Anna Zharkova on 25.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

typealias Collector = Kotlinx_coroutines_coreFlowCollector

class Observer: Collector {
    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        callback(value)
        completionHandler(nil)
    }
    
    let callback:(Any?) -> Void
    
    init(callback: @escaping (Any?) -> Void) {
        self.callback = callback
    }
}
