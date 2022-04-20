package br.com.dh.ctd.ecommerce.repositories;

import br.com.dh.ctd.ecommerce.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Integer> {
}
