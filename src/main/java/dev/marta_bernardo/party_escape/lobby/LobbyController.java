package dev.marta_bernardo.party_escape.lobby;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("${api-base-path}/admin/{adminId}/lobbies")
public class LobbyController {

    private final LobbyService lobbyService;

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @GetMapping()
    public ResponseEntity<List<LobbyResponseDTO>> index(@PathVariable("adminId") Long adminId) {
        return ResponseEntity.ok(lobbyService.getAll(adminId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LobbyResponseDTO> show(@PathVariable("id") Long id) {
        return ResponseEntity.ok(lobbyService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<LobbyResponseDTO>create(@PathVariable("adminId") Long adminId, @RequestBody LobbyRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lobbyService.create(request, adminId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LobbyResponseDTO> update(@PathVariable Long id, @RequestBody LobbyRequestDTO request) {
        return ResponseEntity.ok(lobbyService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        lobbyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
