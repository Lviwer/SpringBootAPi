package pl.lviwer.restapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.lviwer.restapi.model.Post;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // List<Post> findAllByTitle(String title); //JPA Query creation -> naming convention rules

    @Query("Select p From Post p")    //to eliminate 1+n Hibernate problem
    List<Post> findAllPosts(Pageable page);


}
