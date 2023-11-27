package com.minami.gall.cmt;

import com.minami.gall.cmt.model.CmtInsDto;
import com.minami.gall.cmt.model.CmtVo;
import com.minami.gall.entity.Cmt;
import com.minami.gall.utils.IpUtils;
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
    public int writeCmt(@RequestBody CmtInsDto dto, HttpServletRequest request) throws UnknownHostException {
        dto.setIp(IpUtils.getStartIp(request));
        return service.writeCmt(dto);
    }

    @GetMapping
    public List<CmtVo> getCmtsByPostId(Long postId) {
        return service.getCmtsByPostId(postId);
    }
}