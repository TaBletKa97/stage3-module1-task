package com.mjc.school.repository.implementation;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;

import java.util.List;
import java.util.Objects;

public class AuthorRepositoryImpl implements Repository<AuthorModel, Long> {
    private final DataSource dataSource;

    public AuthorRepositoryImpl() {
        this.dataSource = DataSource.getInstance();
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthorModels();
    }

    @Override
    public AuthorModel readById(Long id) {
        return dataSource.getAuthorModels().stream()
                .filter(e-> Objects.equals(e.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public Boolean deleteById(Long id) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }
}
