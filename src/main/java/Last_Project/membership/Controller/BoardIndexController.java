package Last_Project.membership.Controller;

import Last_Project.membership.Service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @GetMapping("/board/read/{id}")
    public String read(@PathVariable Long id, Model model){
        BoardResponseDto dto = BoardService.findById(id);
        boardService.updateView(id);
        model.addAttribute("board", dto);

        return "posts-read";
    }

}
