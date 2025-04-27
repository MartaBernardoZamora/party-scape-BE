package dev.marta_bernardo.party_escape.match;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("${api-base-path}/admin/{adminId}/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
    
    @GetMapping(params = "lobbyId")
    public ResponseEntity<List<MatchResponseDTO>> index(@RequestParam(required = true) Long lobbyId) {
        return ResponseEntity.ok(matchService.getByLobbyId(lobbyId));
    }
    @GetMapping(params = "code")
    public ResponseEntity<MatchResponseDTO> getByJoinCode(@RequestParam String code) {
        MatchResponseDTO match = matchService.getByJoinCode(code);
        if (match == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(match);
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchResponseDTO> show(@PathVariable("matchId") Long matchId) {
        return ResponseEntity.ok(matchService.getById(matchId));
    }
    @PostMapping()
    public ResponseEntity<MatchResponseDTO>create(@RequestBody MatchRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.create(request));
    }
    @PutMapping("/{matchId}")
    public ResponseEntity<MatchResponseDTO> update(@PathVariable Long matchId, @RequestBody MatchRequestDTO request) {
        return ResponseEntity.ok(matchService.update(matchId, request));
    }
    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> destroy(@PathVariable Long matchId) {
        matchService.delete(matchId);
        return ResponseEntity.noContent().build();
    }
    
}
