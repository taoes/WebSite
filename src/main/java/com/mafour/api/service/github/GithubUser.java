package com.mafour.api.service.github;

import lombok.Data;

@Data
public class GithubUser {
  private String login;

  private int id;

  private String avatar_url;

  private String gravatar_id;

  private String url;

  private String html_url;

  private String followers_url;

  private String following_url;

  private String gists_url;

  private String starred_url;

  private String subscriptions_url;

  private String organizations_url;

  private String repos_url;

  private String events_url;

  private String received_events_url;

  private String type;

  private boolean site_admin;

  private String name;

  private String company;

  private String blog;

  private String location;

  private String email;

  private String hireable;

  private String bio;

  private int public_repos;

  private int public_gists;

  private int followers;

  private int following;

  private String created_at;

  private String updated_at;
}
