package com.mafour.service.github;

import com.mafour.service.github.converter.GithubConverter;
import com.mafour.tunnel.GithubTunnel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GithubServiceImpl implements GithubService {

  private GithubTunnel githubTunnel;

  private GithubConverter converter;

  @Override
  public List<Github> findAll() {
    return githubTunnel.all().stream().map(converter::converterFrom).collect(Collectors.toList());
  }
}
