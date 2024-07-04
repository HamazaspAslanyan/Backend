package com.example.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DanceDetailsService {
//
//    private final DanceDetailsRepository danceDetailsRepository;
//    private final DanceMapper danceMapper;
//
//    @Autowired
//    private final TelegramMapper telegramMapper;
//
//
//    public List<DanceOptionDto> allOptions(){
//        List <Dance> all = danceOptionRepository.getDances();
//        return danceOptionMapper.toDanceDtoList(all);
//    }
//
//    public List<TelegramButton> getDanceList() {
////        List<DanceInDto> danceInDtos = db.getDanceList();
//        List<DanceInDto> danceInDtos = new ArrayList<>();
//        return telegramMapper.toDanceButtons(danceInDtos);
//    }
//
//
//    public DanceOptionDto getOption(UUID id) {
//        Dance dance = danceOptionRepository.findById(id)
//                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));
//
//        return danceOptionMapper.toDanceDto(dance, new ArrayList<>());
//    }
//
//    public DanceOptionDto createOption(DanceOptionDto danceOptionDto) {
//        Dance dance = danceOptionMapper.toDance(danceOptionDto);
//        Dance createdDance = danceOptionRepository.save(dance);
//        return danceOptionMapper.toDanceDto(createdDance, new ArrayList<>());
//    }
//
//    public DanceOptionDto updateOption(UUID id, DanceOptionDto danceOptionDto) {
//        Dance dance = danceOptionRepository.findById(id)
//                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));
//
////        danceMapper.updateDance(dance, danceMapper.toDance(danceDto));
//
//        Dance updatedDance = danceOptionRepository.save(dance);
//
//        return danceOptionMapper.toDanceDto(updatedDance, new ArrayList<>());
//    }
//
//    public DanceOptionDto deleteOption(UUID id) {
//        Dance dance = danceOptionRepository.findById(id)
//                .orElseThrow(() -> new AppException("Dance not found", HttpStatus.NOT_FOUND));
//
//        danceOptionRepository.deleteById(id);
//
//        return  danceOptionMapper.toDanceDto(dance, new ArrayList<>());
//
//    }
}

