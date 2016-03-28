package io.pivotal.springdata.pcf.gemfire.data;

import java.util.List;

/**
 * Created by Jay Lee on 3/28/16.
 */
public interface ServiceInterface<T> {
    List<T> findAll();

    public void save(T value);

    public Integer count();

    public T findOne(Long id);
}
