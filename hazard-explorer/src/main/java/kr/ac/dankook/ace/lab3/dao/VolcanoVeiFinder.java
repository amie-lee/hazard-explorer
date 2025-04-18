package kr.ac.dankook.ace.lab3.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import kr.ac.dankook.ace.lab3.dto.Volcano;

@Component
public class VolcanoVeiFinder {

    // 특정 VEI 범위에 포함되는 화산 필터링
    public List<Volcano> findByVeiRange(List<Volcano> list, Integer veiMin, Integer veiMax) {
        return list.stream()
                .filter(v -> (veiMin == null || v.getVei() >= veiMin) && (veiMax == null || v.getVei() <= veiMax))
                .toList();
    }

    // 특정 VEI 값과 일치하는 화산 필터링
    public List<Volcano> findByVei(List<Volcano> list, Integer vei) {
        return list.stream()
                .filter(v -> v.getVei() == vei)
                .toList();
    }
    
    // VEI 리스트 추출
    public List<Integer> extractVeiValues(List<Volcano> volcanoes) {
        return volcanoes.stream()
                .map(Volcano::getVei)
                .filter(vei -> vei >= 0)
                .distinct()
                .sorted()
                .toList();
    }
}
