package com.minami.gall.cmt;

import com.minami.gall.cmt.model.CmtInsDto;
import com.minami.gall.entity.Cmt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cmt")
public class CmtController {
    private final CmtService service;

    @PostMapping
    public Cmt writeCmt(@RequestBody CmtInsDto dto) {
        return service.writeCmt(dto);
    }
}
