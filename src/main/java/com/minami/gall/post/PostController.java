package com.minami.gall.post;

import com.minami.gall.gallery.GallService;
import com.minami.gall.post.model.PageVo;
import com.minami.gall.post.model.PostInsDto;
import com.minami.gall.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;
    private final GallService gallService;

    @GetMapping("/write")
    public String writePostView(Model model, Long id) {
        model.addAttribute("gallInfo", gallService.getGallNameById(id));
        return "writePost";
    }

    @PostMapping("/write")
    public String writePost(List<MultipartFile> imgList, PostInsDto dto, Model model, HttpServletRequest request) throws UnknownHostException {
        dto.setIp(IpUtils.getStartIp(request));
        service.writePost(imgList, dto);

        String url = "/board?id=" + dto.getGallId();
//        if (result == 0) {
//            model.addAttribute("message", "글 작성 실패");
//            model.addAttribute("url", url);
//            return "message";
//        }
        return "redirect:" + url;
    }

    @GetMapping("/view")
    public String getPostView(Model model, Long id, Long no, @PageableDefault(size = 50) Pageable pageable) {
        model.addAttribute("gallInfo", gallService.getGallNameById(id));
        model.addAttribute("postDetail", service.getPostById(no));

        PageVo vo = service.getPostsByGallId(id, pageable);
        model.addAttribute("postList", vo.getPosts());
        model.addAttribute("totalPage", vo.getTotalPage());
        model.addAttribute("currentPage", pageable.getPageNumber() + 1);
        return "postView";
    }
}
