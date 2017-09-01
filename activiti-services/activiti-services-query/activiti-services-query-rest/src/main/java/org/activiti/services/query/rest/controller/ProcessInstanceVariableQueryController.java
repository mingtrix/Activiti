/*
 * Copyright 2017 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.services.query.rest.controller;

import org.activiti.services.query.es.model.VariableES;
import org.activiti.services.query.es.repository.VariableRepository;
import org.activiti.services.query.resource.VariableQueryResource;
import org.activiti.services.query.rest.assembler.VariableQueryResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/process-instances/{processInstanceId}/variables", produces = MediaTypes.HAL_JSON_VALUE)
public class ProcessInstanceVariableQueryController {

	private final VariableRepository variableRepository;

	private final VariableQueryResourceAssembler resourceAssembler;

	@Autowired
	public ProcessInstanceVariableQueryController(VariableRepository variableRepository,
			VariableQueryResourceAssembler resourceAssembler) {
		this.variableRepository = variableRepository;
		this.resourceAssembler = resourceAssembler;
	}

	@RequestMapping(method = RequestMethod.GET)
	public PagedResources<VariableQueryResource> findAll(@PathVariable String processInstanceId,

			Pageable pageable, PagedResourcesAssembler<VariableES> pagedResourcesAssembler) {
		return pagedResourcesAssembler.toResource(variableRepository.findAll(processInstanceId, pageable),
				resourceAssembler);
	}
}