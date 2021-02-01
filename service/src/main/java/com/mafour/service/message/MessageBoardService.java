package com.mafour.service.message;

import java.util.List;

public interface MessageBoardService {

  List<Message> all();

  void create(String content);
}
