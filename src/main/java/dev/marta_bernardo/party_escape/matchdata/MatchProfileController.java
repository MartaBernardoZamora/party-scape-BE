package dev.marta_bernardo.party_escape.matchdata;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("${api-base-path}/match-profiles")
public class MatchProfileController {
    private final MatchProfileService matchProfileService;

    public MatchProfileController(MatchProfileService matchProfileService) {
        this.matchProfileService = matchProfileService;        
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchProfileResponseDTO> show(@PathVariable("matchId") Long matchId) {

        return ResponseEntity.ok(matchProfileService.getById(matchId));
    }
}
