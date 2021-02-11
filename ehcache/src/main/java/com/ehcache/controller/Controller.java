package com.ehcache.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ehcache.model.Organisation;
import com.ehcache.service.EhcacheService;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private EhcacheService eacheService;

	@PostMapping(value = "/add")
	public ResponseEntity<Organisation> addOrganisation(@RequestBody Organisation request) {
		Organisation addOrganisation = eacheService.addOrganisation(request);
		return ResponseEntity.ok().body(addOrganisation);

	}

	@GetMapping(value ="/org/info")
	public ResponseEntity<Optional<Organisation>> getOrganisationDetail(@RequestParam(required = true) Long orgId) {
		Optional<Organisation> organisationDetails = eacheService.getOrganisationDetails(orgId);
		return ResponseEntity.ok().body(organisationDetails);
	}

}
