package com.example.Student.Student.Service;

import com.example.Student.Student.DTOClasse.JournalDto;
import com.example.Student.Student.Entity.Journal;
import com.example.Student.Student.Entity.User;
import com.example.Student.Student.ExceptionHandling.ResourceNotFoundException;
import com.example.Student.Student.Repository.JournalRepository;
import com.example.Student.Student.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired UserService userService;

    //create new
    @Transactional
    public JournalDto createJournal( JournalDto journalDto,String userName) {
        try {
            User user = userRepository.findByUserName(userName)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            Journal journal = new Journal();
            journal.setTitle(journalDto.getTitle());
            journal.setContent(journalDto.getContent());
            journal.setUser(user);
            user.getJournals().add(journal);
            return modelMapper.map(journalRepository.save(journal), JournalDto.class);
        }catch (Exception e){
            System.out.println(e);
            throw   new RuntimeException ("Any error occurred while saving the entry",e);
        }
    }
    //update
    @Transactional
    public JournalDto updateJournal(Integer journalId, String userName, JournalDto newJournal) throws AccessDeniedException {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new ResourceNotFoundException("Journal not found"));

        // 🔐 Ownership check
        if (!journal.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("You are not allowed to update this journal");
        }
        // Partial update (PATCH-like behavior)
        if (newJournal.getTitle() != null && !newJournal.getTitle().isBlank()) {
            journal.setTitle(newJournal.getTitle());
        }
        if (newJournal.getContent() != null && !newJournal.getContent().isBlank()) {
            journal.setContent(newJournal.getContent());
        }
        Journal saved = journalRepository.save(journal);
        return modelMapper.map(saved, JournalDto.class);
    }

    //get journalById
    public Optional<JournalDto> getJournalByIdForUser(Integer journalId, Integer userId) {
        return journalRepository.findByIdAndUserId(journalId, userId)
                .map(journal -> modelMapper.map(journal, JournalDto.class));
    }

   // paticular user
    public List<JournalDto> getJournalsByUser(Integer userId) {
        List<JournalDto> list = journalRepository.findByUserId(userId)
                .stream()
                .map(journal -> modelMapper.map(journal, JournalDto.class))
                .collect(Collectors.toList());
        Collections.reverse(list);
        return list;
    }
    // all
    public List<JournalDto> getAllJournals(){
        return journalRepository.findAll()
                .stream().map(j -> modelMapper.map(j, JournalDto.class)).collect(Collectors.toList());
    }
    //delete any user own data only
    @Transactional
    public Boolean deleteJournal(Integer journalId,String userName) {
        boolean remove=false;
        try {
            User user = userService.loadByUsername(userName);
             remove = user.getJournals().removeIf(x -> x.getId().equals(journalId));
            if (remove) {
                userService.saveUser(user);
                journalRepository.deleteById(journalId);
            }
        } catch (RuntimeException e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while detecting the entry.",e);
        }
        return  remove;
    }
}
