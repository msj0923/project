package Last_Project.membership.Service;

import Last_Project.membership.Entity.PostsEntity;
import Last_Project.membership.Repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {

    /* 조회수 */
    @Transactional
    public int updateView(Long id) {
        return PostsRepository.updateView(id);
    }

    }
}
