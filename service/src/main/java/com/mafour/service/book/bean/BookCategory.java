package com.mafour.service.book.bean;

import java.util.List;
import lombok.Data;

@Data
public class BookCategory {

  private String title;

  private String type;

  private List<BookCategory> subCategory;
}
