package com.leusonguenha.personal_blog.article;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ArticleModel>> findAll() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<ArticleModel> findOne(@PathVariable long id) {
            return ResponseEntity.ok(articleService.findById(id));
    }

//                                    A D M I N
    @PostMapping("/new")
    public ResponseEntity<ArticleModel> save(@RequestBody ArticleModel article) {
        var created = articleService.create(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ArticleModel> update(@PathVariable long id, @RequestBody ArticleModel article) {
        return ResponseEntity.ok(articleService.update(id, article));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
