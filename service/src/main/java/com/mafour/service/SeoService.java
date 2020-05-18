package com.mafour.service;

import java.io.IOException;

public interface SeoService {

  void push(String bookName, String slugName) throws IOException;

  void push(String bookId) throws IOException;
}
