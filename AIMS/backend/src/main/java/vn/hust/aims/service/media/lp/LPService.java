package vn.hust.aims.service.media.lp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.media.LP;
import vn.hust.aims.repository.media.LPRepository;
import vn.hust.aims.service.dto.input.media.lp.CreateLPInput;
import vn.hust.aims.service.dto.input.media.lp.GetLPInput;
import vn.hust.aims.service.dto.input.media.lp.UpdateLPInput;
import vn.hust.aims.service.dto.output.media.lp.CreateLPOutput;
import vn.hust.aims.service.dto.output.media.lp.GetLPOutput;
import vn.hust.aims.service.dto.output.media.lp.UpdateLPOutput;

@Service
@AllArgsConstructor
public class LPService {
    private final LPRepository lpRepository;

    public CreateLPOutput createLP(CreateLPInput createLPInput) {
        LP lp = LP.builder()
                .title(createLPInput.getTitle())
                .category(createLPInput.getCategory())
                .value(createLPInput.getValue())
                .price(createLPInput.getPrice())
                .quantityInStock(createLPInput.getQuantityInStock())
                .isAbleToRushDelivery(createLPInput.getIsAbleToRushDelivery())
                .weight(createLPInput.getWeight())
                .imageUrl(createLPInput.getImageUrl())
                .barcodeUrl(createLPInput.getBarcodeUrl())
                .description(createLPInput.getDescription())
                .importDate(createLPInput.getImportDate())

                .artists(createLPInput.getArtists())
                .recordLabel(createLPInput.getRecordLabel())
                .trackList(createLPInput.getTrackList())
                .genres(createLPInput.getGenres())
                .releaseDate(createLPInput.getReleaseDate())
                .build();

        lpRepository.save(lp);

        return CreateLPOutput.from("Create lp success");

    }

    public GetLPOutput getLP(GetLPInput getLPInput) {
        LP lp = lpRepository.getReferenceById(getLPInput.getId());

        return GetLPOutput
                .builder()
                .lp(lp)
                .build();
    }

    public UpdateLPOutput updateLP(UpdateLPInput updateLPInput) {
        LP lp = lpRepository.findById(updateLPInput.getId()).orElseThrow();

        lp.setTitle(updateLPInput.getTitle());
        lp.setCategory(updateLPInput.getCategory());
        lp.setValue(updateLPInput.getValue());
        lp.setPrice(updateLPInput.getPrice());
        lp.setQuantityInStock(updateLPInput.getQuantityInStock());
        lp.setIsAbleToRushDelivery(updateLPInput.getIsAbleToRushDelivery());
        lp.setWeight(updateLPInput.getWeight());
        lp.setImageUrl(updateLPInput.getImageUrl());
        lp.setBarcodeUrl(updateLPInput.getBarcodeUrl());
        lp.setDescription(updateLPInput.getDescription());
        lp.setImportDate(updateLPInput.getImportDate());

        lp.setArtists(updateLPInput.getArtists());
        lp.setRecordLabel(updateLPInput.getRecordLabel());
        lp.setTrackList(updateLPInput.getTrackList());
        lp.setGenres(updateLPInput.getGenres());
        lp.setReleaseDate(updateLPInput.getReleaseDate());

        lpRepository.save(lp);

        return UpdateLPOutput.from("Update success");

    }
}
