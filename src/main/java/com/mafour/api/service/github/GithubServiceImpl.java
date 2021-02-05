package com.mafour.api.service.github;

import com.mafour.api.dao.tunnel.GithubTunnel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GithubServiceImpl implements GithubService {

  private final GithubTunnel githubTunnel;

  private final GithubConverter converter;

  @Override
  public List<Github> findAll() {
    return githubTunnel.all().stream().map(converter::converterFrom).collect(Collectors.toList());
  }
}
