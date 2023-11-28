package com.minami.gall.post;

import com.minami.gall.gallery.GallService;
import com.minami.gall.post.model.*;
import com.minami.gall.common.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;
    private final GallService gallService;

    @GetMapping("/write/{gallId}")
    public String writePost(Model model, @PathVariable Long gallId) {
        model.addAttribute("gallInfo", gallService.getGallNameById(gallId));
        return "writePost";
    }

    @PostMapping("/write")
    public String writePost(List<MultipartFile> imgList, PostInsParam p, HttpServletRequest request) throws UnknownHostException {
        p.setIp(IpUtils.getStartIp(request));
        service.writePost(imgList, p);
        return "redirect:/board/" + p.getGallId();
    }

    @GetMapping("/{gallId}/{postId}")
    public String getPostView(Model model, @PageableDefault(size = 50) Pageable pageable,
                              @PathVariable Long gallId, @PathVariable Long postId) {
        model.addAttribute("gallInfo", gallService.getGallNameById(gallId));
        model.addAttribute("postDetail", service.getPostDetail(postId));

        PageDto vo = service.getPostsByGallId(gallId, pageable);
        model.addAttribute("postList", vo.getPosts());
        model.addAttribute("totalPage", vo.getTotalPage());
        model.addAttribute("currentPage", pageable.getPageNumber() + 1);
        return "postView";
    }

    @GetMapping("/pwCheck/{gallId}/{postId}")
    public String pwCheck(Model model, @PathVariable Long gallId, @PathVariable Long postId) {
        model.addAttribute("gallInfo", gallService.getGallNameById(gallId));
        model.addAttribute("postId", postId);
        return "pwCheck";
    }

    @ResponseBody
    @PostMapping("/pwCheck")
    public boolean pwCheck(@RequestBody PostPwParam p) {
        return service.pwCheck(p);
    }

    @GetMapping("/upd/{gallId}/{postId}")
    public String updPost(Model model, @PathVariable Long postId, @PathVariable Long gallId) {
        model.addAttribute("gallInfo", gallService.getGallNameById(gallId));
        model.addAttribute("post", service.getPostSimple(postId));
        return "updPost";
    }

    @PatchMapping
    public void updPost(@RequestBody PostUpdParam p) {
        service.updPost(p);
    }

    @ResponseBody
    @DeleteMapping("{postId}")
    public void delPost(@PathVariable Long postId) {
        service.delPost(postId);
    }

    @ResponseBody
    @PatchMapping("{postId}")
    public PostRecoDto upRecoOrDeco(@PathVariable Long postId, String mode) {
        return service.upRecoOrDeco(postId, mode);
    }
}
