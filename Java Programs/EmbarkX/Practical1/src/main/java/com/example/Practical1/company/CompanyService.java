package com.example.Practical1.company;



import java.util.List;
import java.util.logging.LogManager;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company,Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
}
