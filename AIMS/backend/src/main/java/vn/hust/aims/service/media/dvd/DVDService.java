package vn.hust.aims.service.media.dvd;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.entity.media.DVD;
import vn.hust.aims.repository.media.DVDRepository;
import vn.hust.aims.service.dto.input.media.dvd.CreateDVDInput;
import vn.hust.aims.service.dto.input.media.dvd.GetDVDInput;
import vn.hust.aims.service.dto.input.media.dvd.UpdateDVDInput;
import vn.hust.aims.service.dto.output.media.dvd.CreateDVDOutput;
import vn.hust.aims.service.dto.output.media.dvd.GetDVDOutput;
import vn.hust.aims.service.dto.output.media.dvd.UpdateDVDOutput;

@Service
@AllArgsConstructor
public class DVDService {
    private final DVDRepository dvdRepository;

    public CreateDVDOutput createDVD(CreateDVDInput createDVDInput){
        DVD dvd = DVD.builder()
                .title(createDVDInput.getTitle())
                .category(createDVDInput.getCategory())
                .value(createDVDInput.getValue())
                .price(createDVDInput.getPrice())
                .quantityInStock(createDVDInput.getQuantityInStock())
                .isAbleToRushDelivery(createDVDInput.getIsAbleToRushDelivery())
                .weight(createDVDInput.getWeight())
                .imageUrl(createDVDInput.getImageUrl())
                .barcodeUrl(createDVDInput.getBarcodeUrl())
                .description(createDVDInput.getDescription())
                .importDate(createDVDInput.getImportDate())

                .discFormat(createDVDInput.getDiscFormat())
                .director(createDVDInput.getDirector())
                .runTime(createDVDInput.getRunTime())
                .studio(createDVDInput.getStudio())
                .language(createDVDInput.getLanguage())
                .subtitles(createDVDInput.getSubtitles())
                .genres(createDVDInput.getGenres())
                .releaseDate(createDVDInput.getReleaseDate())
                .build();

        dvdRepository.save(dvd);

        return CreateDVDOutput.from("Create dvd success");
    }

    public GetDVDOutput getDVD(GetDVDInput getDVDInput) {
        DVD dvd = dvdRepository.getReferenceById(getDVDInput.getId());

        return GetDVDOutput
                .builder().dvd(dvd).build();
    }

    public UpdateDVDOutput updateDVD(UpdateDVDInput updateDVDInput) {
        DVD dvd = dvdRepository.findById(updateDVDInput.getId()).orElseThrow();

        dvd.setTitle(updateDVDInput.getTitle());
        dvd.setCategory(updateDVDInput.getCategory());
        dvd.setValue(updateDVDInput.getValue());
        dvd.setPrice(updateDVDInput.getPrice());
        dvd.setQuantityInStock(updateDVDInput.getQuantityInStock());
        dvd.setIsAbleToRushDelivery(updateDVDInput.getIsAbleToRushDelivery());
        dvd.setWeight(updateDVDInput.getWeight());
        dvd.setImageUrl(updateDVDInput.getImageUrl());
        dvd.setBarcodeUrl(updateDVDInput.getBarcodeUrl());
        dvd.setDescription(updateDVDInput.getDescription());
        dvd.setImportDate(updateDVDInput.getImportDate());

        dvd.setDiscFormat(updateDVDInput.getDiscFormat());
        dvd.setDirector(updateDVDInput.getDirector());
        dvd.setRunTime(updateDVDInput.getRunTime());
        dvd.setStudio(updateDVDInput.getStudio());
        dvd.setLanguage(updateDVDInput.getLanguage());
        dvd.setSubtitles(updateDVDInput.getSubtitles());
        dvd.setGenres(updateDVDInput.getGenres());
        dvd.setReleaseDate(updateDVDInput.getReleaseDate());


        dvdRepository.save(dvd);

        return UpdateDVDOutput.from("Update success");
    }
}
