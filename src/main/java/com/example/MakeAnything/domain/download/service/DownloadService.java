package com.example.MakeAnything.domain.download.service;

import org.springframework.stereotype.Service;

@Service
public interface DownloadService {

    // 다운로드 생성
    Long createDownload(Long orderId);

}
