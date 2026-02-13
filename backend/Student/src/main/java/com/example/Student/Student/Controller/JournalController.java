package com.example.Student.Student.Controller;

import com.example.Student.Student.DTOClasse.JournalDto;
import com.example.Student.Student.Entity.Journal;
import com.example.Student.Student.Entity.User;
import com.example.Student.Student.Service.JournalService;
import com.example.Student.Student.Service.UserService;
import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    @Autowired private JournalService journalService;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;


    //create journal
    @PostMapping("/create")
    public ResponseEntity<JournalDto>  create( @RequestBody JournalDto dto) {
  try {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    JournalDto journalDto = journalService.createJournal(dto, userName);
    return new ResponseEntity<>(journalDto, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
}
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<JournalDto> updateJournalById(
            @PathVariable Integer id,
            @RequestBody JournalDto newJournal) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        JournalDto updated = journalService.updateJournal(id, userName, newJournal);
        return ResponseEntity.ok(updated);
    }
//all journal
    @GetMapping("/all")
    public ResponseEntity<List<JournalDto>> getAllJournalEntity() {
        try {
            List<JournalDto> alls=  journalService.getAllJournals();
             return new ResponseEntity<>(alls,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
// get user by jo by id
@GetMapping("/id/{id}")
public ResponseEntity<JournalDto> getAllUserJournal(@PathVariable Integer id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.loadByUsername(authentication.getName());
    return journalService.getJournalByIdForUser(id, Math.toIntExact(user.getId()))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
// get all journal of one user
@GetMapping("/one-user-all")
public ResponseEntity<List<JournalDto>> getLoggedInUserJournals() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.loadByUsername(authentication.getName());
    List<JournalDto> journals = journalService.getJournalsByUser(Math.toIntExact(user.getId()));
    if (journals.isEmpty()) {
        return ResponseEntity.noContent().build(); // 204
    }
    return ResponseEntity.ok(journals);
}


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.loadByUsername(authentication.getName());
            Boolean deletes = journalService.deleteJournal(id,user.getUserName());
          if(deletes){
              return new ResponseEntity<>("Journal deleted successfully",HttpStatus.NO_CONTENT);
          }
            return  new  ResponseEntity<>("Journal not deleted successfully",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return  ResponseEntity.notFound().build();
        }
    }


}
