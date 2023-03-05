package sptech.school.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.backend.entities.AbilityEntity;
import sptech.school.backend.entities.BarberEntity;
import sptech.school.backend.entities.SchedulingEntity;
import sptech.school.backend.exception.BusinessException;
import sptech.school.backend.repositories.ISchedulingRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchedulingService {

    private final ISchedulingRepository repository;
    private final BarberService barberService;
    private final AbilityService abilityService;

    public List<SchedulingEntity> findAll() {
        return repository.findAll();
    }

    public Optional<SchedulingEntity> findById(Long id) {
        return repository.findById(id);
    }

    public List<SchedulingEntity> findByDay(LocalDateTime date) {
        return repository.findAllByTime(date);
    }

    public SchedulingEntity save(SchedulingEntity schedule) {
        try {
            Optional<BarberEntity> optBarber = barberService.findById(schedule.getBarber().getId());
            Optional<AbilityEntity> optAbility = abilityService.findByid(schedule.getService().getId());

            if (optBarber.isEmpty())
                throw new BusinessException("Barbeiro não encontrado!");

            if (optAbility.isEmpty())
                throw new BusinessException("Serviço não encontrado!");

            Optional<SchedulingEntity> optTime = repository.findByTime(schedule.getTime());

            if (optTime.isPresent())
                throw new BusinessException("Já existe agendamento para este horário!");

            schedule.setBarber(optBarber.get());
            schedule.setService(optAbility.get());
            schedule.setScheduledDate(LocalDateTime.now());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getMessage();
        }

        return repository.save(schedule);
    }

    public SchedulingEntity findByBarberShop(Long id) {

        var list = repository.findAll();

        var res = list.stream()
                .map(x -> x.getBarberShop())
                .findFirst();

        if (res.get().getId().equals(id))
            return repository.findByBarberShopId(id);

        throw new BusinessException("Not found");
    }

    public List<SchedulingEntity> findAllSchedulingDates(Long id) {
        return repository.findAllByBarberShopIdOrderByTimeDesc(id);
    }

    public SchedulingEntity update(Long id, SchedulingEntity entity) {
        Optional<SchedulingEntity> opt = repository.findById(id);

        if (opt.isEmpty())
            throw new BusinessException("Nenhum registro encontradi");

        entity.setId(id);
        return save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public SchedulingEntity updateAll(Long id, SchedulingEntity entity) {
        Optional<SchedulingEntity> opt = repository.findById(id);

        if (opt.isEmpty())
            throw new BusinessException("Nenhum registro encontradi");

        entity.setId(id);
        return save(entity);
    }
}
