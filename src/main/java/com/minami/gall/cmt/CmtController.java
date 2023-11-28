package com.minami.gall.cmt;

import com.minami.gall.cmt.model.CmtInsParam;
import com.minami.gall.cmt.model.CmtDto;
import com.minami.gall.common.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cmt")
public class CmtController {
    private final CmtService service;

    @PostMapping
    public int writeCmt(@RequestBody CmtInsParam p, HttpServletRequest request) throws UnknownHostException {
        p.setIp(IpUtils.getStartIp(request));
        return service.writeCmt(p);
    }

    @GetMapping
    public List<CmtDto> getCmtsByPostId(Long postId) {
        return service.getCmtsByPostId(postId);
    }
}
