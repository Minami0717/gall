package com.minami.gall.cmt;

import com.minami.gall.cmt.model.CmtInsDto;
import com.minami.gall.entity.Cmt;
import com.minami.gall.entity.Post;
import com.minami.gall.repository.CmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CmtService {
    private final CmtRepository rep;

    public Cmt writeCmt(CmtInsDto dto) {
        return rep.save(Cmt.builder()
                .post(Post.builder().postId(dto.getPostId()).build())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .pw(dto.getPw())
                .createdAt(LocalDateTime.now()).build());
    }
}
