//NoticesController.java
package capstone.bank_api.controller;

import java.util.List;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import capstone.bank_api.model.Notice;
import capstone.bank_api.repository.NoticeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NoticesController {

    private NoticeRepository noticeRepository;

    public NoticesController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices(@RequestParam(required = false) String noticeSummary) {
        if (noticeSummary != null) {
            List<Notice> notices = noticeRepository.findByNoticeSummaryContaining(noticeSummary);
            if (!notices.isEmpty()) {
                return ResponseEntity.ok()
                        .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                        .body(notices);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            List<Notice> notices = noticeRepository.findAllActiveNotices();
            if (!notices.isEmpty()) {
                return ResponseEntity.ok()
                        .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                        .body(notices);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @GetMapping("/notices/{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable("id") int id) {
        Optional<Notice> notice = noticeRepository.findById(id);
        return notice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/notices/{id}")
    public ResponseEntity<Notice> updateNotice(@PathVariable("id") int id, @RequestBody Notice updatedNotice) {
        Optional<Notice> existingNoticeOptional = noticeRepository.findById(id);
        if (existingNoticeOptional.isPresent()) {
            Notice existingNotice = existingNoticeOptional.get();
            existingNotice.setNoticeSummary(updatedNotice.getNoticeSummary());
            existingNotice.setNoticeDetails(updatedNotice.getNoticeDetails());
            existingNotice.setNoticBegDt(updatedNotice.getNoticBegDt());
            existingNotice.setNoticEndDt(updatedNotice.getNoticEndDt());
            existingNotice.setUpdateDt(new Date(System.currentTimeMillis())); // Assuming this updates the update date
            noticeRepository.save(existingNotice);
            return ResponseEntity.ok(existingNotice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/notices")
    public ResponseEntity<Notice> createNotice(@RequestBody Notice newNotice) {
        try {
            // Set create date and update date
            newNotice.setCreateDt(new Date(System.currentTimeMillis()));
            newNotice.setUpdateDt(newNotice.getCreateDt());

            // Save the new notice
            Notice savedNotice = noticeRepository.save(newNotice);

            // Return the saved notice with a HTTP status code of 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNotice);
        } catch (Exception e) {
            // Return an error response if something goes wrong
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/notices/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable("id") int id) {
        Optional<Notice> existingNoticeOptional = noticeRepository.findById(id);
        if (existingNoticeOptional.isPresent()) {
            noticeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/notices")
    public ResponseEntity<?> deleteAllNotices() {
        try {
            noticeRepository.deleteAll();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
