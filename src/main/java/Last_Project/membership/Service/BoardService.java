package Last_Project.membership.Service;

import Last_Project.membership.Entity.BoardEntity;
import Last_Project.membership.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /* 조회수 */
    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }

    /* 페이징 */
    @Transactional(readOnly = true)
    public Page<BoardEntity> pageList(Pageable pageable)

}

