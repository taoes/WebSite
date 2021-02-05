package com.mafour.api.service.seo;

public interface SeoService {

  void push(String bookName, String slugName);

  void push(String bookId);
}
