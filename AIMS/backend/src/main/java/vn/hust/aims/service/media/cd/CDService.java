package vn.hust.aims.service.media.cd;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.media.CD;
import vn.hust.aims.repository.media.CDRepository;
import vn.hust.aims.service.dto.input.media.cd.CreateCDInput;
import vn.hust.aims.service.dto.input.media.cd.GetCDInput;
import vn.hust.aims.service.dto.input.media.cd.UpdateCDInput;
import vn.hust.aims.service.dto.output.media.book.GetBookOutput;
import vn.hust.aims.service.dto.output.media.cd.CreateCDOutput;
import vn.hust.aims.service.dto.output.media.cd.GetCDOutput;
import vn.hust.aims.service.dto.output.media.cd.UpdateCDOutput;

@Service
@AllArgsConstructor
public class CDService {
    private final CDRepository cdRepository;

    public CreateCDOutput createCD(CreateCDInput createCDInput) {
        CD cd = CD.builder()
                .title(createCDInput.getTitle())
                .category(createCDInput.getCategory())
                .value(createCDInput.getValue())
                .price(createCDInput.getPrice())
                .quantityInStock(createCDInput.getQuantityInStock())
                .isAbleToRushDelivery(createCDInput.getIsAbleToRushDelivery())
                .weight(createCDInput.getWeight())
                .imageUrl(createCDInput.getImageUrl())
                .barcodeUrl(createCDInput.getBarcodeUrl())
                .description(createCDInput.getDescription())
                .importDate(createCDInput.getImportDate())

                .artists(createCDInput.getArtists())
                .recordLabel(createCDInput.getRecordLabel())
                .trackList(createCDInput.getTrackList())
                .genres(createCDInput.getGenres())
                .releaseDate(createCDInput.getReleaseDate())
                .build();

        cdRepository.save(cd);

        return CreateCDOutput.from("Create cd success");
    }

    public GetCDOutput getCD(GetCDInput getCDInput) {
        CD cd = cdRepository.getReferenceById(getCDInput.getId());

        return GetCDOutput.builder()
                .cd(cd)
                .build();
    }

    public UpdateCDOutput updateCD(UpdateCDInput updateCDInput) {
        CD cd = cdRepository.findById(updateCDInput.getId()).orElseThrow();

        cd.setTitle(updateCDInput.getTitle());
        cd.setCategory(updateCDInput.getCategory());
        cd.setValue(updateCDInput.getValue());
        cd.setPrice(updateCDInput.getPrice());
        cd.setQuantityInStock(updateCDInput.getQuantityInStock());
        cd.setIsAbleToRushDelivery(updateCDInput.getIsAbleToRushDelivery());
        cd.setWeight(updateCDInput.getWeight());
        cd.setImageUrl(updateCDInput.getImageUrl());
        cd.setBarcodeUrl(updateCDInput.getBarcodeUrl());
        cd.setDescription(updateCDInput.getDescription());
        cd.setImportDate(updateCDInput.getImportDate());

        cd.setArtists(updateCDInput.getArtists());
        cd.setRecordLabel(updateCDInput.getRecordLabel());
        cd.setTrackList(updateCDInput.getTrackList());
        cd.setGenres(updateCDInput.getGenres());
        cd.setReleaseDate(updateCDInput.getReleaseDate());

        cdRepository.save(cd);

        return UpdateCDOutput.from("Update success");
    }
}
