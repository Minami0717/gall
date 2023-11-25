package com.minami.gall.cmt;

import com.minami.gall.cmt.model.CmtInsDto;
import com.minami.gall.cmt.model.CmtVo;
import com.minami.gall.entity.Cmt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cmt")
public class CmtController {
    private final CmtService service;

    @PostMapping
    public int writeCmt(@RequestBody CmtInsDto dto) {
        return service.writeCmt(dto);
    }

    @GetMapping
    public List<CmtVo> getCmtsByPostId(Long postId) {
        return service.getCmtsByPostId(postId);
    }
}
