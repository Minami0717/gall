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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequestMapping("gallery")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;
    private final GallService gallService;

    @GetMapping("/{gallId}/write")
    public String writePost(Model model, @PathVariable String gallId) {
        model.addAttribute("gallInfo", gallService.getGallNameById(gallId));
        return "writePost";
    }

    @PostMapping("/write")
    public String writePost(List<MultipartFile> imgList, PostInsParam p, HttpServletRequest request) throws UnknownHostException {
        p.setIp(IpUtils.getStartIp(request));
        service.writePost(imgList, p);
        return "redirect:/gallery/" + p.getGallId();
    }

    @GetMapping("/{gallId}/{postId}")
    public String getPostView(Model model, @PageableDefault(size = 50) Pageable pageable,
                              @PathVariable String gallId, @PathVariable Long postId, String mode) {
        model.addAttribute("gallInfo", gallService.getGallNameById(gallId));
        model.addAttribute("postDetail", service.getPostDetail(postId));

        PageDto vo = service.getPostsByGallId(gallId, pageable, mode);
        model.addAttribute("postList", vo.getPosts());
        model.addAttribute("totalPage", vo.getTotalPage());
        model.addAttribute("currentPage", pageable.getPageNumber() + 1);
        return "postView";
    }

    @GetMapping("/{gallId}/pw/{postId}")
    public String pwCheck(Model model, @PathVariable String gallId, @PathVariable Long postId) {
        model.addAttribute("gallInfo", gallService.getGallNameById(gallId));
        model.addAttribute("postId", postId);
        return "pwCheck";
    }

    @ResponseBody
    @PostMapping("/pw")
    public boolean pwCheck(@RequestBody PostPwParam p) {
        return service.pwCheck(p);
    }

    @GetMapping("/{gallId}/upd/{postId}")
    public String updPost(Model model, @PathVariable Long postId, @PathVariable String gallId) {
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
    public PostRecoDto upRecoOrDeco(@PathVariable Long postId, String mode, HttpServletRequest r) throws Exception {
        String ip = r.getRemoteAddr();
        if(ip.equals("0:0:0:0:0:0:0:1")) { ip = InetAddress.getLocalHost().getHostAddress(); }

        return service.upRecoOrDeco(postId, mode, ip);
    }
}
