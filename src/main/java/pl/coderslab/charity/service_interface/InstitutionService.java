package pl.coderslab.charity.service_interface;

import pl.coderslab.charity.entity.Institution;

import java.util.List;

public interface InstitutionService {
    List<Institution> findAll();

    void save(Institution institution);

    Institution getById(Long id);

    void deleteById(Long id);
}
