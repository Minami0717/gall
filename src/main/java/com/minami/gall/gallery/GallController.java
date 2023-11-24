package com.minami.gall.gallery;

import com.minami.gall.post.PostService;
import com.minami.gall.post.model.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
@Slf4j
public class GallController {
    private final GallService service;
    private final PostService postService;

    @GetMapping
    public String getGallInfoAndPost(Model model, Long id, @PageableDefault(size = 50) Pageable pageable) {
        PageVo vo = postService.getPostByGallId(id, pageable);

        model.addAttribute("gallInfo", service.getGallInfoById(id));
        model.addAttribute("postList", vo.getPosts());
        model.addAttribute("totalPage", vo.getTotalPage());
        model.addAttribute("currentPage", pageable.getPageNumber() + 1);
        return "gallMain";
    }
}
