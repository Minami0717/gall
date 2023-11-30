package com.minami.gall.gallery;

import com.minami.gall.common.entity.Gall;
import com.minami.gall.common.entity.GallManager;
import com.minami.gall.common.enums.TrueFalse;
import com.minami.gall.gallery.model.GallInfoDto;
import com.minami.gall.gallery.model.GallNameDto;
import com.minami.gall.common.repository.GallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GallService {
    private final GallRepository rep;

    public GallInfoDto getGallInfoById(String id) {
        Gall gall = rep.findById(id).orElseThrow();
        List<GallManager> managers = gall.getGallManagers();
        String mainManager = null;
        List<String> subManagers = new ArrayList<>();

        for (GallManager m : managers) {
            String managerInfo = String.format(
                    "%s(%s)", m.getGallManagerID().getUser().getNick(), m.getGallManagerID().getUser().getUid());

            if (m.getSubYn() == TrueFalse.FALSE) {
                mainManager = managerInfo;
            }
            else {
                subManagers.add(managerInfo);
            }
        }

        return GallInfoDto.builder()
                .gallId(gall.getGallId())
                .nm(gall.getNm())
                .img(gall.getImg())
                .intro(gall.getIntro())
                .manager(mainManager)
                .subManagers(subManagers)
                .createdAt(gall.getCreatedAt().toLocalDate())
                .build();
    }

    public GallNameDto getGallNameById(String id) {
        Gall gall = rep.findById(id).orElseThrow();
        return GallNameDto.builder()
                .gallId(gall.getGallId())
                .nm(gall.getNm())
                .build();
    }
}
