package com.mafour.service.github.converter;

import com.mafour.dao.GithubDO;
import com.mafour.service.AbstractConverter;
import com.mafour.service.github.Github;
import org.springframework.stereotype.Component;

@Component
public class GithubConverter extends AbstractConverter<Github, GithubDO> {

  @Override
  public Github converterFrom(GithubDO d) {
    return new Github().setCoverImgUrl(d.getCoverImgUrl()).setTitle(d.getTitle());
  }

  @Override
  public GithubDO converterTo(Github github) {
    return null;
  }
}
