package Last_Project.membership.Repository;

import Last_Project.membership.Entity.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<PostsEntity, Long> {

    @Modifying
    @Query("update PostsEntity p set p.board_view = p.board_view + 1 where p.board_id = : board_id")
    int updateView(Long id);

    Page<PostsEntity> findByTitleContaining(String keyword, Pageable pageable);
}
