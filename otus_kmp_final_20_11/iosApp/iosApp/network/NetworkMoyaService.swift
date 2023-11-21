//
//  NetworkMoyaService.swift
//  iosApp
//
//  Created by Anna Zharkova on 07.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Moya

enum NetworkMoyaService {
    case getNews
}

extension NetworkMoyaService: TargetType {
    var baseURL: URL { return URL(string: "https://newsapi.org")! }
    var path: String {
        switch self {
        case .getNews:
            return "/v2/everything?q=science"
        }
        var method: Moya.Method {
            return .get
        }
        var parameters: [String: Any]? {
            return nil
        }
        var parameterEncoding: ParameterEncoding {
            return URLEncoding.default
        }
        var sampleData: Data {
            return Data()
        }
        var task: Task {
            return .request
        }
    }
}
