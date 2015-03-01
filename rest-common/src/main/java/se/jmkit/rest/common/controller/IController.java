package se.jmkit.rest.common.controller;

import java.util.List;

import se.jmkit.rest.common.entity.AbstractEntity;
import se.jmkit.rest.common.entity.JSONWrapperEntity;

public interface IController<T extends AbstractEntity<T>> {

    T create(T entity);

    T read(Long id);

    T update(T entity);

    T delete(Long id);

    List<T> list();

    JSONWrapperEntity<T> jSONWrapperList();
}
