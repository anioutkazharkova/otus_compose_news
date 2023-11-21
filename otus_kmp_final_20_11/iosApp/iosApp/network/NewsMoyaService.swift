//
//  NewsMoyaService.swift
//  iosApp
//
//  Created by Anna Zharkova on 07.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Moya

class NewsMoyaService {
    private lazy var provider = MoyaProvider<NetworkMoyaService>()
    
    func loadNews(completion: @escaping(Result<NewsList,Error>)->Void) {
        provider.request(.getNews) { result in
            switch result {
            case .success(let response):
                let newsResponse = try? response.mapObject(NewsList.self)
                completion(.success(newsResponse))
            case .failure(let error):
                print(error.errorDescription ?? "Unknown error")
                completion(.failure(error))
            }
        }
    }
}
