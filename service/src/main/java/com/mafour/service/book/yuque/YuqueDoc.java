package com.mafour.service.book.yuque;

@lombok.Data
public class YuqueDoc {

  private Abilities abilities;

  private Data data;

  @lombok.Data
  static class Abilities {
    private boolean update;

    private boolean destroy;
  }

  @lombok.Data
  public static class User {
    private int id;

    private String type;

    private String login;

    private String name;

    private String description;

    private String avatar_url;

    private int books_count;

    private int public_books_count;

    private int followers_count;

    private int following_count;

    private String created_at;

    private String updated_at;

    private String _serializer;
  }

  @lombok.Data
  static class Book {
    private int id;

    private String type;

    private String slug;

    private String name;

    private int user_id;

    private String description;

    private int creator_id;

    private int items_count;

    private int likes_count;

    private int watches_count;

    private String content_updated_at;

    private String updated_at;

    private String created_at;

    private String namespace;

    private User user;

    private String _serializer;
  }

  @lombok.Data
  static class Creator {
    private int id;

    private String type;

    private String login;

    private String name;

    private String description;

    private String avatar_url;

    private int books_count;

    private int public_books_count;

    private int followers_count;

    private int following_count;

    private String created_at;

    private String updated_at;

    private String _serializer;
  }

  @lombok.Data
  public static class Data {
    private int id;

    private String slug;

    private String title;

    private int book_id;

    private Book book;

    private int user_id;

    private String format;

    private String body;

    private String body_draft;

    private String body_html;

    private String body_lake;

    private String body_draft_lake;

    private int status;

    private int view_status;

    private int read_status;

    private int likes_count;

    private int comments_count;

    private String content_updated_at;

    private String deleted_at;

    private String created_at;

    private String updated_at;

    private String published_at;

    private String first_published_at;

    private int word_count;

    private String cover;

    private String description;

    private String custom_description;

    private String _serializer;
  }
}
