package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service_interface.InstitutionService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @Override
    public List<Institution> findAll(){
        return institutionRepository.findAll();
    }
    @Override
    public void save(Institution institution){
        institutionRepository.save(institution);
    }
    @Override
    public Institution getById(Long id){
        return institutionRepository.getById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        deleteInstitutionFromDonation(id);
        institutionRepository.deleteById(id);
    }

    private void deleteInstitutionFromDonation(Long id) {
        donationRepository.findAllByInstitutionId(id).forEach(donation -> {
            donation.setInstitution(null);
            donationRepository.save(donation);
        });
    }
}
