package com.mafour.api.service.recommend;

import java.util.List;

public interface RecommendService {

  /** 获取全部的推荐内容 */
  List<Recommend> listAll();
}
