package com.leusonguenha.personal_blog.article;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepo;

    public ArticleService(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }

    public ArticleModel create(ArticleModel articleModel) {
        if (articleModel.getTitle() == null || articleModel.getTitle().isBlank()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        return articleRepo.save(articleModel);
    }

    public List<ArticleModel> findAll() {
        return articleRepo.findAll();
    }

    public ArticleModel findById(long id) {
        return articleRepo.findById(id)
                .orElseThrow(() ->
                new EntityNotFoundException("Article with id " + id + " not found"));
    }

    public ArticleModel update(long id, ArticleModel articleModel) {
        ArticleModel article = findById(id);
        article.setTitle(articleModel.getTitle());
        article.setContent(articleModel.getContent());
        article.setCategory(articleModel.getCategory());
        article.setUpdatedAt(articleModel.getUpdatedAt());
        return articleRepo.save(articleModel);
    }

    public void deleteById(long id) {
        if (!articleRepo.existsById(id)) {
            throw new EntityNotFoundException("Article with id " + id + " not found");
        }
        articleRepo.deleteById(id);
    }

}
