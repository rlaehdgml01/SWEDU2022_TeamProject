package com.study.project.service;

import com.study.project.entity.Market;
import com.study.project.entity.Review;
import com.study.project.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class MarketService {
    @Autowired
    private MarketRepository marketRepository;

    // 글 작성 저장
    public void marketWriteDo(Market market, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        market.setMafilename(fileName);
        market.setMafilepath("/files/"+ fileName);

        marketRepository.save(market);
    }

    // 글 목록 출력
    public List<Market> marketList() {

        return marketRepository.findAll();
    }

    // 상세 페이지
    public Market marketDetail(Integer id) {
        return marketRepository.findById(id).get();
    }

    // 글 삭제
    public void marketDelete(Integer id) {
        marketRepository.deleteById(id);
    }


    public void marketUpdateDo(Market market, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        market.setMafilename(fileName);
        market.setMafilepath("/files/"+ fileName);

        marketRepository.save(market);
    }
}