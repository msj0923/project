package Last_Project.membership.Controller;

import Last_Project.membership.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardIndexController {

    private final BoardService boardService;



    /* 글 상세보기 */
    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, Model model) {
        BoardResponseDto.Response dto = boardService.findById(id);
        boardService.updateView(id);
        model.

    }



}
