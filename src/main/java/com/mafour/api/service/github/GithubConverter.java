package com.mafour.api.service.github;

import com.mafour.api.dao.dao.GithubDO;
import com.mafour.api.service.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class GithubConverter extends AbstractConverter<Github, GithubDO> {

  @Override
  public Github converterFrom(GithubDO d) {
    return new Github().setCoverImgUrl(d.getCoverImgUrl()).setTitle(d.getTitle()).setLinkUrl(d.getLinkUrl());
  }

  @Override
  public GithubDO converterTo(Github github) {
    return null;
  }
}
