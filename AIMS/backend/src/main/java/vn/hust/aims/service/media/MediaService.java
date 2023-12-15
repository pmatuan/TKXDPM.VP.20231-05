package vn.hust.aims.service.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hust.aims.repository.media.MediaRepository;
import vn.hust.aims.service.dto.input.media.DeleteMediaBulkInput;
import vn.hust.aims.service.dto.output.media.DeleteMediaBulkOutput;
import vn.hust.aims.service.dto.output.media.GetAllMediaOutput;

@Data
@Service
@AllArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;

    public GetAllMediaOutput getAll() {
        return GetAllMediaOutput.builder().mediaList(mediaRepository.findAll()).build();
    }

    public DeleteMediaBulkOutput deleteBulk(DeleteMediaBulkInput deleteMediaBulkInput) {
        mediaRepository.deleteAllById(deleteMediaBulkInput.getIds());
        return DeleteMediaBulkOutput.from("Delete bulk success");
    }

}
