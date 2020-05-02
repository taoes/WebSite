package com.mafour.service.book.yuque;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class YuqueCategoryData implements Serializable {

  private List<YuqueCategory> data;
}
