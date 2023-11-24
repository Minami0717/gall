package com.minami.gall.gallery;

import com.minami.gall.entity.Gall;
import com.minami.gall.entity.GallManager;
import com.minami.gall.gallery.model.GallInfoVo;
import com.minami.gall.gallery.model.GallNameVo;
import com.minami.gall.repository.GallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GallService {
    private final GallRepository rep;

    @Value("${img-url}")
    private String imgUrl;

    public GallInfoVo getGallInfoById(Long id) {
        Gall gall = rep.findById(id).orElseThrow();
        List<GallManager> managers = gall.getGallManagers();
        String mainManager = null;
        List<String> subManagers = new ArrayList<>();

        for (GallManager m : managers) {
            String managerInfo = String.format(
                    "%s(%s)", m.getGallManagerID().getUser().getNick(), m.getGallManagerID().getUser().getUid());

            if (m.getSubYn() == 0) {
                mainManager = managerInfo;
            }
            else {
                subManagers.add(managerInfo);
            }
        }

        return GallInfoVo.builder()
                .gallId(gall.getGallId())
                .nm(gall.getNm())
                .img(String.format("%s/%s", imgUrl, gall.getImg()))
                .intro(gall.getIntro())
                .manager(mainManager)
                .subManagers(subManagers)
                .createdAt(gall.getCreatedAt().toLocalDate())
                .build();
    }

    public GallNameVo getGallNameById(Long id) {
        Gall gall = rep.findById(id).orElseThrow();
        return GallNameVo.builder()
                .gallId(gall.getGallId())
                .nm(gall.getNm())
                .build();
    }
}
