package com.minami.gall.cmt;

import com.minami.gall.cmt.model.CmtInsDto;
import com.minami.gall.cmt.model.CmtVo;
import com.minami.gall.entity.Cmt;
import com.minami.gall.entity.Post;
import com.minami.gall.repository.CmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CmtService {
    private final CmtRepository rep;

    public int writeCmt(CmtInsDto dto) {
        rep.save(Cmt.builder()
                .post(Post.builder().postId(dto.getPostId()).build())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .pw(dto.getPw())
                .createdAt(LocalDateTime.now()).build());
        return 1;
    }

    public List<CmtVo> getCmtsByPostId(Long postId) {
        List<Cmt> cmts = rep.findByPost(Post.builder().postId(postId).build());
        return cmts.stream().map(c -> CmtVo.builder()
                .cmtId(c.getCmtId())
                .content(c.getContent())
                .writer(c.getWriter())
                .createdAt(c.getCreatedAt())
                .build()).toList();
    }
}
