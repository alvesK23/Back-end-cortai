package sptech.school.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.backend.request.SchedulingAllRequest;
import sptech.school.backend.request.SchedulingRequest;
import sptech.school.backend.response.SchedulingResponse;
import sptech.school.backend.entities.SchedulingEntity;
import sptech.school.backend.mapper.SchedulingMapper;
import sptech.school.backend.obj.FilaObj;
import sptech.school.backend.services.SchedulingService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules")
public class SchedulingController {

    private final SchedulingService service;
    private final SchedulingMapper mapper;

    @GetMapping
    public ResponseEntity<List<SchedulingResponse>> findAll() {
        List<SchedulingEntity> schedules = service.findAll();
        List<SchedulingResponse> schedulesResponses = mapper.toResponseList(schedules);
        return schedulesResponses.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(schedulesResponses);
    }

    @GetMapping("dates/{id}")
    public ResponseEntity<List<SchedulingResponse>> findAll(@PathVariable Long id) {
        List<SchedulingEntity> schedules = service.findAllSchedulingDates(id);
        List<SchedulingResponse> response = mapper.toResponseList(schedules);
        return schedules.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponse> findById(@PathVariable Long id) {
        Optional<SchedulingEntity> optSchedule = service.findById(id);

        if (optSchedule.isEmpty())
            return ResponseEntity.notFound().build();

        SchedulingResponse scheduleResponse = mapper.toResponse(optSchedule.get());

        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }

    @PostMapping
    public ResponseEntity<SchedulingResponse> save(@Valid @RequestBody SchedulingRequest request) {
        SchedulingEntity schedule = mapper.toEntity(request);
        SchedulingEntity scheduleSave = service.save(schedule);
        SchedulingResponse response = mapper.toResponse(scheduleSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/row")
    public ResponseEntity<LocalDateTime[]> rowSchedule() {
        List<SchedulingEntity> schedules = service.findAll();
        List<SchedulingResponse> schedulesResponses = mapper.toResponseList(schedules);

        FilaObj<LocalDateTime> rowScheduler = new FilaObj<>(schedulesResponses.size());

        for (SchedulingResponse schedulesRespons : schedulesResponses) {

            rowScheduler.insert(schedulesRespons.getTime());
        }

        LocalDateTime[] rowSched = new LocalDateTime[schedulesResponses.size()];

        int i = 0;
        while (!rowScheduler.isEmpty()) {

            rowSched[i++] = rowScheduler.poll();

        }

        return ResponseEntity.status(200).body(rowSched);
    }

    @GetMapping("/dates/{date}")
    public ResponseEntity<List<SchedulingResponse>> findByDay(@RequestParam("datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        var schedules = service.findByDay(date);
        List<SchedulingResponse> schedulesResponses = mapper.toResponseList(schedules);
        System.out.println(date);
        return ResponseEntity.status(HttpStatus.OK).body(schedulesResponses);
    }


    @GetMapping("/barbershop/{id}")
    public ResponseEntity<SchedulingResponse> findByBarbershop(@PathVariable Long id) {

        SchedulingEntity optSchedule = service.findByBarberShop(id);

        SchedulingResponse scheduleResponse = mapper.toResponse(optSchedule);

        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchedulingResponse> update(@PathVariable Long id,
                                                     @Valid @RequestBody SchedulingRequest request) {
        var scheduling = service.findById(id);
        if (scheduling.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return Stream.of(request)
                .map(mapper::toEntity)
                .map(brbr -> service.update(id, brbr))
                .map(mapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .findFirst()
                .get();
    }

    @PutMapping("/barber/{id}")
    public ResponseEntity<SchedulingResponse> updateAll(@RequestBody SchedulingAllRequest request,@PathVariable Long id) {
        var scheduling = service.findById(id);
        if (scheduling.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return Stream.of(request)
                .map(mapper::toEntityAll)
                .map(brbr -> service.update(id, brbr))
                .map(mapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .findFirst()
                .get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        var ability = service.findById(id);
        if (ability.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
