package com.mafour.api.service;

public abstract class AbstractConverter<DM, DO> {

  public abstract DM converterFrom(DO d);

  public abstract DO converterTo(DM dm);
}
