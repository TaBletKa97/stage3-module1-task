package com.mjc.school.repository.dao.implementation;

import com.mjc.school.repository.DataGenerator;
import com.mjc.school.repository.dao.AbstractDao;
import com.mjc.school.repository.model.Author;

import java.util.List;
import java.util.Objects;

public class AuthorDAOImpl implements AbstractDao<Author, Long> {
    private final List<Author> authorsRepo;

    public AuthorDAOImpl() {
        this.authorsRepo = DataGenerator.getInstance().getAuthors();
    }

    @Override
    public List<Author> readAll() {
        return authorsRepo;
    }

    @Override
    public Author readById(Long id) {
        return authorsRepo.stream()
                .filter(e-> Objects.equals(e.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Author create(Author entity) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public Author update(Author entity) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public boolean deleteById(Long id) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }
}
