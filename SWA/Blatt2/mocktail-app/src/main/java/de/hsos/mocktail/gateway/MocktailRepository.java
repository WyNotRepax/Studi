package de.hsos.mocktail.gateway;

import java.util.Collection;

import de.hsos.mocktail.entity.Mocktail;

public interface MocktailRepository {
    public Collection<Mocktail> getAll();

    public Mocktail get(long id);

    public boolean create(Mocktail mocktail);

    public boolean update(Mocktail mocktail);

    public boolean delete(long id);
}
