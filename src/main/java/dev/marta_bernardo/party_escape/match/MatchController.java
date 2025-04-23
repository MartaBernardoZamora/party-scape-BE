package dev.marta_bernardo.party_escape.match;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("${api-base-path}/admin/{adminId}/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping()
    public ResponseEntity<MatchResponseDTO>create(@RequestBody MatchRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.create(request));
    }
    
}
