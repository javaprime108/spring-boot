package com.ehcache.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.ehcache.model.Organisation;
import com.ehcache.repository.OrganisationRepository;

@Service
public class EhcacheService {

	@Autowired
	private OrganisationRepository orgRepo;

	/**
	 * add the organisation
	 * 
	 * @param request
	 * @return
	 */
	@Caching(evict = { @CacheEvict(value = "customCache", allEntries = true) }, put = {
			@CachePut(value = "customCache", key = "#request.orgName")})
	public Organisation addOrganisation(Organisation request) {
		if (request.getId() == null) {
			return orgRepo.save(request);
		} else {

			Optional<Organisation> orgsationUpdated = orgRepo.findById(request.getId());
			orgsationUpdated.get().setOrgEmail(request.getOrgEmail());
			orgsationUpdated.get().setOrgName(request.getOrgName());
			return orgRepo.save(orgsationUpdated.get());
		}

	}

	/**
	 * get the organisation dettail
	 * 
	 * @param orgId
	 * @return
	 */
	@Cacheable(value = "customCache", key = "#orgId")
	public Optional<Organisation> getOrganisationDetails(Long orgId) {
		System.out.println("calling database");
		return orgRepo.findById(orgId);

	}

}
