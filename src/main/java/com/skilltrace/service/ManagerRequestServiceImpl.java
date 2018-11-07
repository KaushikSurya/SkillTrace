package com.skilltrace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilltrace.dao.ManagerRequestRepo;
import com.skilltrace.model.ManagerRequest;

@Service
public class ManagerRequestServiceImpl implements ManagerRequestService {

	@Autowired
	private ManagerRequestRepo managerReqRepo;
	
	@Override
	public ManagerRequest addManagerRequest(ManagerRequest managerRequest) {
		return managerReqRepo.save(managerRequest);
	}

}
