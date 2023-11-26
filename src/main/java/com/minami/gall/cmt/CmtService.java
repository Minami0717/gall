package com.minami.gall.cmt;

import com.minami.gall.cmt.model.CmtInsDto;
import com.minami.gall.cmt.model.CmtVo;
import com.minami.gall.entity.Cmt;
import com.minami.gall.entity.Post;
import com.minami.gall.repository.CmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CmtService {
    private final CmtRepository rep;

    public int writeCmt(CmtInsDto dto) {
        try {
            rep.save(Cmt.builder()
                    .post(Post.builder().postId(dto.getPostId()).build())
                    .content(dto.getContent())
                    .writer(dto.getWriter())
                    .ip(dto.getIp())
                    .pw(dto.getPw())
                    .createdAt(LocalDateTime.now()).build());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    public List<CmtVo> getCmtsByPostId(Long postId) {
        List<Cmt> cmts = rep.findByPost(Post.builder().postId(postId).build());
        return cmts.stream().map(c -> CmtVo.builder()
                .cmtId(c.getCmtId())
                .content(c.getContent())
                .writer(c.getWriter())
                .ip(c.getIp())
                .createdAt(c.getCreatedAt().format(DateTimeFormatter.ofPattern("MM.dd HH:mm:ss")))
                .build()).toList();
    }
}
