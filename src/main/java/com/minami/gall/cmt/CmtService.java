package com.minami.gall.cmt;

import com.minami.gall.cmt.model.CmtInsParam;
import com.minami.gall.cmt.model.CmtDto;
import com.minami.gall.common.entity.Cmt;
import com.minami.gall.common.entity.Post;
import com.minami.gall.common.repository.CmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CmtService {
    private final CmtRepository rep;

    public int writeCmt(CmtInsParam p) {
        try {
            rep.save(Cmt.builder()
                    .post(Post.builder().postId(p.getPostId()).build())
                    .content(p.getContent())
                    .writer(p.getWriter())
                    .ip(p.getIp())
                    .pw(p.getPw())
                    .createdAt(LocalDateTime.now()).build());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    public List<CmtDto> getCmtsByPostId(Long postId) {
        List<Cmt> cmts = rep.findByPost(Post.builder().postId(postId).build());
        return cmts.stream().map(c -> CmtDto.builder()
                .cmtId(c.getCmtId())
                .content(c.getContent())
                .writer(c.getWriter())
                .ip(c.getIp())
                .createdAt(c.getCreatedAt().format(DateTimeFormatter.ofPattern("MM.dd HH:mm:ss")))
                .build()).toList();
    }
}
