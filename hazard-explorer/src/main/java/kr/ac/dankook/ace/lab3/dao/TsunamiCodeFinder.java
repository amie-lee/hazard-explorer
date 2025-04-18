package kr.ac.dankook.ace.lab3.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Tsunami;

@Component
public class TsunamiCodeFinder {

    // 특정 Code 범위에 포함되는 쓰나미 필터링
    public List<Tsunami> findByCodeRange(List<Tsunami> list, Integer codeMin, Integer codeMax) {
        return list.stream()
                .filter(t -> (codeMin == null || t.getValidity() >= codeMin) && (codeMax == null || t.getCode() <= codeMax))
                .toList();
    }

    // 특정 Code 값과 일치하는 쓰나미 필터링
    public List<Tsunami> findByCode(List<Tsunami> list, Integer code) {
        return list.stream()
                .filter(t -> t.getCode() == code)
                .toList();
    }

    // Code 리스트 추출
    public List<Integer> extractCodeValues(List<Tsunami> tsunamis) {
        return tsunamis.stream()
                .map(Tsunami::getCode)
                .filter(code -> code >= 0)
                .distinct()
                .sorted()
                .toList();
    }
}
